 /***************************************************************************
 *
 * Copyright (c) 2013 Baidu.com, Inc. All Rights Reserved
 *
 **************************************************************************/
/*
 * Author: liwei12 <liwei12@baidu.com>
 *
 *
 * File: pb_collector.cpp
 * Create Date: 2014-09-03 14:23:49
 *
 */
#include "flow/pb_collector.h"
#include <string>
#include <iostream>
#include <google/protobuf/compiler/importer.h>
#include <google/protobuf/io/zero_copy_stream_impl.h>
#include "common/rt_base.h"
#include "common/pb_utils.h"
#include "common/rt_mutex.h"
#include "common/string_util.h"
#include "common/json_utils.h"
#include "common/pb_json_parser.h"
#include "boyscout/json_pb_utils.h"
using std::string;
using std::cout;
using std::cerr;
using std::endl;
using google::protobuf::Descriptor;
using google::protobuf::Message;
using google::protobuf::FileDescriptor;
using google::protobuf::FileDescriptorProto;
using google::protobuf::DescriptorPool;
using google::protobuf::compiler::DiskSourceTree;
using google::protobuf::compiler::MultiFileErrorCollector;
using google::protobuf::compiler::Importer;
using google::protobuf::MessageFactory;
using google::protobuf::DynamicMessageFactory;
using google::protobuf::FieldDescriptor;
using google::protobuf::io::ErrorCollector;
using google::protobuf::io::ArrayInputStream;
namespace bdg {
namespace udw {
namespace rt {
const int DEFAULT_UINT32 = 0;
const int DEFAULT_UINT64 = 0;
const int DEFAULT_INT = 0;
const int DEFAULT_INT64 = 0;
const float DEFAULT_FLOAT = 0.;
const double DEFAULT_DOUBLE = 0.;
const bool DEFAULT_BOOL = false;
google::protobuf::DynamicMessageFactory* PbCollector::_s_dync_fac = nullptr;
MutexLockRt PbCollector::_s_mutex;
google::protobuf::compiler::DiskSourceTree source_tree;
class MyErrorCollector : public google::protobuf::compiler::MultiFileErrorCollector {
public:
    virtual void AddError(
            const std::string& filename,
            int line,
            int column,
            const std::string&
            message) {
        RT_WARNING("parsing error. filename [%s], line [%d], column [%d], mess"
                "age [%s]", filename.c_str(), line, column, message.c_str());
    }
};
MyErrorCollector ec;
Importer* PbCollector::_s_importer = nullptr;
std::vector<std::string> file_parsed;
bool path_maped = false;
const FileDescriptor* PbCollector::get_file_descriptor(const string& file_name){
    string proto_path = "./";
    auto proto_file = file_name;
    MutexLockGuardRt lock(_s_mutex);
    auto slash_pos = file_name.rfind("/");
    if (slash_pos != string::npos){
        proto_path = file_name.substr(0, slash_pos);
        proto_file = file_name.substr(slash_pos + 1, file_name.size() - slash_pos - 1);
    }
    auto find = std::find(file_parsed.begin(), file_parsed.end(), proto_path);
    if (find == file_parsed.end() || _s_importer == nullptr){
        source_tree.MapPath("", proto_path.c_str());
        file_parsed.push_back(proto_path);
    }
    if (NULL == _s_importer){
        _s_importer = new Importer(&source_tree, &ec);
    }
    if (NULL == _s_importer){
        RT_FATAL("new object of class [%s] failed", "Importer");
        return NULL;
    }
    const FileDescriptor* fdesc = _s_importer->Import(proto_file.c_str());
    if (NULL == fdesc){
        RT_FATAL("import proto file failed");
    }
    return fdesc;
}
PbCollector::PbCollector(
        const string& cpath,
        const string &file,
        const string &type_name) :
        PbCollector(cpath, file, type_name, true) {
}
PbCollector::PbCollector(
        const string& cpath,
        const string &file,
        const string &type_name, bool is_dync) :
        Collector(cpath) {
    init_internal(file, type_name, is_dync);
}
PbCollector::PbCollector(
        const string& cpath,
        const string &file,
        int type_index,
        bool is_dync) :
        Collector(cpath) {
    init_internal(file, type_index, is_dync);
}
PbCollector::PbCollector(const PbCollector &pb_collector): Collector("") {
    _is_cache = false;
    _is_dync = pb_collector._is_dync;
    _file = pb_collector._file;
    _type_name = pb_collector._type_name;
    _message = pb_collector._message->New();
    _descriptor = _message->GetDescriptor();
    _reflection = _message->GetReflection();
    _message->MergeFrom(*pb_collector._message);
    _msg_id = pb_collector._msg_id;
    _seq_id = pb_collector._seq_id;
    _pipelet_id = pb_collector._pipelet_id;
}
//TODO BUG here
PbCollector& PbCollector::operator=(const PbCollector &pb_collector) {
    if (this == &pb_collector) {
        return *this;
    }
    if (_message != NULL){
        //TODO hidden bug here
        delete _message;
    }
    _is_cache = false;
    _is_dync = pb_collector._is_dync;
    _file = pb_collector._file;
    _type_name = pb_collector._type_name;
    _message = pb_collector._message->New();
    RT_TRACE("from_message:%s", pb_collector._message->ShortDebugString().c_str());
    //_message->MergeFrom(*pb_collector._message);
    _message->CopyFrom(*pb_collector._message);
    RT_TRACE("to_message:%s", _message->ShortDebugString().c_str());
    _reflection = _message->GetReflection();
    _descriptor = _message->GetDescriptor();
    _msg_id = pb_collector._msg_id;
    _seq_id = pb_collector._seq_id;
    _pipelet_id = pb_collector._pipelet_id;
    return *this;
}
#include <unistd.h>
void PbCollector::init_internal(
        const string& file,
        const string& type_name,
        bool is_dync) {
    _is_cache = false;
    _is_dync = is_dync;
    _file = file;
    _type_name = type_name;
    const FileDescriptor* file_desc = NULL;
    if (is_dync) {
        if (_s_dync_fac == nullptr) {
            MutexLockGuardRt lock(_s_mutex);
            if (_s_dync_fac == nullptr){
                _s_dync_fac = new DynamicMessageFactory;
            }
        }
        RT_TRACE("init pb collector from file, file [%s] name[%s], is_dync[%d]",
                 file.c_str(), type_name.c_str(), is_dync);
        file_desc = get_file_descriptor(file);
    } else {
        RT_TRACE("init pb collector from generated pool, file [%s] name[%s], "
                "is_dync[%d]", file.c_str(), type_name.c_str(), is_dync);
        file_desc = DescriptorPool::generated_pool()->FindFileByName(file);
    }
    _descriptor = file_desc->FindMessageTypeByName(type_name);
    if (_descriptor == NULL) {
        RT_FATAL("get descriptor from file failed.");
        assert(_descriptor != NULL);
    }
    _message = factory()->GetPrototype(_descriptor)->New();
    if (_message == NULL) {
        RT_FATAL("generate message failed.");
        assert(_message != NULL);
    }
    _reflection = _message->GetReflection();
}
void PbCollector::init_internal(
        const string& file,
        int type_index,
        bool is_dync) {
    _is_cache = false;
    _is_dync = is_dync;
    _file = file;
    const FileDescriptor* file_desc = NULL;
    if (is_dync) {
        if (_s_dync_fac == nullptr) {
            _s_dync_fac = new DynamicMessageFactory;
        }
        file_desc  = get_file_descriptor(file);
    } else {
        file_desc = DescriptorPool::generated_pool()->FindFileByName(file);
    }
    _descriptor = file_desc->message_type(type_index);
    if (_descriptor == NULL) {
        RT_FATAL("get descriptor from file [%s] with index [%d] failed.",
                file.c_str(), type_index);
        assert(_descriptor != NULL);
    }
    _message = factory()->GetPrototype(_descriptor)->New();
    if (_message == NULL) {
        RT_FATAL("generate message failed.");
        assert(_message != NULL);
    }
    _type_name = _descriptor->name();
    _reflection = _message->GetReflection();
}
PbCollector::PbCollector(
        const std::string& cpath,
        google::protobuf::Message* message,
        bool is_dync,
        bool is_cache)
        : Collector(cpath),
        _is_cache(is_cache),
        _message(message),
        _reflection(message->GetReflection()),
        _descriptor(message->GetDescriptor()) {
    _is_dync = is_dync;
}
PbCollector::~PbCollector() {
    if (!_is_cache){
        RT_DEBUG("destructor PbCollector , release message");
        if (_message !=  nullptr){
//            _message->Clear();
            delete _message;
        }
    }else{
        RT_DEBUG("is cache not delete");
        _message = nullptr;
    }
}
void PbCollector::clear(){
    if (_message != nullptr){
//        _message->Clear();
    }
    //  Collector::clear();
}
void PbCollector::message_clear() {
    if (_message != nullptr) {
        _message->Clear();
    }
}
int PbCollector::collect_string(
        const std::string& name,
        std::string* value,
        SearchHint* hint) {
    return collect_string(_message, name, value, hint);
}
int PbCollector::collect_string_ref(
        const std::string& name,
        const std::string& value,
        SearchHint* hint) {
    return collect_string(_message, name, const_cast<std::string*>(&value),
            hint);
}
int PbCollector::get_leaf_message(
        google::protobuf::Message* msg,
        const std::string& name,
        SearchHint* hint,
        MsgAndDesc& md,
        bool create) {
    HintHelper helper(hint);
   // RT_TRACE("get_leaf_message, name=%s, msg is [%s]", name.c_str(),
   // msg->ShortDebugString().c_str());
    google::protobuf::Message* sub_msg = get_sub_msg_or_self(msg, name, hint,
            create);
    if (sub_msg == msg) {
        if (sub_msg == NULL){
            RT_FATAL("message is null, name = %s", name.c_str());
        }
        int pindex = property_index(sub_msg, name, hint);
        if (pindex == -1) {
            RT_FATAL("get index[%s] in path[%s] failed, msg addr[%p], sub msg "
                    "addr[%p].", name.c_str(), path().c_str(), msg, sub_msg);
            assert(pindex != -1);
        }
        md.desc = msg->GetDescriptor()->field(pindex);
        md.msg = sub_msg;
        helper.lable_success();
        return RT_SUCCESS;
    } else if (sub_msg != nullptr) {
        return get_leaf_message(sub_msg, helper.substr_if_needed(name), hint,
                md, create);
    }
    return RT_NOT_FOUND;
}
Property* PbCollector::lookup(const std::string& name, SearchHint* hint, int index){
    MsgAndDesc md;
    int ret = get_leaf_message(_message, name, hint, md, false);
    if (ret == RT_SUCCESS){
        return PbUtils::convert_to_property(md, index);
    }
    return nullptr;
}
int PbCollector::get_in_property(const std::string& name, Property& property,
                                 SearchHint* hint, int index){
    MsgAndDesc md;
    int ret = get_leaf_message(_message, name, hint, md, false);
    if (RT_SUCCESS != ret || md.msg == NULL || md.desc == NULL){
        RT_DEBUG("parameter null, ret code [%d] msg [%p] desc[%p]",
                   ret, md.msg, md.desc);
        return RT_FAILED;
    }
    if (!md.desc->is_repeated()){
        RT_TRACE("get leaf, copy to property");
        return PbUtils::copy_to_property(md, property, index);
    }
    //this won't label init to hint, exec first.
    int real_index = get_real_index(hint, index, name);
    int size = md.msg->GetReflection()->FieldSize(*md.msg, md.desc);
    if (real_index >= size){
        RT_WARNING("out of range. index[%d], size[%d]", real_index, size);
        return RT_FAILED;
    }
    return PbUtils::copy_to_property(md, property, real_index);
/**
    HintHelper helper(hint);
    MsgAndDesc md;
    int ret = get_leaf_message(msg, name, hint, md, false);
    if (RT_SUCCESS != ret || md.msg == NULL || md.desc == NULL){
        RT_WARNING("parameter null, ret code [%d] msg [%p] desc[%p]",
                   ret, md.msg, md.desc);
        return nullptr;
    }
    if (!md.desc->is_repeated()){
        const string& str = md.msg->GetReflection()->GetStringReference(*md.msg, md.desc, NULL);
        return const_cast<string*>(&str);
    }
    int real_index = get_real_index(hint, index, name);
    int size = md.msg->GetReflection()->FieldSize(*md.msg, md.desc);
    if (real_index >= size){
        RT_WARNING("out of range. index[%d], size[%d]", real_index, size);
        return nullptr;
    }
    const string& str = md.msg->GetReflection()
        ->GetRepeatedStringReference(*md.msg, md.desc, real_index, NULL);
    return const_cast<string*>(&str);
*/
}
int PbCollector::sub_msg_index(Message* msg, const std::string& name, SearchHint* hint){
    int sub_index = -1;
    if (hint != NULL && hint->inited()){
        sub_index = hint->next_sub_index();
        RT_TRACE("get [%s] sub msg index from hint, index = %d", name.c_str(), sub_index);
    }else{
        auto pos = name.find(".");
        string subname = name;
        if (pos != std::string::npos){
            subname = name.substr(0, pos);
        } else{
           if (subname.find("(") ==  std::string::npos) {
              RT_TRACE("no dot and no repeated field in name [%s]", name.c_str());
              return -1;
           }
       }
       auto pos1 = subname.find("(");
       auto pos2 = subname.find(")");
       auto pos3 = subname.find(".");
       if (subname.size() > 0 && pos1 != std::string::npos &&
          pos2 != std::string::npos && pos2 > pos1 &&
          (pos3 == std::string::npos || pos3 > pos2)){// conditions in repeat array
                RT_DEBUG("get sub msg is repeated field [%s]", subname.c_str());
            //    std::string tmp = subname.substr(pos1 + 1, pos2 - pos1);
                subname = subname.substr(0, pos1);
            //   RT_DEBUG("get sub msg repeated field index [%s]", tmp.c_str());
            //  sub_index = atoi(tmp.c_str()); //get array index
       }
     auto field = msg->GetDescriptor()->FindFieldByName(subname);
     if (field == nullptr){
          RT_FATAL("field [%s] in path [%s] is empty, exit", subname.c_str(), path().c_str());
          assert(field != nullptr);
     };
     if (field->cpp_type() != google::protobuf::FieldDescriptor::CPPTYPE_MESSAGE){
         RT_DEBUG("found field [%s] in [%s] but not message type, should followed by index [%d]",
              subname.c_str(), name.c_str(), sub_index);
         return -1;
     }
     sub_index = field->index();
     if (hint != NULL){
         RT_DEBUG("set [%s] in [%s] next sub index is [%d]", subname.c_str(), name.c_str(), sub_index);
         hint->set_next_sub_index(field->index());
     }
  }
 return sub_index;
}
SearchCondition* PbCollector::get_condition(const std::string& name, SearchHint* hint){
    //RT_DEBUG("get condition expr from [%s]", name.c_str());
    if (hint != nullptr && hint->inited()) {
         return hint->next_condition();
    }
    auto l_pos =  name.find("[");
    if (l_pos != std::string::npos) {
        auto r_pos =  name.find("]");
        auto dpos = name.find(".");
        auto pos2 = name.find(".", r_pos);
        if (pos2 == std::string::npos) {
            RT_WARNING("using repeate field[%s] illegal,check each field!", name.c_str());
            assert(false);
        }
        string key = name.substr(dpos + 1,l_pos - dpos - 1);
        string value = name.substr(l_pos + 1,r_pos - l_pos - 1);
        if (value[0] == '\'') {
           value = value.substr(1, value.length() - 2);
        }
        SearchCondition* c = new SearchCondition();
        RT_DEBUG("get search condition key [%s], value [%s]", key.c_str(), value.c_str());
        c->set_field_name(key);
        c->set_target_value(value);
        c->set_type(CONDITION_EQUAL);
        if (hint != nullptr) {
            hint->set_next_condition(c);
        }
        return c;
    }
    auto pos1  =  name.find("(");
    if (pos1 != std::string::npos) {
        auto pos2 =  name.find(")", pos1);
        auto pos3 = name.find(".");
        if (pos2 != std::string::npos && (pos3 == string::npos
            || pos3 > pos2)) {
            SearchCondition* c = new SearchCondition();
            c->set_type(CONDITION_INDEX);
            c->set_index(atoi(name.substr(pos1 + 1, pos2 - pos1).c_str()));
            if (hint != nullptr) {
               hint->set_next_condition(c);
            }
            return c;
        }else {
            RT_WARNING("using repeate field[%s] illegal,check each field!", name.c_str());
            assert(false);
        }
    }
   return nullptr;
}
Message* PbCollector::get_sub_msg_or_self(
        Message* msg,
        const std::string& name,
        SearchHint* hint,
        bool create) {
    int sindex = sub_msg_index(msg, name, hint);
    if (sindex == -1 ){
        return msg;
    }
    const FieldDescriptor* f =  msg->GetDescriptor()->field(sindex);
    if (f == nullptr){
        RT_WARNING("field descriptor[%s] not found in path [%s]", name.c_str(), path().c_str());
        assert(f != nullptr);
    }
    if (f->cpp_type() != google::protobuf::FieldDescriptor::CPPTYPE_MESSAGE && !f->is_repeated()){
        RT_DEBUG("sub is not a message or a repeated type, path [%d]", name.c_str());
        return msg;
    }
    // when repeated, seletive s
    //how condition is stored?
    Message* ret_msg = nullptr;
    if (f->is_repeated()){
        int size = msg->GetReflection()->FieldSize(*msg, f);
        auto cond = get_condition(name, hint);
        RT_TRACE("condition is null [%d], name [%s]", (cond == nullptr), name.c_str());
        if (cond != nullptr){
            if (cond->type() == CONDITION_INDEX){
                if (size <= cond->index()){
                    RT_DEBUG("want index [%d] but the size is [%d] in [%s]",
                               cond->index(), size, name.c_str());
                    return nullptr;
                }else{
                    RT_TRACE("get in repeated message with given index[%d]", cond->index());
                    ret_msg = msg->GetReflection()->MutableRepeatedMessage(msg, f, cond->index());
                }
            }else if (cond->type() == CONDITION_EQUAL){
                std::string& field_name = cond->field();
                for (int i = 0; i < size; ++i){
                    ret_msg = msg->GetReflection()->MutableRepeatedMessage(msg, f, i);
//ATTENTION: hint not supported in repeated sub-message yet.
                    if (PbUtils::equal(ret_msg, field_name, cond->target_value(), nullptr, true)){
                        RT_TRACE("found property equals to the given value at index [%d]", i);
                        break;
                    }else{
                        RT_DEBUG("not equal");
                    };
                    ret_msg = nullptr;
                }
                RT_DEBUG("no property found equals to the given value, field:[%s]", field_name.c_str());
            }else if (cond->type() == CONDITION_FUNC){
                // TODO;
            }
            if (hint == nullptr){
                delete cond;
            }
        }
//    }else if (){
    }else{
        RT_TRACE("get descriptor [%s] in  pb collector with path [%s]", name.c_str(), path().c_str());
        ret_msg =  msg->GetReflection()->MutableMessage(msg, f);
    }
    if (create && ret_msg == nullptr){
        ret_msg = msg->GetReflection()->AddMessage(msg, f);
    }
    return ret_msg;
}
int PbCollector::property_index(Message* msg, const std::string& name, SearchHint* hint){
    int p_index = -1;
    if (hint != NULL && hint->inited()){
        p_index = hint->next_prop_index();
        RT_TRACE("get property index from hint, index = %d", p_index);
    }else{
        RT_TRACE("get field desc [%s] in path [%s]", name.c_str(), path().c_str());
        std::string fname = name;
        auto pos1 = fname.find("(");
        auto pos2 = fname.find(")", pos1);
        if (pos1 != std::string::npos && pos2 != std::string::npos) {
            fname = name.substr(0, pos1);
        }
        auto field = msg->GetDescriptor()->FindFieldByName(fname);
        if (field != nullptr){
            p_index = field->index();
            if (hint != NULL){
                hint->set_next_prop_index(p_index);
            }
        }else{
            RT_TRACE("get property index[%s] in message[%s] failed, path [%s]",
                    name.c_str(), msg->GetTypeName().c_str(), path().c_str());
        }
    }
    return p_index;
}
Collector* PbCollector::wrap_collector(
        const std::string& from,
        int index,
        SearchHint* hint,
        bool add ) {
    return wrap_collector(_message, from, index, hint, add);
}
Collector* PbCollector::wrap_collector(
        Message* msg,
        const std::string& name,
        int index,
        SearchHint* hint,
        bool add) {
    MsgAndDesc md;
    int ret = get_leaf_message(_message, name, hint, md, add);
    if (md.msg == NULL || md.desc == NULL){
        RT_FATAL("get sub message [%s] failed", name.c_str());
        if (add){
            assert(md.msg != NULL && md.desc != NULL);
        }
        return NULL;
    };
    auto field = md.desc;
    Message* wrapped = nullptr;
    if (add){
        RT_TRACE("need to add sub message first " );
        if (field->is_repeated()){
            wrapped = md.msg->GetReflection()->AddMessage(md.msg, field, factory());
            if (wrapped == NULL){
                RT_FATAL("add message [%s] failed", name.c_str());
                assert(wrapped != NULL);
            }
        }else{
            RT_WARNING("message [%s] is not repeated", name.c_str());
            return nullptr;
        }
    }else if (index > -1){
        wrapped = md.msg->GetReflection()->MutableRepeatedMessage(md.msg, field, index);
    }else{
        wrapped = md.msg->GetReflection()->MutableMessage(md.msg, field, factory());
    }
    if (wrapped == nullptr){
        RT_WARNING("message [%s] not found ", name.c_str());
        return nullptr;
    }
    return  new PbCollector(path() + "/" + name, wrapped, _is_dync);
}
Message* PbCollector::get_direct_sub_message(
        Message* msg,
        const std::string& name,
        bool create) {
    string *ret = nullptr;
    const FieldDescriptor * field = nullptr;
    field = msg->GetDescriptor()->FindFieldByName(name);
    if (field != nullptr &&
        field->cpp_type() == google::protobuf::FieldDescriptor::CPPTYPE_MESSAGE) {
        Message* sub_msg = msg->GetReflection()->MutableMessage(msg, field);
        if (sub_msg == nullptr) {
            if (create) {
                return msg->GetReflection()->AddMessage(msg, field);
            }
            RT_WARNING("message [%s] not found", name.c_str());
            return nullptr;
        }
        return sub_msg;
    } else {
        return nullptr;
    }
}
const Descriptor* PbCollector::descriptor(){
    return _descriptor;
}
google::protobuf::Message* PbCollector::message(
        const std::string& path,
        Message* parent,
        bool create){
    if (parent == nullptr){
        parent = _message;
    }
    if (path == ""){
        return parent;
    }else{
        std::string::size_type dpos = path.find(".");
        if (dpos != std::string::npos){
            Message* sub = get_direct_sub_message(parent, path.substr(0, dpos));
            return message(path.substr(dpos + 1),  sub);
        }
        return get_direct_sub_message(parent, path, create);
    }
}
Message* PbCollector::last_parent_message(const std::string& path, bool create){
    std::string::size_type last_dot = path.rfind(".");
    if (last_dot != std::string::npos){
        return message(path.substr(0, last_dot), _message, create);
    }
    return _message;
}
RT_Data_Type PbCollector::get_type(const std::string& name){
    MsgAndDesc md;
    RT_TRACE("PbCollector get_type name=%s", name.c_str());
    int ret = get_leaf_message(_message, name, nullptr, md, false);
    if (RT_SUCCESS == ret && md.msg != NULL && md.desc != NULL){
        if (md.desc != nullptr){
            return (RT_Data_Type)rt_type[md.desc->cpp_type()];
        }
    };
    RT_WARNING("Path [%s] not correct, in [%s]", name.c_str(),path().c_str());
    return RT_Type_Unknown;
}
int PbCollector::collect_in_str(
        const std::string& name,
        const std::string& value,
        SearchHint* hint) {
    MsgAndDesc md;
    int ret = get_leaf_message(_message, name, hint, md, true);
    if (RT_SUCCESS == ret && md.msg != NULL && md.desc != NULL) {
        ret = PbUtils::set_value_in_string(md, value);
    }
    return ret;
}
int PbCollector::collect_property(const std::string& name,
                                  Property& property, SearchHint* hint){
    MsgAndDesc md;
    int ret = get_leaf_message(_message, name, hint, md, true);
    if (RT_SUCCESS == ret && md.msg != NULL && md.desc != NULL){
        if (md.desc->cpp_type() == google::protobuf::FieldDescriptor::CPPTYPE_MESSAGE && property.get_type() == RT_Type_String ){
            if (md.desc->is_repeated()){
                ret = JsonPbUtils::fill_complex_field(md, *property.get_string());
            } else {
                google::protobuf::Message * sub_msg =
                    md.msg->GetReflection()->MutableMessage(md.msg, md.desc);
                ret = JsonPbUtils::fill_whole_message(sub_msg, *property.get_string());
            }
        } else if (md.desc->cpp_type() != google::protobuf::FieldDescriptor::CPPTYPE_MESSAGE) {
            ret = PbUtils::set_value_in_prop(md, property);
        } else {
            RT_WARNING("error message in collect property %s, %s",
                md.desc->name().c_str(), property.name().c_str());
            return RT_FAILED;
        }
    }
    return ret;
}
int PbCollector::collect_uint32(const std::string& name, uint32_t value, SearchHint* hint){
    MsgAndDesc md;
    int ret = get_leaf_message(_message, name, hint, md, true);
    if (RT_SUCCESS == ret && md.msg != NULL && md.desc != NULL){
        if (md.desc ->is_repeated()){
            md.msg->GetReflection()->AddUInt32(md.msg, md.desc, value);
        }else{
            md.msg->GetReflection()->SetUInt32(md.msg, md.desc, value);
        }
    };
    return ret;
}
int PbCollector::collect_uint64(const std::string& name, uint64_t value, SearchHint* hint){
    MsgAndDesc md;
    int ret = get_leaf_message(_message, name, hint, md, true);
    if (RT_SUCCESS == ret && md.msg != NULL && md.desc != NULL){
        if (md.desc ->is_repeated()){
            md.msg->GetReflection()->AddUInt64(md.msg, md.desc, value);
        }else{
            md.msg->GetReflection()->SetUInt64(md.msg, md.desc, value);
        }
    };
    return ret;
}
int PbCollector::collect_int32(const std::string& name, int32_t value, SearchHint* hint){
    MsgAndDesc md;
    int ret = get_leaf_message(_message, name, hint, md, true);
    if (RT_SUCCESS == ret && md.msg != NULL && md.desc != NULL){
        if (md.desc->is_repeated()){
            md.msg->GetReflection()->AddInt32(md.msg, md.desc, value);
        }else{
            md.msg->GetReflection()->SetInt32(md.msg, md.desc, value);
        }
    };
    return ret;
}
int PbCollector::collect_int64(const std::string& name, int64_t value, SearchHint* hint){
    MsgAndDesc md;
    int ret = get_leaf_message(_message, name, hint, md, true);
    if (RT_SUCCESS == ret && md.msg != NULL && md.desc != NULL){
        if (md.desc ->is_repeated()){
            md.msg->GetReflection()->AddInt64(md.msg, md.desc, value);
        }else{
            md.msg->GetReflection()->SetInt64(md.msg, md.desc, value);
        }
    };
    return ret;
}
int PbCollector::collect_float(const std::string& name, float value, SearchHint* hint){
    MsgAndDesc md;
    int ret = get_leaf_message(_message, name, hint, md, true);
    if (RT_SUCCESS == ret && md.msg != NULL && md.desc != NULL){
        if (md.desc ->is_repeated()){
            md.msg->GetReflection()->AddFloat(md.msg, md.desc, value);
        }else{
            md.msg->GetReflection()->SetFloat(md.msg, md.desc, value);
        }
    };
    return ret;
}
int PbCollector::collect_enum_int(const std::string& name, int value, SearchHint* hint){
    MsgAndDesc md;
    int ret = get_leaf_message(_message, name, hint, md, true);
    if (RT_SUCCESS == ret && md.msg != NULL && md.desc != NULL){
        const google::protobuf::EnumDescriptor* type = md.desc->enum_type();
        const google::protobuf::EnumValueDescriptor* v = type->FindValueByNumber(value);
        if (v != nullptr){
            if (md.desc->is_repeated()){
                md.msg->GetReflection()->AddEnum(md.msg, md.desc, v);
            }else{
                md.msg->GetReflection()->SetEnum(md.msg, md.desc, v);
            }
            return RT_SUCCESS;
        }else{
            RT_WARNING("out of range in enum, value is [%d]", value);
            ret = RT_NOT_FOUND;
        }
    };
    return ret;
}
int PbCollector::collect_double(const std::string& name, double value, SearchHint* hint){
    MsgAndDesc md;
    int ret = get_leaf_message(_message, name, hint, md, true);
    if (RT_SUCCESS == ret && md.msg != NULL && md.desc != NULL){
        if (md.desc ->is_repeated()){
            md.msg->GetReflection()->AddDouble(md.msg, md.desc, value);
        }else{
            md.msg->GetReflection()->SetDouble(md.msg, md.desc, value);
        }
    };
    return ret;
}
int PbCollector::collect_bool(const std::string& name, bool value, SearchHint* hint){
    MsgAndDesc md;
    int ret = get_leaf_message(_message, name, hint, md, true);
    if (RT_SUCCESS == ret && md.msg != NULL && md.desc != NULL){
        if (md.desc ->is_repeated()){
            md.msg->GetReflection()->AddBool(md.msg, md.desc, value);
        }else{
            md.msg->GetReflection()->SetBool(md.msg, md.desc, value);
        }
    };
    return ret;
}
int PbCollector::collect_string(Message* msg, const std::string& name, std::string* value, SearchHint* hint){
    MsgAndDesc md;
    int ret = get_leaf_message(msg, name, hint, md, true);
    if (RT_SUCCESS == ret && md.msg != NULL && md.desc != NULL){
        if (md.desc ->is_repeated()){
            md.msg->GetReflection()->AddString(md.msg, md.desc, *value);
        }else{
            md.msg->GetReflection()->SetString(md.msg, md.desc, *value);
        }
    };
    return ret;
}
std::string* PbCollector::get_string(const std::string& name, int index, SearchHint* hint){
    return get_string(_message, name, index, hint);
}
std::string PbCollector::get_string_value(const std::string& name, int index, SearchHint* hint){
    std::string* s = get_string(name, index, hint);
    return s == nullptr ? "" : *s;
}
uint32_t PbCollector::get_uint32(const std::string& name, int index, SearchHint* hint){
    HintHelper helper(hint);
    MsgAndDesc md;
    int ret = get_leaf_message(_message, name, hint, md, false);
    if (RT_SUCCESS != ret || md.msg == NULL || md.desc == NULL){
        RT_WARNING("parameter null, ret code [%d] msg [%p] desc[%p]",
                   ret, md.msg, md.desc);
        return DEFAULT_UINT32;
    }
    if (!md.desc->is_repeated()){
        return md.msg->GetReflection()->GetUInt32(*md.msg, md.desc);
    }
    //this won't label init to hint, exec first.
    int real_index = get_real_index(hint, index, name);
    int size = md.msg->GetReflection()->FieldSize(*md.msg, md.desc);
    if (real_index >= size){
        RT_WARNING("out of range. index[%d], size[%d]", real_index, size);
        return DEFAULT_UINT32;
    }
    return md.msg->GetReflection()->GetRepeatedUInt32(*md.msg, md.desc, real_index);
}
uint64_t PbCollector::get_uint64(const std::string& name, int index, SearchHint* hint){
    HintHelper helper(hint);
    MsgAndDesc md;
    int ret = get_leaf_message(_message, name, hint, md, false);
    if (RT_SUCCESS != ret || md.msg == NULL || md.desc == NULL){
        RT_WARNING("parameter null, ret code [%d] msg [%p] desc[%p]",
                   ret, md.msg, md.desc);
        return DEFAULT_UINT64;
    }
    if (!md.desc->is_repeated()){
        return md.msg->GetReflection()->GetUInt64(*md.msg, md.desc);
    }
    //this won't label init to hint, exec first.
    int real_index = get_real_index(hint, index, name);
    int size = md.msg->GetReflection()->FieldSize(*md.msg, md.desc);
    if (real_index >= size){
        RT_WARNING("out of range. index[%d], size[%d]", real_index, size);
        return DEFAULT_UINT64;
    }
    return md.msg->GetReflection()->GetRepeatedUInt64(*md.msg, md.desc, real_index);
}
std::string* PbCollector::get_string(Message* msg, const std::string& name, int index, SearchHint* hint){
    HintHelper helper(hint);
    MsgAndDesc md;
    int ret = get_leaf_message(msg, name, hint, md, false);
    if (RT_SUCCESS != ret || md.msg == NULL || md.desc == NULL){
        RT_DEBUG("parameter null, ret code [%d] msg [%p] desc[%p]",
                   ret, md.msg, md.desc);
        return nullptr;
    }
    if (!md.desc->is_repeated()){
        const string& str = md.msg->GetReflection()->GetStringReference(*md.msg, md.desc, NULL);
        return const_cast<string*>(&str);
    }
    int real_index = get_real_index(hint, index, name);
    int size = md.msg->GetReflection()->FieldSize(*md.msg, md.desc);
    if (real_index >= size){
        RT_WARNING("out of range. index[%d], size[%d]", real_index, size);
        return nullptr;
    }
    const string& str = md.msg->GetReflection()
        ->GetRepeatedStringReference(*md.msg, md.desc, real_index, NULL);
    return const_cast<string*>(&str);
};
int32_t PbCollector::get_int32(const std::string& name, int index, SearchHint* hint){
    HintHelper helper(hint);
    MsgAndDesc md;
    int ret = get_leaf_message(_message, name, hint, md, false);
    if (RT_SUCCESS != ret || md.msg == NULL || md.desc == NULL){
        RT_WARNING("parameter null, ret code [%d] msg [%p] desc[%p]",
                   ret, md.msg, md.desc);
        return DEFAULT_INT;
    }
    if (!md.desc->is_repeated()){
        return md.msg->GetReflection()->GetInt32(*md.msg, md.desc);
    }
    //this won't label init to hint, exec first.
    int real_index = get_real_index(hint, index, name);
    int size = md.msg->GetReflection()->FieldSize(*md.msg, md.desc);
    if (real_index >= size){
        RT_WARNING("out of range. index[%d], size[%d]", real_index, size);
        return DEFAULT_INT;
    }
    return md.msg->GetReflection()->GetRepeatedInt32(*md.msg, md.desc, real_index);
}
int64_t PbCollector::get_int64(const std::string& name, int index, SearchHint* hint){
    HintHelper helper(hint);
    MsgAndDesc md;
    int ret = get_leaf_message(_message, name , hint, md, false);
    if (RT_SUCCESS != ret || md.msg == NULL || md.desc == NULL){
        RT_WARNING("parameter null, ret code [%d] msg [%p] desc [%p]", ret, md.msg, md.desc);
        return DEFAULT_INT64;
    }
    if (!md.desc->is_repeated()){
        return md.msg->GetReflection()->GetInt64(*md.msg, md.desc);
    }
    //this won't label init to hint, exec first.
    int real_index = get_real_index(hint, index, name);
    int size = md.msg->GetReflection()->FieldSize(*md.msg, md.desc);
    if (real_index >= size){
        RT_WARNING("out of range. index[%d], size[%d]", real_index, size);
        return DEFAULT_INT64;
    }
    return md.msg->GetReflection()->GetRepeatedInt64(*md.msg, md.desc, real_index);
}
float PbCollector::get_float(const std::string& name, int index, SearchHint* hint){
    HintHelper helper(hint);
    MsgAndDesc md;
    int ret = get_leaf_message(_message, name, hint, md, false);
    if (RT_SUCCESS != ret || md.msg == NULL || md.desc == NULL){
        RT_WARNING("parameter null, ret code [%d] msg [%p] desc[%p]",
                   ret, md.msg, md.desc);
        return DEFAULT_FLOAT;
    }
    if (!md.desc->is_repeated()){
        return md.msg->GetReflection()->GetFloat(*md.msg, md.desc);
    }
    //this won't label init to hint, exec first.
    int real_index = get_real_index(hint, index, name);
    int size = md.msg->GetReflection()->FieldSize(*md.msg, md.desc);
    if (real_index >= size){
        RT_WARNING("out of range. index[%d], size[%d]", real_index, size);
        return DEFAULT_FLOAT;
    }
    return md.msg->GetReflection()->GetRepeatedFloat(*md.msg, md.desc, real_index);
}
int PbCollector::get_enum_int(const std::string& name, int index, SearchHint* hint){
    MsgAndDesc md;
    int ret = get_leaf_message(_message, name, hint, md, false);
    if (RT_SUCCESS == ret && md.msg != NULL && md.desc != NULL){
        if (index > -1){
            auto evalue = md.msg->GetReflection()->GetRepeatedEnum(*md.msg, md.desc, index);
            if (evalue != nullptr){
                return evalue->number();
            }
        }else{
            auto evalue = md.msg->GetReflection()->GetEnum(*md.msg, md.desc);
            if (evalue != nullptr){
                return evalue->number();
            }
        }
    };
    return DEFAULT_INT;
}
const std::string PbCollector::get_enum_string(const std::string& name_str, int index,
            SearchHint* hint) {
    MsgAndDesc md;
    int ret = get_leaf_message(_message, name_str, hint, md, false);
    if (RT_SUCCESS == ret && md.msg != NULL && md.desc != NULL) {
        if (index > -1) {
            auto evalue = md.msg->GetReflection()->GetRepeatedEnum(*md.msg, md.desc, index);
            if (evalue != nullptr) {
                return evalue->name();
            }
        } else {
            auto evalue = md.msg->GetReflection()->GetEnum(*md.msg, md.desc);
            if (evalue != nullptr) {
                return evalue->name();
            }
        }
    };
    return "";
}
double PbCollector::get_double(const std::string& name, int index, SearchHint* hint){
    HintHelper helper(hint);
    MsgAndDesc md;
    int ret = get_leaf_message(_message, name, hint, md ,false);
    if (RT_SUCCESS != ret || md.msg == NULL || md.desc == NULL){
        RT_WARNING("parameter null , ret code [%d] msg[%p] desc[%p]",ret, md.msg, md.desc);
        return DEFAULT_DOUBLE;
    }
    if (!md.desc->is_repeated()){
        return md.msg->GetReflection()->GetDouble(*md.msg, md.desc);
    }
    //this won't label init to hint, exec first.
    int real_index = get_real_index(hint, index, name);
    int size = md.msg->GetReflection()->FieldSize(*md.msg, md.desc);
    if (real_index >= size){
        RT_WARNING("out of range. index[%d], size[%d]",real_index, size);
        return DEFAULT_DOUBLE;
    }
    return md.msg->GetReflection()->GetRepeatedDouble(*md.msg, md.desc, real_index);
}
bool PbCollector::get_bool(const std::string& name, int index, SearchHint* hint){
    HintHelper helper(hint);
    MsgAndDesc md;
    int ret = get_leaf_message(_message, name, hint, md, false);
    if (RT_SUCCESS != ret || md.msg == NULL || md.desc == NULL){
        RT_WARNING("parameter null, ret code [%d] msg [%p] desc[%p]",
                   ret, md.msg, md.desc);
        return DEFAULT_BOOL;
    }
    if (!md.desc->is_repeated()){
        return md.msg->GetReflection()->GetBool(*md.msg, md.desc);
    }
    //this won't label init to hint, exec first.
    int real_index = get_real_index(hint, index, name);
    int size = md.msg->GetReflection()->FieldSize(*md.msg, md.desc);
    if (real_index >= size){
        RT_WARNING("out of range. index[%d], size[%d]", real_index, size);
        return DEFAULT_BOOL;
    }
    return md.msg->GetReflection()->GetRepeatedBool(*md.msg, md.desc, real_index);
}
Collector* PbCollector::clone(){
    RT_TRACE("PbCollector clone");
    return new PbCollector(*this);
}
ValueLen* PbCollector::to_value_len() {
    if (_message != nullptr){
        const int size = _message->ByteSize();
        char* buf = new char[size];
        if (_message->SerializeToArray(buf, size)){
            ValueLen* pvl = new ValueLen;
            pvl->value = buf;
            pvl->len = size;
            pvl->msg_id = _msg_id;
            pvl->seq_id = _seq_id;
            pvl->pipelet_id = _pipelet_id;
            return pvl;
        }
    }
    return nullptr;
}
int PbCollector::from_value_len(ValueLen * vl, ValuelenFormat format){
    if (vl->value == nullptr){
        return RT_FAILED;
    }
    int ret = RT_SUCCESS;
    switch (format){
        case JSON_FORMAT:{
                std::string str(vl->value);
                ret = msg_from_json(str, _message);
                break;
            }
        case PB_FORMAT:
                ret = from_value_len(vl);
                break;
        default:
                RT_WARNING("unknown value len format [%d]", format);
                ret =  RT_FAILED;
                break;
    }
    return ret;
}
int PbCollector::msg_from_json(std::string& json_string, google::protobuf::Message * srcmsg){
    return JsonPbUtils::fill_whole_message(srcmsg, json_string);
}
int PbCollector::from_value_len(ValueLen* vl){
    if (vl->value == nullptr){
        return RT_FAILED;
    }
//    ArrayInputStream ais(vl->value + vl->offset, vl->len);
    //add by wangzhiqing at 2014.10.21
    _msg_id = vl->msg_id;
    _seq_id = vl->seq_id;
    _pipelet_id = vl->pipelet_id;
    RT_TRACE("PbCollector:from_value_len:_msg_id=%ld, _seq_id=%ld, _pipelet_id=%d", _msg_id, _seq_id, _pipelet_id);
    if (_message->ParseFromArray(vl->value, vl->len)){
        return RT_SUCCESS;
    }
    return RT_FAILED;
}
/**
std::string PbCollector::sub_collector_string(const std::string& name){
    auto dpos = name.rfind(".");
    Message* msg = _message;
    std::string subname = name;
    if (dpos != std::string::npos){
        msg = last_parent_message(name);
        if (msg == nullptr){
            return "";
        }
        subname = name.substr(dpos + 1);
    }
    auto fd = msg->GetDescriptor()->FindFieldByName(subname);
    if (fd == nullptr){
        return "";
    }
    auto ref = msg->GetReflection();
     if (fd->is_repeated()){
        if (fd->cpp_type() == FieldDescriptor::CPPTYPE_MESSAGE){
            int size = ref->FieldSize(*msg, fd);
            std::string ret = "";
            for (int i = 0; i < size; ++i){
                ret += ref->GetRepeatedMessage(*msg, fd, i).DebugString();
                if (i != size - 1){
                    ret += ",";
                }
            }
        }else {
            //TODO
        }
        return "";
    }else if (fd->cpp_type() != FieldDescriptor::CPPTYPE_MESSAGE){
        return "";
    }else{
        return ref->GetMessage(*msg, fd, NULL).DebugString();
    }
    }  **/
void PbCollector::print_debug(){
//  printf("collector:[%s]=[%s]\n", name().c_str(), _message->Utf8DebugString().c_str());
//    RT_WARNING("message is:[%s]", _message->Utf8DebugString().c_str());
    printf("collector:[%s]=[%s]\n", name().c_str(), _message->Utf8DebugString().c_str());
    RT_DEBUG("message is:[%s]", _message->ShortDebugString().c_str());
}
void PbCollector::write_to_ostream(
        std::ostream &writer,
        std::string& column_delim,
        std::string& row_delim,
        std::vector<int>& field_nums,
        std::string& null_value,
        std::string& kv_delim) {
    Message* msg = _message;
    const FieldDescriptor* field_descriptor = NULL;
    int flag = 0;
//  writer << msg->ShortDebugString() << std::endl;
    for (int index : field_nums) {
        field_descriptor = _descriptor->FindFieldByNumber(index);
        if (field_descriptor == NULL) {
            RT_WARNING("failed find field_descriptor,which index is %d", index);
        }
        FieldDescriptor::Label label = field_descriptor->label();
        FieldDescriptor::CppType cpptype = field_descriptor->cpp_type();
        if (flag > 0) {
            if (column_delim.empty()) {
                writer << char(0);
            } else {
                writer << column_delim;
            }
        }
        flag++;
        if (kv_delim != ""){
            writer << field_descriptor->name() << kv_delim;
        }
        if (FieldDescriptor::LABEL_REPEATED == label) {
            int size = _reflection->FieldSize(*msg, field_descriptor);
            if (size == 0) {
                continue;
            }
            int i = 0;
            switch (cpptype) {
            case FieldDescriptor::CPPTYPE_INT32:
                for (i = 0; i < size - 1; i++) {
                    writer << _reflection->GetRepeatedInt32(*msg, field_descriptor, i) << ",";
                }
                writer << _reflection->GetRepeatedInt32(*msg, field_descriptor, i);
                break;
            case FieldDescriptor::CPPTYPE_INT64:
                for (i = 0; i < size - 1; i++) {
                    writer << _reflection->GetRepeatedInt64(*msg, field_descriptor, i) << ",";
                }
                writer << _reflection->GetRepeatedInt64(*msg, field_descriptor, i);
                break;
            case FieldDescriptor::CPPTYPE_UINT32:
                for (i = 0; i < size - 1; i++) {
                    writer << _reflection->GetRepeatedUInt32(*msg, field_descriptor, i) << ",";
                }
                writer << _reflection->GetRepeatedUInt32(*msg, field_descriptor, i);
                break;
            case FieldDescriptor::CPPTYPE_UINT64:
                for (i = 0; i < size - 1; i++) {
                    writer << _reflection->GetRepeatedUInt64(*msg, field_descriptor, i) << ",";
                }
                writer << _reflection->GetRepeatedUInt64(*msg, field_descriptor, i);
                break;
            case FieldDescriptor::CPPTYPE_FLOAT:
                for (i = 0; i < size - 1; i++) {
                    writer << _reflection->GetRepeatedFloat(*msg, field_descriptor, i) << ",";
                }
                writer << _reflection->GetRepeatedFloat(*msg, field_descriptor, i);
                break;
            case FieldDescriptor::CPPTYPE_DOUBLE:
                for (i = 0; i < size - 1; i++) {
                    writer << _reflection->GetRepeatedDouble(*msg, field_descriptor, i) << ",";
                }
                writer << _reflection->GetRepeatedDouble(*msg, field_descriptor, i);
                break;
            case FieldDescriptor::CPPTYPE_BOOL:
                for (i = 0; i < size - 1; i++) {
                    writer << _reflection->GetRepeatedBool(*msg, field_descriptor, i) << ",";
                }
                writer << _reflection->GetRepeatedBool(*msg, field_descriptor, i);
                break;
            case FieldDescriptor::CPPTYPE_ENUM:
                for (i = 0; i < size - 1; i++) {
                    writer << _reflection->GetRepeatedEnum(*msg, field_descriptor, i)->number()
                           << ",";
                }
                writer << _reflection->GetRepeatedEnum(*msg, field_descriptor, i)->number();
                break;
            case FieldDescriptor::CPPTYPE_STRING:
                for (i = 0; i < size - 1; i++) {
                    writer << _reflection->GetRepeatedString(*msg, field_descriptor, i);
                }
                writer << _reflection->GetRepeatedString(*msg, field_descriptor, i);
                break;
            default:
                RT_TRACE("cpptype:%d can know\n", cpptype);
            }
            // printf("\n");
        } else {
            if (!_reflection->HasField(*msg, field_descriptor)) {
                writer << null_value;
                continue;
            }
            switch (cpptype) {
            case FieldDescriptor::CPPTYPE_INT32:
                writer << _reflection->GetInt32(*msg, field_descriptor);
                break;
            case FieldDescriptor::CPPTYPE_INT64:
                writer << _reflection->GetInt64(*msg, field_descriptor);
                break;
            case FieldDescriptor::CPPTYPE_UINT32:
                writer << _reflection->GetUInt32(*msg, field_descriptor);
                break;
            case FieldDescriptor::CPPTYPE_UINT64:
                writer << _reflection->GetUInt64(*msg, field_descriptor);
                break;
            case FieldDescriptor::CPPTYPE_DOUBLE:
                writer << _reflection->GetDouble(*msg, field_descriptor);
                break;
            case FieldDescriptor::CPPTYPE_FLOAT:
                writer << _reflection->GetFloat(*msg, field_descriptor);
                break;
            case FieldDescriptor::CPPTYPE_BOOL:
                writer << _reflection->GetBool(*msg, field_descriptor);
                break;
            case FieldDescriptor::CPPTYPE_ENUM:
                writer << _reflection->GetEnum(*msg, field_descriptor)->number();
                break;
            case FieldDescriptor::CPPTYPE_STRING:
                writer << _reflection->GetString(*msg, field_descriptor);
                break;
            default:
                RT_TRACE("cpptype:%d can know\n", cpptype);
            }
            // printf("\n");
        }
    }
    writer << row_delim;
}
bool PbCollector::is_empty(const std::string& name, SearchHint* hint){
    MsgAndDesc md;
    int ret = get_leaf_message(_message, name, hint, md, false);
    if (ret != RT_SUCCESS || md.msg == NULL || md.desc == NULL){
        RT_DEBUG("property [%s] not exist in message with path:[%s]", name.c_str(), path().c_str());
        return true;
    }
    RT_TRACE("judge if property [%s] is empty in collector [%s]", name.c_str(), path().c_str());
    bool empty = true;
    if (md.desc->is_repeated()){
        empty = (md.msg->GetReflection()->FieldSize(*md.msg, md.desc) == 0);
    }else{
        bool retb = md.msg->GetReflection()->HasField(*md.msg, md.desc);
        if (retb){
            if (md.desc->cpp_type() == google::protobuf::FieldDescriptor::CPPTYPE_STRING){
                const string &str = md.msg->GetReflection()
                    ->GetStringReference(*md.msg, md.desc, NULL);
                empty = str.empty();
            }else{
                empty = false;
            }
        }else{
            empty = true;
        }
    }
    return empty;
}
bool PbCollector::has(const std::string& name, SearchHint* hint){
    MsgAndDesc md;
    int ret = get_leaf_message(_message, name, hint, md, false);
    if (ret != RT_SUCCESS || md.msg == NULL || md.desc == NULL){
        RT_DEBUG("property [%s] not exist in message with path:[%s]", name.c_str(), path().c_str());
        return false;
    }
    bool retb = false;
    FieldDescriptor::Label label = md.desc->label();
    if (FieldDescriptor::LABEL_REPEATED == label) {
        int size = md.msg->GetReflection()->FieldSize(*md.msg, md.desc);
        int real_index = get_real_index(hint, -1, name);
        if (size > 0 && size > real_index) {
            retb = true;
        }
    } else {
        retb = md.msg->GetReflection()->HasField(*md.msg, md.desc);
    }
    RT_TRACE("judge if property [%s] exists in collector [%s], result:[%d]", name.c_str(), path().c_str(), retb);
    return retb;
}
bool PbCollector::is_collector(const std::string& name){
    if (name == ""){
        return true;
    }
    const FieldDescriptor * field = nullptr;
    MsgAndDesc md;
    int ret = get_leaf_message(_message, name, NULL, md, true);
    if (ret == RT_SUCCESS && md.desc != nullptr){
        return md.desc->type() == google::protobuf::FieldDescriptor::TYPE_MESSAGE;
    } else {
        return false;
    }
}
int PbCollector::field_size(const std::string& name){
    MsgAndDesc md;
    int ret = get_leaf_message(_message, name, NULL, md, false);
    if (ret != RT_SUCCESS || md.msg == NULL || md.desc == NULL){
        RT_DEBUG("property [%s] not exist in message with path:[%s]", name.c_str(), path().c_str());
        return 0;
    }
    if (md.desc->is_repeated()){
        int size = md.msg->GetReflection()->FieldSize(*md.msg, md.desc);
        RT_TRACE("get field size for property [%s], size: [%d]", name.c_str(), size);
        return size;
    }
    RT_TRACE("get field size for property [%s] in collector [%s], non-repeated, size: 0", name.c_str(), path().c_str());
    return 0;
}
Collector* PbCollector::get_or_create_sub(const std::string& name, const int& index, SearchHint* ){
    if (index > -1){//remove cache
        Collector* c_in = take_direct_sub(name);
        if (c_in != nullptr){
            delete c_in;
        }
    }
    Collector* c = get_direct_sub_collector(name);
    if (c == nullptr){
        c = create_sub(name, index);
        if (c != nullptr){
            collect_sub_collector(name, c);
        }else{
            RT_WARNING("create sub with name: [%s] with index: [%d] failed", name.c_str(), index);
        }
    }
    return c;
}
Collector* PbCollector::create_sub_in_msg(
        Message* msg,
        const std::string& name,
        const int& index) {
    std::string::size_type dpos = name.find(".");
    if (dpos != std::string::npos) {
        Message* sub_msg = get_direct_sub_message(msg, name.substr(0, dpos), false);
        if (msg != nullptr) {
            return create_sub_in_msg(sub_msg, name.substr(dpos + 1), index);
        } else {
            RT_WARNING("found sub message [%s] failed, found null message",
                       name.substr(0, dpos).c_str());
            return nullptr;
        }
    }
    const FieldDescriptor * field = nullptr;
    field = msg->GetDescriptor()->FindFieldByName(name);
    if (field != nullptr && field->type() == google::protobuf::FieldDescriptor::TYPE_MESSAGE){
        if (field->is_repeated()){
            if (index < 0){
                RT_WARNING("index out of range: [%d]", index);
                return nullptr;
            }
            Message* sub_msg = msg->GetReflection()->MutableRepeatedMessage(msg, field, index);
            if (sub_msg == nullptr){
                RT_WARNING("message [%s] with index: [%d] not found", name.c_str(), index);
                return nullptr;
            }
            return  new PbCollector("", sub_msg, _is_dync);
        }else{
            Message* sub_msg = msg->GetReflection()->MutableMessage(msg, field, factory());
            if (sub_msg == nullptr){
                RT_WARNING("message [%s] not found ", name.c_str());
                return nullptr;
            }
            return  new PbCollector("/" + name + "/", sub_msg, _is_dync);
        }
    }else{
        RT_WARNING("create sub: [%s] in msg failed!", name.c_str());
    }
    return nullptr;
}
Collector* PbCollector::get_sub_collector(const std::string& name) {
     Collector* pbc = create_sub_in_msg(_message, name, -1);
     if (pbc != nullptr) {
        collect_sub_collector(name, pbc);
        return pbc;
     } else {
        return nullptr;
     }
}
Collector* PbCollector::create_sub(const std::string& name, const int& index){
    return create_sub_in_msg(_message, name, index);
}
Collector* PbCollector::add_repeat_message(
        const std::string& name,
        Message* msg) {
    if (msg ==  nullptr) {
        msg = _message;
    }
    std::string::size_type dpos = name.find(".");
    if (dpos != std::string::npos) {
        Message* sub_msg = get_direct_sub_message(msg, name.substr(0, dpos), true);
        return add_repeat_message(name.substr(dpos + 1), msg);
    }
    const FieldDescriptor * field = nullptr;
    field = _descriptor->FindFieldByName(name);
    if (field != nullptr && field->is_repeated()) {
        Message* sub_msg = _reflection->AddMessage(_message, field, factory());
        return  new PbCollector("/" + name + "/", sub_msg, _is_dync);
    } else {
        RT_WARNING("message [%s] is not repeated", name.c_str());
        return nullptr;
    }
}
Collector* PbCollector::add_repeat_message(const std::string& path) {
    return add_repeat_message(path, nullptr);
}
int PbCollector::merge_collector(const Collector& collector) {
    const PbCollector pb_collector = static_cast<const PbCollector&>(collector);
    if (_message == nullptr) {
        _message = pb_collector._message->New();
    }
    //_message->MergeFrom(*pb_collector._message);
    _message->CopyFrom(*pb_collector._message);
    return 0;
}
int PbCollector::pb_type(const std::string& name){
    const FieldDescriptor * field = nullptr;
    field = _descriptor->FindFieldByName(name);
    if (field != nullptr){
        return field->cpp_type();
    }
    RT_WARNING("pb type not found : [%s] " , name.c_str());
    return 0;
}
int PbCollector::copy_from_another_pb(const Message* src_msg,
                                      const std::string& src_field,
                                      const std::string& dest_field,
                                      SearchHint* src_hint,
                                      SearchHint* dest_hint,
                                      bool duplicate, int start, int length){
    if (src_msg == nullptr){
        RT_WARNING("param src_msg is null, copy from [%s] to [%s]",
                   src_field.c_str(), dest_field.c_str());
        return RT_PARAM_NULL;
    }
    MsgAndDesc md;
    get_leaf_message(const_cast<Message*>(src_msg), src_field, src_hint, md, false);
    if (md.msg == nullptr || md.desc == nullptr){
        return RT_SUCCESS;
    }
    MsgAndDesc dest_md;
    RT_TRACE("get msg and desc in message with name: [%s], message pointer:[%p]", dest_field.c_str(), _message);
    get_leaf_message(_message, dest_field, dest_hint, dest_md, true);
    if (dest_md.msg == nullptr || dest_md.desc == nullptr){
        return RT_FAILED;
    }
    return PbUtils::copy(md, dest_md, duplicate, start, length);
//    return copy_from_another_pb(src_msg, fd, dest_field);
}
}
}
}
/* vim: set ts=4 sw=4: */