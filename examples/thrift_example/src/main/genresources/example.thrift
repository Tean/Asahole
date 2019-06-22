namespace java com.netteans.thrift.java.example.domain
namespace go com.netteans.thrift.go.example.domain

struct ExpDomain{
    1: optional bool domainBool;
    2: required i8 domainByte;
    3: optional i16 domainI16;
    4: optional i32 domainI32;
    5: optional i64 domainI64;
    6: optional double domainDouble;
    7: optional string domainString;
    8: optional binary domainBinary;
    9: optional ExpStruct domainStruct;
}

struct ExpStruct {
    1: map<string, User> usermap,
    2: set<i32> intset,
    3: list<double> doublelist
}

struct User {
    1: string name,
    2: required i16 number
}

service HelloService {
    i32 question(1:string param)
    ExpDomain answer()
}