#include<fstream>
#include<iostream>
using namespace std;

int main(){
   ofstream outfile;
   outfile.open("test_write", ios::out | ios::app);
   string s = "hhhhhhhhhhh";
   outfile << s << endl;
   outfile.close();
}
