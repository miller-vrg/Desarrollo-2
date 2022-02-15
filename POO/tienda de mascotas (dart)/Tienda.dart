import 'dart:io';

class Tienda{

String _nombre;
String _direccion;
String _email;
int _telefono;
int _id;
int _nit;

Tienda(this._nombre,this._direccion,this._email,this._telefono,this._id,this._nit);

//-----------------------------------
//          Metodos
//-----------------------------------

int menu(){

print("\n\n");
print(".::: Bienvenido a ${_nombre} :::.");
print("+----------- Menu --------------+");
print("|1 | vender                     |");
print("|2 | Registrar producto         |");
print("|3 | Registrar vendedor         |");
print("|4 | Buscar                     |");
print("+-------------------------------+");
stdout.write("Respuesta: ");
var x = stdin.readLineSync().toString();
return int.parse(x);
}

//-----------------------------------
//          Encapculamiento
//-----------------------------------

set id(int id){
  _id = id;
}

int get id => _id;

set nit( int nit ){
_nit = nit;
}

int get nit => _nit;

set telefono(int telefono){
  _telefono = telefono;
}

int get telefono => _telefono;

set nombre( String nombre ){
_nombre = nombre;
}

String get nombre => _nombre;

set email(String email){
  _email = email;
}

String get email => _email;

set direccion( String direccion ){
_direccion = direccion;
}

String get direccion => _direccion;

}