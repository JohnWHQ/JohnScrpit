#include <iostream>  
#include <fstream> 
#include "pbtest.pb.h"
using namespace std;
 
 int main() 
 { 
   
  gps_data msg1; 
  msg1.set_id(101);
//  msg1.set_satellitetype(1);
  string s = "test";
  msg1.set_terminalid(s);
  msg1.set_lon(123.123);
  msg1.set_lat(321.321); 
  // Write the new address book back to disk. 
  fstream output("./log123", ios::out | ios::trunc | ios::binary); 
         
  if (!msg1.SerializeToOstream(&output)) { 
      cerr << "Failed to write msg." << endl; 
      return -1; 
  }         
  return 0; 
 }
