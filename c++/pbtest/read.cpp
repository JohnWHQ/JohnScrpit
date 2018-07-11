#include <iostream>
#include <fstream>
#include "pbtest.pb.h"
using namespace std;

 int main() {

  gps_data msg1;
  fstream input("./binaryOutput", ios::in | ios::binary); 
  if (!msg1.ParseFromIstream(&input)) { 
      cerr << "Failed to parse address book." << endl; 
      return -1; 
  }
  cout << msg1.lon() << endl;
  cout << msg1.lat() << endl;

  cout << "terminalId " << msg1.terminalid() << endl;
  cout << "dataTime   " << msg1.datatime()   << endl;
  cout << "lon        " << msg1.lon()        << endl;
  cout << "lat        " << msg1.lat()        << endl;
  cout << "speed      " << msg1.speed()      << endl;
  cout << "altitude   " << msg1.altitude()   << endl;
  cout << "locType    " << msg1.loctype()    << endl;
  cout << "gpsStatus  " << msg1.gpsstatus()  << endl;
  cout << "direction  " << msg1.direction()  << endl;
  cout << "satellite  " << msg1.satellite()  << endl;
  cout << "testField1 " << msg1.testfield1() << endl;
  cout << "SatelliteType " << msg1.satellitetype() << endl;
  return 0;
 }
