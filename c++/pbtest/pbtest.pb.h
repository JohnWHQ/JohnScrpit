// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: pbtest.proto

#ifndef PROTOBUF_pbtest_2eproto__INCLUDED
#define PROTOBUF_pbtest_2eproto__INCLUDED

#include <string>

#include <google/protobuf/stubs/common.h>

#if GOOGLE_PROTOBUF_VERSION < 3005000
#error This file was generated by a newer version of protoc which is
#error incompatible with your Protocol Buffer headers.  Please update
#error your headers.
#endif
#if 3005001 < GOOGLE_PROTOBUF_MIN_PROTOC_VERSION
#error This file was generated by an older version of protoc which is
#error incompatible with your Protocol Buffer headers.  Please
#error regenerate this file with a newer version of protoc.
#endif

#include <google/protobuf/io/coded_stream.h>
#include <google/protobuf/arena.h>
#include <google/protobuf/arenastring.h>
#include <google/protobuf/generated_message_table_driven.h>
#include <google/protobuf/generated_message_util.h>
#include <google/protobuf/metadata.h>
#include <google/protobuf/message.h>
#include <google/protobuf/repeated_field.h>  // IWYU pragma: export
#include <google/protobuf/extension_set.h>  // IWYU pragma: export
#include <google/protobuf/generated_enum_reflection.h>
#include <google/protobuf/unknown_field_set.h>
// @@protoc_insertion_point(includes)

namespace protobuf_pbtest_2eproto {
// Internal implementation detail -- do not use these members.
struct TableStruct {
  static const ::google::protobuf::internal::ParseTableField entries[];
  static const ::google::protobuf::internal::AuxillaryParseTableField aux[];
  static const ::google::protobuf::internal::ParseTable schema[2];
  static const ::google::protobuf::internal::FieldMetadata field_metadata[];
  static const ::google::protobuf::internal::SerializationTable serialization_table[];
  static const ::google::protobuf::uint32 offsets[];
};
void AddDescriptors();
void InitDefaultsgps_data_LocationXYImpl();
void InitDefaultsgps_data_LocationXY();
void InitDefaultsgps_dataImpl();
void InitDefaultsgps_data();
inline void InitDefaults() {
  InitDefaultsgps_data_LocationXY();
  InitDefaultsgps_data();
}
}  // namespace protobuf_pbtest_2eproto
class gps_data;
class gps_dataDefaultTypeInternal;
extern gps_dataDefaultTypeInternal _gps_data_default_instance_;
class gps_data_LocationXY;
class gps_data_LocationXYDefaultTypeInternal;
extern gps_data_LocationXYDefaultTypeInternal _gps_data_LocationXY_default_instance_;

enum gps_data_SatelliteType {
  gps_data_SatelliteType_DEFAULT = 0,
  gps_data_SatelliteType_GPS = 1,
  gps_data_SatelliteType_GLONASS = 2,
  gps_data_SatelliteType_GALILEO = 3
};
bool gps_data_SatelliteType_IsValid(int value);
const gps_data_SatelliteType gps_data_SatelliteType_SatelliteType_MIN = gps_data_SatelliteType_DEFAULT;
const gps_data_SatelliteType gps_data_SatelliteType_SatelliteType_MAX = gps_data_SatelliteType_GALILEO;
const int gps_data_SatelliteType_SatelliteType_ARRAYSIZE = gps_data_SatelliteType_SatelliteType_MAX + 1;

const ::google::protobuf::EnumDescriptor* gps_data_SatelliteType_descriptor();
inline const ::std::string& gps_data_SatelliteType_Name(gps_data_SatelliteType value) {
  return ::google::protobuf::internal::NameOfEnum(
    gps_data_SatelliteType_descriptor(), value);
}
inline bool gps_data_SatelliteType_Parse(
    const ::std::string& name, gps_data_SatelliteType* value) {
  return ::google::protobuf::internal::ParseNamedEnum<gps_data_SatelliteType>(
    gps_data_SatelliteType_descriptor(), name, value);
}
// ===================================================================

class gps_data_LocationXY : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:gps_data.LocationXY) */ {
 public:
  gps_data_LocationXY();
  virtual ~gps_data_LocationXY();

  gps_data_LocationXY(const gps_data_LocationXY& from);

  inline gps_data_LocationXY& operator=(const gps_data_LocationXY& from) {
    CopyFrom(from);
    return *this;
  }
  #if LANG_CXX11
  gps_data_LocationXY(gps_data_LocationXY&& from) noexcept
    : gps_data_LocationXY() {
    *this = ::std::move(from);
  }

  inline gps_data_LocationXY& operator=(gps_data_LocationXY&& from) noexcept {
    if (GetArenaNoVirtual() == from.GetArenaNoVirtual()) {
      if (this != &from) InternalSwap(&from);
    } else {
      CopyFrom(from);
    }
    return *this;
  }
  #endif
  inline const ::google::protobuf::UnknownFieldSet& unknown_fields() const {
    return _internal_metadata_.unknown_fields();
  }
  inline ::google::protobuf::UnknownFieldSet* mutable_unknown_fields() {
    return _internal_metadata_.mutable_unknown_fields();
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const gps_data_LocationXY& default_instance();

  static void InitAsDefaultInstance();  // FOR INTERNAL USE ONLY
  static inline const gps_data_LocationXY* internal_default_instance() {
    return reinterpret_cast<const gps_data_LocationXY*>(
               &_gps_data_LocationXY_default_instance_);
  }
  static PROTOBUF_CONSTEXPR int const kIndexInFileMessages =
    0;

  void Swap(gps_data_LocationXY* other);
  friend void swap(gps_data_LocationXY& a, gps_data_LocationXY& b) {
    a.Swap(&b);
  }

  // implements Message ----------------------------------------------

  inline gps_data_LocationXY* New() const PROTOBUF_FINAL { return New(NULL); }

  gps_data_LocationXY* New(::google::protobuf::Arena* arena) const PROTOBUF_FINAL;
  void CopyFrom(const ::google::protobuf::Message& from) PROTOBUF_FINAL;
  void MergeFrom(const ::google::protobuf::Message& from) PROTOBUF_FINAL;
  void CopyFrom(const gps_data_LocationXY& from);
  void MergeFrom(const gps_data_LocationXY& from);
  void Clear() PROTOBUF_FINAL;
  bool IsInitialized() const PROTOBUF_FINAL;

  size_t ByteSizeLong() const PROTOBUF_FINAL;
  bool MergePartialFromCodedStream(
      ::google::protobuf::io::CodedInputStream* input) PROTOBUF_FINAL;
  void SerializeWithCachedSizes(
      ::google::protobuf::io::CodedOutputStream* output) const PROTOBUF_FINAL;
  ::google::protobuf::uint8* InternalSerializeWithCachedSizesToArray(
      bool deterministic, ::google::protobuf::uint8* target) const PROTOBUF_FINAL;
  int GetCachedSize() const PROTOBUF_FINAL { return _cached_size_; }
  private:
  void SharedCtor();
  void SharedDtor();
  void SetCachedSize(int size) const PROTOBUF_FINAL;
  void InternalSwap(gps_data_LocationXY* other);
  private:
  inline ::google::protobuf::Arena* GetArenaNoVirtual() const {
    return NULL;
  }
  inline void* MaybeArenaPtr() const {
    return NULL;
  }
  public:

  ::google::protobuf::Metadata GetMetadata() const PROTOBUF_FINAL;

  // nested types ----------------------------------------------------

  // accessors -------------------------------------------------------

  // optional string x = 1;
  bool has_x() const;
  void clear_x();
  static const int kXFieldNumber = 1;
  const ::std::string& x() const;
  void set_x(const ::std::string& value);
  #if LANG_CXX11
  void set_x(::std::string&& value);
  #endif
  void set_x(const char* value);
  void set_x(const char* value, size_t size);
  ::std::string* mutable_x();
  ::std::string* release_x();
  void set_allocated_x(::std::string* x);

  // optional string y = 2;
  bool has_y() const;
  void clear_y();
  static const int kYFieldNumber = 2;
  const ::std::string& y() const;
  void set_y(const ::std::string& value);
  #if LANG_CXX11
  void set_y(::std::string&& value);
  #endif
  void set_y(const char* value);
  void set_y(const char* value, size_t size);
  ::std::string* mutable_y();
  ::std::string* release_y();
  void set_allocated_y(::std::string* y);

  // @@protoc_insertion_point(class_scope:gps_data.LocationXY)
 private:
  void set_has_x();
  void clear_has_x();
  void set_has_y();
  void clear_has_y();

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::google::protobuf::internal::HasBits<1> _has_bits_;
  mutable int _cached_size_;
  ::google::protobuf::internal::ArenaStringPtr x_;
  ::google::protobuf::internal::ArenaStringPtr y_;
  friend struct ::protobuf_pbtest_2eproto::TableStruct;
  friend void ::protobuf_pbtest_2eproto::InitDefaultsgps_data_LocationXYImpl();
};
// -------------------------------------------------------------------

class gps_data : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:gps_data) */ {
 public:
  gps_data();
  virtual ~gps_data();

  gps_data(const gps_data& from);

  inline gps_data& operator=(const gps_data& from) {
    CopyFrom(from);
    return *this;
  }
  #if LANG_CXX11
  gps_data(gps_data&& from) noexcept
    : gps_data() {
    *this = ::std::move(from);
  }

  inline gps_data& operator=(gps_data&& from) noexcept {
    if (GetArenaNoVirtual() == from.GetArenaNoVirtual()) {
      if (this != &from) InternalSwap(&from);
    } else {
      CopyFrom(from);
    }
    return *this;
  }
  #endif
  inline const ::google::protobuf::UnknownFieldSet& unknown_fields() const {
    return _internal_metadata_.unknown_fields();
  }
  inline ::google::protobuf::UnknownFieldSet* mutable_unknown_fields() {
    return _internal_metadata_.mutable_unknown_fields();
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const gps_data& default_instance();

  static void InitAsDefaultInstance();  // FOR INTERNAL USE ONLY
  static inline const gps_data* internal_default_instance() {
    return reinterpret_cast<const gps_data*>(
               &_gps_data_default_instance_);
  }
  static PROTOBUF_CONSTEXPR int const kIndexInFileMessages =
    1;

  void Swap(gps_data* other);
  friend void swap(gps_data& a, gps_data& b) {
    a.Swap(&b);
  }

  // implements Message ----------------------------------------------

  inline gps_data* New() const PROTOBUF_FINAL { return New(NULL); }

  gps_data* New(::google::protobuf::Arena* arena) const PROTOBUF_FINAL;
  void CopyFrom(const ::google::protobuf::Message& from) PROTOBUF_FINAL;
  void MergeFrom(const ::google::protobuf::Message& from) PROTOBUF_FINAL;
  void CopyFrom(const gps_data& from);
  void MergeFrom(const gps_data& from);
  void Clear() PROTOBUF_FINAL;
  bool IsInitialized() const PROTOBUF_FINAL;

  size_t ByteSizeLong() const PROTOBUF_FINAL;
  bool MergePartialFromCodedStream(
      ::google::protobuf::io::CodedInputStream* input) PROTOBUF_FINAL;
  void SerializeWithCachedSizes(
      ::google::protobuf::io::CodedOutputStream* output) const PROTOBUF_FINAL;
  ::google::protobuf::uint8* InternalSerializeWithCachedSizesToArray(
      bool deterministic, ::google::protobuf::uint8* target) const PROTOBUF_FINAL;
  int GetCachedSize() const PROTOBUF_FINAL { return _cached_size_; }
  private:
  void SharedCtor();
  void SharedDtor();
  void SetCachedSize(int size) const PROTOBUF_FINAL;
  void InternalSwap(gps_data* other);
  private:
  inline ::google::protobuf::Arena* GetArenaNoVirtual() const {
    return NULL;
  }
  inline void* MaybeArenaPtr() const {
    return NULL;
  }
  public:

  ::google::protobuf::Metadata GetMetadata() const PROTOBUF_FINAL;

  // nested types ----------------------------------------------------

  typedef gps_data_LocationXY LocationXY;

  typedef gps_data_SatelliteType SatelliteType;
  static const SatelliteType DEFAULT =
    gps_data_SatelliteType_DEFAULT;
  static const SatelliteType GPS =
    gps_data_SatelliteType_GPS;
  static const SatelliteType GLONASS =
    gps_data_SatelliteType_GLONASS;
  static const SatelliteType GALILEO =
    gps_data_SatelliteType_GALILEO;
  static inline bool SatelliteType_IsValid(int value) {
    return gps_data_SatelliteType_IsValid(value);
  }
  static const SatelliteType SatelliteType_MIN =
    gps_data_SatelliteType_SatelliteType_MIN;
  static const SatelliteType SatelliteType_MAX =
    gps_data_SatelliteType_SatelliteType_MAX;
  static const int SatelliteType_ARRAYSIZE =
    gps_data_SatelliteType_SatelliteType_ARRAYSIZE;
  static inline const ::google::protobuf::EnumDescriptor*
  SatelliteType_descriptor() {
    return gps_data_SatelliteType_descriptor();
  }
  static inline const ::std::string& SatelliteType_Name(SatelliteType value) {
    return gps_data_SatelliteType_Name(value);
  }
  static inline bool SatelliteType_Parse(const ::std::string& name,
      SatelliteType* value) {
    return gps_data_SatelliteType_Parse(name, value);
  }

  // accessors -------------------------------------------------------

  // repeated int32 xys = 15;
  int xys_size() const;
  void clear_xys();
  static const int kXysFieldNumber = 15;
  ::google::protobuf::int32 xys(int index) const;
  void set_xys(int index, ::google::protobuf::int32 value);
  void add_xys(::google::protobuf::int32 value);
  const ::google::protobuf::RepeatedField< ::google::protobuf::int32 >&
      xys() const;
  ::google::protobuf::RepeatedField< ::google::protobuf::int32 >*
      mutable_xys();

  // optional string terminalId = 2;
  bool has_terminalid() const;
  void clear_terminalid();
  static const int kTerminalIdFieldNumber = 2;
  const ::std::string& terminalid() const;
  void set_terminalid(const ::std::string& value);
  #if LANG_CXX11
  void set_terminalid(::std::string&& value);
  #endif
  void set_terminalid(const char* value);
  void set_terminalid(const char* value, size_t size);
  ::std::string* mutable_terminalid();
  ::std::string* release_terminalid();
  void set_allocated_terminalid(::std::string* terminalid);

  // optional string dataTime = 3;
  bool has_datatime() const;
  void clear_datatime();
  static const int kDataTimeFieldNumber = 3;
  const ::std::string& datatime() const;
  void set_datatime(const ::std::string& value);
  #if LANG_CXX11
  void set_datatime(::std::string&& value);
  #endif
  void set_datatime(const char* value);
  void set_datatime(const char* value, size_t size);
  ::std::string* mutable_datatime();
  ::std::string* release_datatime();
  void set_allocated_datatime(::std::string* datatime);

  // optional .gps_data.LocationXY locXY = 13;
  bool has_locxy() const;
  void clear_locxy();
  static const int kLocXYFieldNumber = 13;
  const ::gps_data_LocationXY& locxy() const;
  ::gps_data_LocationXY* release_locxy();
  ::gps_data_LocationXY* mutable_locxy();
  void set_allocated_locxy(::gps_data_LocationXY* locxy);

  // optional int64 id = 1;
  bool has_id() const;
  void clear_id();
  static const int kIdFieldNumber = 1;
  ::google::protobuf::int64 id() const;
  void set_id(::google::protobuf::int64 value);

  // optional double lon = 4;
  bool has_lon() const;
  void clear_lon();
  static const int kLonFieldNumber = 4;
  double lon() const;
  void set_lon(double value);

  // optional double lat = 5;
  bool has_lat() const;
  void clear_lat();
  static const int kLatFieldNumber = 5;
  double lat() const;
  void set_lat(double value);

  // optional float speed = 6;
  bool has_speed() const;
  void clear_speed();
  static const int kSpeedFieldNumber = 6;
  float speed() const;
  void set_speed(float value);

  // optional int32 altitude = 7;
  bool has_altitude() const;
  void clear_altitude();
  static const int kAltitudeFieldNumber = 7;
  ::google::protobuf::int32 altitude() const;
  void set_altitude(::google::protobuf::int32 value);

  // optional int32 locType = 8;
  bool has_loctype() const;
  void clear_loctype();
  static const int kLocTypeFieldNumber = 8;
  ::google::protobuf::int32 loctype() const;
  void set_loctype(::google::protobuf::int32 value);

  // optional int32 gpsStatus = 9;
  bool has_gpsstatus() const;
  void clear_gpsstatus();
  static const int kGpsStatusFieldNumber = 9;
  ::google::protobuf::int32 gpsstatus() const;
  void set_gpsstatus(::google::protobuf::int32 value);

  // optional float direction = 10;
  bool has_direction() const;
  void clear_direction();
  static const int kDirectionFieldNumber = 10;
  float direction() const;
  void set_direction(float value);

  // optional int32 satellite = 11;
  bool has_satellite() const;
  void clear_satellite();
  static const int kSatelliteFieldNumber = 11;
  ::google::protobuf::int32 satellite() const;
  void set_satellite(::google::protobuf::int32 value);

  // optional int64 testField1 = 12;
  bool has_testfield1() const;
  void clear_testfield1();
  static const int kTestField1FieldNumber = 12;
  ::google::protobuf::int64 testfield1() const;
  void set_testfield1(::google::protobuf::int64 value);

  // required .gps_data.SatelliteType satelliteType = 14;
  bool has_satellitetype() const;
  void clear_satellitetype();
  static const int kSatelliteTypeFieldNumber = 14;
  ::gps_data_SatelliteType satellitetype() const;
  void set_satellitetype(::gps_data_SatelliteType value);

  // @@protoc_insertion_point(class_scope:gps_data)
 private:
  void set_has_id();
  void clear_has_id();
  void set_has_terminalid();
  void clear_has_terminalid();
  void set_has_datatime();
  void clear_has_datatime();
  void set_has_lon();
  void clear_has_lon();
  void set_has_lat();
  void clear_has_lat();
  void set_has_speed();
  void clear_has_speed();
  void set_has_altitude();
  void clear_has_altitude();
  void set_has_loctype();
  void clear_has_loctype();
  void set_has_gpsstatus();
  void clear_has_gpsstatus();
  void set_has_direction();
  void clear_has_direction();
  void set_has_satellite();
  void clear_has_satellite();
  void set_has_testfield1();
  void clear_has_testfield1();
  void set_has_locxy();
  void clear_has_locxy();
  void set_has_satellitetype();
  void clear_has_satellitetype();

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::google::protobuf::internal::HasBits<1> _has_bits_;
  mutable int _cached_size_;
  ::google::protobuf::RepeatedField< ::google::protobuf::int32 > xys_;
  ::google::protobuf::internal::ArenaStringPtr terminalid_;
  ::google::protobuf::internal::ArenaStringPtr datatime_;
  ::gps_data_LocationXY* locxy_;
  ::google::protobuf::int64 id_;
  double lon_;
  double lat_;
  float speed_;
  ::google::protobuf::int32 altitude_;
  ::google::protobuf::int32 loctype_;
  ::google::protobuf::int32 gpsstatus_;
  float direction_;
  ::google::protobuf::int32 satellite_;
  ::google::protobuf::int64 testfield1_;
  int satellitetype_;
  friend struct ::protobuf_pbtest_2eproto::TableStruct;
  friend void ::protobuf_pbtest_2eproto::InitDefaultsgps_dataImpl();
};
// ===================================================================


// ===================================================================

#ifdef __GNUC__
  #pragma GCC diagnostic push
  #pragma GCC diagnostic ignored "-Wstrict-aliasing"
#endif  // __GNUC__
// gps_data_LocationXY

// optional string x = 1;
inline bool gps_data_LocationXY::has_x() const {
  return (_has_bits_[0] & 0x00000001u) != 0;
}
inline void gps_data_LocationXY::set_has_x() {
  _has_bits_[0] |= 0x00000001u;
}
inline void gps_data_LocationXY::clear_has_x() {
  _has_bits_[0] &= ~0x00000001u;
}
inline void gps_data_LocationXY::clear_x() {
  x_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  clear_has_x();
}
inline const ::std::string& gps_data_LocationXY::x() const {
  // @@protoc_insertion_point(field_get:gps_data.LocationXY.x)
  return x_.GetNoArena();
}
inline void gps_data_LocationXY::set_x(const ::std::string& value) {
  set_has_x();
  x_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:gps_data.LocationXY.x)
}
#if LANG_CXX11
inline void gps_data_LocationXY::set_x(::std::string&& value) {
  set_has_x();
  x_.SetNoArena(
    &::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::move(value));
  // @@protoc_insertion_point(field_set_rvalue:gps_data.LocationXY.x)
}
#endif
inline void gps_data_LocationXY::set_x(const char* value) {
  GOOGLE_DCHECK(value != NULL);
  set_has_x();
  x_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:gps_data.LocationXY.x)
}
inline void gps_data_LocationXY::set_x(const char* value, size_t size) {
  set_has_x();
  x_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:gps_data.LocationXY.x)
}
inline ::std::string* gps_data_LocationXY::mutable_x() {
  set_has_x();
  // @@protoc_insertion_point(field_mutable:gps_data.LocationXY.x)
  return x_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* gps_data_LocationXY::release_x() {
  // @@protoc_insertion_point(field_release:gps_data.LocationXY.x)
  clear_has_x();
  return x_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void gps_data_LocationXY::set_allocated_x(::std::string* x) {
  if (x != NULL) {
    set_has_x();
  } else {
    clear_has_x();
  }
  x_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), x);
  // @@protoc_insertion_point(field_set_allocated:gps_data.LocationXY.x)
}

// optional string y = 2;
inline bool gps_data_LocationXY::has_y() const {
  return (_has_bits_[0] & 0x00000002u) != 0;
}
inline void gps_data_LocationXY::set_has_y() {
  _has_bits_[0] |= 0x00000002u;
}
inline void gps_data_LocationXY::clear_has_y() {
  _has_bits_[0] &= ~0x00000002u;
}
inline void gps_data_LocationXY::clear_y() {
  y_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  clear_has_y();
}
inline const ::std::string& gps_data_LocationXY::y() const {
  // @@protoc_insertion_point(field_get:gps_data.LocationXY.y)
  return y_.GetNoArena();
}
inline void gps_data_LocationXY::set_y(const ::std::string& value) {
  set_has_y();
  y_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:gps_data.LocationXY.y)
}
#if LANG_CXX11
inline void gps_data_LocationXY::set_y(::std::string&& value) {
  set_has_y();
  y_.SetNoArena(
    &::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::move(value));
  // @@protoc_insertion_point(field_set_rvalue:gps_data.LocationXY.y)
}
#endif
inline void gps_data_LocationXY::set_y(const char* value) {
  GOOGLE_DCHECK(value != NULL);
  set_has_y();
  y_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:gps_data.LocationXY.y)
}
inline void gps_data_LocationXY::set_y(const char* value, size_t size) {
  set_has_y();
  y_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:gps_data.LocationXY.y)
}
inline ::std::string* gps_data_LocationXY::mutable_y() {
  set_has_y();
  // @@protoc_insertion_point(field_mutable:gps_data.LocationXY.y)
  return y_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* gps_data_LocationXY::release_y() {
  // @@protoc_insertion_point(field_release:gps_data.LocationXY.y)
  clear_has_y();
  return y_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void gps_data_LocationXY::set_allocated_y(::std::string* y) {
  if (y != NULL) {
    set_has_y();
  } else {
    clear_has_y();
  }
  y_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), y);
  // @@protoc_insertion_point(field_set_allocated:gps_data.LocationXY.y)
}

// -------------------------------------------------------------------

// gps_data

// optional int64 id = 1;
inline bool gps_data::has_id() const {
  return (_has_bits_[0] & 0x00000008u) != 0;
}
inline void gps_data::set_has_id() {
  _has_bits_[0] |= 0x00000008u;
}
inline void gps_data::clear_has_id() {
  _has_bits_[0] &= ~0x00000008u;
}
inline void gps_data::clear_id() {
  id_ = GOOGLE_LONGLONG(0);
  clear_has_id();
}
inline ::google::protobuf::int64 gps_data::id() const {
  // @@protoc_insertion_point(field_get:gps_data.id)
  return id_;
}
inline void gps_data::set_id(::google::protobuf::int64 value) {
  set_has_id();
  id_ = value;
  // @@protoc_insertion_point(field_set:gps_data.id)
}

// optional string terminalId = 2;
inline bool gps_data::has_terminalid() const {
  return (_has_bits_[0] & 0x00000001u) != 0;
}
inline void gps_data::set_has_terminalid() {
  _has_bits_[0] |= 0x00000001u;
}
inline void gps_data::clear_has_terminalid() {
  _has_bits_[0] &= ~0x00000001u;
}
inline void gps_data::clear_terminalid() {
  terminalid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  clear_has_terminalid();
}
inline const ::std::string& gps_data::terminalid() const {
  // @@protoc_insertion_point(field_get:gps_data.terminalId)
  return terminalid_.GetNoArena();
}
inline void gps_data::set_terminalid(const ::std::string& value) {
  set_has_terminalid();
  terminalid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:gps_data.terminalId)
}
#if LANG_CXX11
inline void gps_data::set_terminalid(::std::string&& value) {
  set_has_terminalid();
  terminalid_.SetNoArena(
    &::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::move(value));
  // @@protoc_insertion_point(field_set_rvalue:gps_data.terminalId)
}
#endif
inline void gps_data::set_terminalid(const char* value) {
  GOOGLE_DCHECK(value != NULL);
  set_has_terminalid();
  terminalid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:gps_data.terminalId)
}
inline void gps_data::set_terminalid(const char* value, size_t size) {
  set_has_terminalid();
  terminalid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:gps_data.terminalId)
}
inline ::std::string* gps_data::mutable_terminalid() {
  set_has_terminalid();
  // @@protoc_insertion_point(field_mutable:gps_data.terminalId)
  return terminalid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* gps_data::release_terminalid() {
  // @@protoc_insertion_point(field_release:gps_data.terminalId)
  clear_has_terminalid();
  return terminalid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void gps_data::set_allocated_terminalid(::std::string* terminalid) {
  if (terminalid != NULL) {
    set_has_terminalid();
  } else {
    clear_has_terminalid();
  }
  terminalid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), terminalid);
  // @@protoc_insertion_point(field_set_allocated:gps_data.terminalId)
}

// optional string dataTime = 3;
inline bool gps_data::has_datatime() const {
  return (_has_bits_[0] & 0x00000002u) != 0;
}
inline void gps_data::set_has_datatime() {
  _has_bits_[0] |= 0x00000002u;
}
inline void gps_data::clear_has_datatime() {
  _has_bits_[0] &= ~0x00000002u;
}
inline void gps_data::clear_datatime() {
  datatime_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
  clear_has_datatime();
}
inline const ::std::string& gps_data::datatime() const {
  // @@protoc_insertion_point(field_get:gps_data.dataTime)
  return datatime_.GetNoArena();
}
inline void gps_data::set_datatime(const ::std::string& value) {
  set_has_datatime();
  datatime_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:gps_data.dataTime)
}
#if LANG_CXX11
inline void gps_data::set_datatime(::std::string&& value) {
  set_has_datatime();
  datatime_.SetNoArena(
    &::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::move(value));
  // @@protoc_insertion_point(field_set_rvalue:gps_data.dataTime)
}
#endif
inline void gps_data::set_datatime(const char* value) {
  GOOGLE_DCHECK(value != NULL);
  set_has_datatime();
  datatime_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:gps_data.dataTime)
}
inline void gps_data::set_datatime(const char* value, size_t size) {
  set_has_datatime();
  datatime_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:gps_data.dataTime)
}
inline ::std::string* gps_data::mutable_datatime() {
  set_has_datatime();
  // @@protoc_insertion_point(field_mutable:gps_data.dataTime)
  return datatime_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* gps_data::release_datatime() {
  // @@protoc_insertion_point(field_release:gps_data.dataTime)
  clear_has_datatime();
  return datatime_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void gps_data::set_allocated_datatime(::std::string* datatime) {
  if (datatime != NULL) {
    set_has_datatime();
  } else {
    clear_has_datatime();
  }
  datatime_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), datatime);
  // @@protoc_insertion_point(field_set_allocated:gps_data.dataTime)
}

// optional double lon = 4;
inline bool gps_data::has_lon() const {
  return (_has_bits_[0] & 0x00000010u) != 0;
}
inline void gps_data::set_has_lon() {
  _has_bits_[0] |= 0x00000010u;
}
inline void gps_data::clear_has_lon() {
  _has_bits_[0] &= ~0x00000010u;
}
inline void gps_data::clear_lon() {
  lon_ = 0;
  clear_has_lon();
}
inline double gps_data::lon() const {
  // @@protoc_insertion_point(field_get:gps_data.lon)
  return lon_;
}
inline void gps_data::set_lon(double value) {
  set_has_lon();
  lon_ = value;
  // @@protoc_insertion_point(field_set:gps_data.lon)
}

// optional double lat = 5;
inline bool gps_data::has_lat() const {
  return (_has_bits_[0] & 0x00000020u) != 0;
}
inline void gps_data::set_has_lat() {
  _has_bits_[0] |= 0x00000020u;
}
inline void gps_data::clear_has_lat() {
  _has_bits_[0] &= ~0x00000020u;
}
inline void gps_data::clear_lat() {
  lat_ = 0;
  clear_has_lat();
}
inline double gps_data::lat() const {
  // @@protoc_insertion_point(field_get:gps_data.lat)
  return lat_;
}
inline void gps_data::set_lat(double value) {
  set_has_lat();
  lat_ = value;
  // @@protoc_insertion_point(field_set:gps_data.lat)
}

// optional float speed = 6;
inline bool gps_data::has_speed() const {
  return (_has_bits_[0] & 0x00000040u) != 0;
}
inline void gps_data::set_has_speed() {
  _has_bits_[0] |= 0x00000040u;
}
inline void gps_data::clear_has_speed() {
  _has_bits_[0] &= ~0x00000040u;
}
inline void gps_data::clear_speed() {
  speed_ = 0;
  clear_has_speed();
}
inline float gps_data::speed() const {
  // @@protoc_insertion_point(field_get:gps_data.speed)
  return speed_;
}
inline void gps_data::set_speed(float value) {
  set_has_speed();
  speed_ = value;
  // @@protoc_insertion_point(field_set:gps_data.speed)
}

// optional int32 altitude = 7;
inline bool gps_data::has_altitude() const {
  return (_has_bits_[0] & 0x00000080u) != 0;
}
inline void gps_data::set_has_altitude() {
  _has_bits_[0] |= 0x00000080u;
}
inline void gps_data::clear_has_altitude() {
  _has_bits_[0] &= ~0x00000080u;
}
inline void gps_data::clear_altitude() {
  altitude_ = 0;
  clear_has_altitude();
}
inline ::google::protobuf::int32 gps_data::altitude() const {
  // @@protoc_insertion_point(field_get:gps_data.altitude)
  return altitude_;
}
inline void gps_data::set_altitude(::google::protobuf::int32 value) {
  set_has_altitude();
  altitude_ = value;
  // @@protoc_insertion_point(field_set:gps_data.altitude)
}

// optional int32 locType = 8;
inline bool gps_data::has_loctype() const {
  return (_has_bits_[0] & 0x00000100u) != 0;
}
inline void gps_data::set_has_loctype() {
  _has_bits_[0] |= 0x00000100u;
}
inline void gps_data::clear_has_loctype() {
  _has_bits_[0] &= ~0x00000100u;
}
inline void gps_data::clear_loctype() {
  loctype_ = 0;
  clear_has_loctype();
}
inline ::google::protobuf::int32 gps_data::loctype() const {
  // @@protoc_insertion_point(field_get:gps_data.locType)
  return loctype_;
}
inline void gps_data::set_loctype(::google::protobuf::int32 value) {
  set_has_loctype();
  loctype_ = value;
  // @@protoc_insertion_point(field_set:gps_data.locType)
}

// optional int32 gpsStatus = 9;
inline bool gps_data::has_gpsstatus() const {
  return (_has_bits_[0] & 0x00000200u) != 0;
}
inline void gps_data::set_has_gpsstatus() {
  _has_bits_[0] |= 0x00000200u;
}
inline void gps_data::clear_has_gpsstatus() {
  _has_bits_[0] &= ~0x00000200u;
}
inline void gps_data::clear_gpsstatus() {
  gpsstatus_ = 0;
  clear_has_gpsstatus();
}
inline ::google::protobuf::int32 gps_data::gpsstatus() const {
  // @@protoc_insertion_point(field_get:gps_data.gpsStatus)
  return gpsstatus_;
}
inline void gps_data::set_gpsstatus(::google::protobuf::int32 value) {
  set_has_gpsstatus();
  gpsstatus_ = value;
  // @@protoc_insertion_point(field_set:gps_data.gpsStatus)
}

// optional float direction = 10;
inline bool gps_data::has_direction() const {
  return (_has_bits_[0] & 0x00000400u) != 0;
}
inline void gps_data::set_has_direction() {
  _has_bits_[0] |= 0x00000400u;
}
inline void gps_data::clear_has_direction() {
  _has_bits_[0] &= ~0x00000400u;
}
inline void gps_data::clear_direction() {
  direction_ = 0;
  clear_has_direction();
}
inline float gps_data::direction() const {
  // @@protoc_insertion_point(field_get:gps_data.direction)
  return direction_;
}
inline void gps_data::set_direction(float value) {
  set_has_direction();
  direction_ = value;
  // @@protoc_insertion_point(field_set:gps_data.direction)
}

// optional int32 satellite = 11;
inline bool gps_data::has_satellite() const {
  return (_has_bits_[0] & 0x00000800u) != 0;
}
inline void gps_data::set_has_satellite() {
  _has_bits_[0] |= 0x00000800u;
}
inline void gps_data::clear_has_satellite() {
  _has_bits_[0] &= ~0x00000800u;
}
inline void gps_data::clear_satellite() {
  satellite_ = 0;
  clear_has_satellite();
}
inline ::google::protobuf::int32 gps_data::satellite() const {
  // @@protoc_insertion_point(field_get:gps_data.satellite)
  return satellite_;
}
inline void gps_data::set_satellite(::google::protobuf::int32 value) {
  set_has_satellite();
  satellite_ = value;
  // @@protoc_insertion_point(field_set:gps_data.satellite)
}

// optional int64 testField1 = 12;
inline bool gps_data::has_testfield1() const {
  return (_has_bits_[0] & 0x00001000u) != 0;
}
inline void gps_data::set_has_testfield1() {
  _has_bits_[0] |= 0x00001000u;
}
inline void gps_data::clear_has_testfield1() {
  _has_bits_[0] &= ~0x00001000u;
}
inline void gps_data::clear_testfield1() {
  testfield1_ = GOOGLE_LONGLONG(0);
  clear_has_testfield1();
}
inline ::google::protobuf::int64 gps_data::testfield1() const {
  // @@protoc_insertion_point(field_get:gps_data.testField1)
  return testfield1_;
}
inline void gps_data::set_testfield1(::google::protobuf::int64 value) {
  set_has_testfield1();
  testfield1_ = value;
  // @@protoc_insertion_point(field_set:gps_data.testField1)
}

// optional .gps_data.LocationXY locXY = 13;
inline bool gps_data::has_locxy() const {
  return (_has_bits_[0] & 0x00000004u) != 0;
}
inline void gps_data::set_has_locxy() {
  _has_bits_[0] |= 0x00000004u;
}
inline void gps_data::clear_has_locxy() {
  _has_bits_[0] &= ~0x00000004u;
}
inline void gps_data::clear_locxy() {
  if (locxy_ != NULL) locxy_->Clear();
  clear_has_locxy();
}
inline const ::gps_data_LocationXY& gps_data::locxy() const {
  const ::gps_data_LocationXY* p = locxy_;
  // @@protoc_insertion_point(field_get:gps_data.locXY)
  return p != NULL ? *p : *reinterpret_cast<const ::gps_data_LocationXY*>(
      &::_gps_data_LocationXY_default_instance_);
}
inline ::gps_data_LocationXY* gps_data::release_locxy() {
  // @@protoc_insertion_point(field_release:gps_data.locXY)
  clear_has_locxy();
  ::gps_data_LocationXY* temp = locxy_;
  locxy_ = NULL;
  return temp;
}
inline ::gps_data_LocationXY* gps_data::mutable_locxy() {
  set_has_locxy();
  if (locxy_ == NULL) {
    locxy_ = new ::gps_data_LocationXY;
  }
  // @@protoc_insertion_point(field_mutable:gps_data.locXY)
  return locxy_;
}
inline void gps_data::set_allocated_locxy(::gps_data_LocationXY* locxy) {
  ::google::protobuf::Arena* message_arena = GetArenaNoVirtual();
  if (message_arena == NULL) {
    delete locxy_;
  }
  if (locxy) {
    ::google::protobuf::Arena* submessage_arena = NULL;
    if (message_arena != submessage_arena) {
      locxy = ::google::protobuf::internal::GetOwnedMessage(
          message_arena, locxy, submessage_arena);
    }
    set_has_locxy();
  } else {
    clear_has_locxy();
  }
  locxy_ = locxy;
  // @@protoc_insertion_point(field_set_allocated:gps_data.locXY)
}

// required .gps_data.SatelliteType satelliteType = 14;
inline bool gps_data::has_satellitetype() const {
  return (_has_bits_[0] & 0x00002000u) != 0;
}
inline void gps_data::set_has_satellitetype() {
  _has_bits_[0] |= 0x00002000u;
}
inline void gps_data::clear_has_satellitetype() {
  _has_bits_[0] &= ~0x00002000u;
}
inline void gps_data::clear_satellitetype() {
  satellitetype_ = 0;
  clear_has_satellitetype();
}
inline ::gps_data_SatelliteType gps_data::satellitetype() const {
  // @@protoc_insertion_point(field_get:gps_data.satelliteType)
  return static_cast< ::gps_data_SatelliteType >(satellitetype_);
}
inline void gps_data::set_satellitetype(::gps_data_SatelliteType value) {
  assert(::gps_data_SatelliteType_IsValid(value));
  set_has_satellitetype();
  satellitetype_ = value;
  // @@protoc_insertion_point(field_set:gps_data.satelliteType)
}

// repeated int32 xys = 15;
inline int gps_data::xys_size() const {
  return xys_.size();
}
inline void gps_data::clear_xys() {
  xys_.Clear();
}
inline ::google::protobuf::int32 gps_data::xys(int index) const {
  // @@protoc_insertion_point(field_get:gps_data.xys)
  return xys_.Get(index);
}
inline void gps_data::set_xys(int index, ::google::protobuf::int32 value) {
  xys_.Set(index, value);
  // @@protoc_insertion_point(field_set:gps_data.xys)
}
inline void gps_data::add_xys(::google::protobuf::int32 value) {
  xys_.Add(value);
  // @@protoc_insertion_point(field_add:gps_data.xys)
}
inline const ::google::protobuf::RepeatedField< ::google::protobuf::int32 >&
gps_data::xys() const {
  // @@protoc_insertion_point(field_list:gps_data.xys)
  return xys_;
}
inline ::google::protobuf::RepeatedField< ::google::protobuf::int32 >*
gps_data::mutable_xys() {
  // @@protoc_insertion_point(field_mutable_list:gps_data.xys)
  return &xys_;
}

#ifdef __GNUC__
  #pragma GCC diagnostic pop
#endif  // __GNUC__
// -------------------------------------------------------------------


// @@protoc_insertion_point(namespace_scope)


namespace google {
namespace protobuf {

template <> struct is_proto_enum< ::gps_data_SatelliteType> : ::google::protobuf::internal::true_type {};
template <>
inline const EnumDescriptor* GetEnumDescriptor< ::gps_data_SatelliteType>() {
  return ::gps_data_SatelliteType_descriptor();
}

}  // namespace protobuf
}  // namespace google

// @@protoc_insertion_point(global_scope)

#endif  // PROTOBUF_pbtest_2eproto__INCLUDED
