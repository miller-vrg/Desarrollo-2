import 'dart:io';
import 'Producto.dart';

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

menu(String vendedor){

print("\n\n");
print(".::: Bienvenido ${vendedor} a ${_nombre} :::.");
print("+----------- Menu --------------+");
print("|1 | Registrar producto         |");
print("|2 | Registrar vendedor         |");
print("|3 | Buscar                     |");
print("+-------------------------------+");
stdout.write("Respuesta: ");
var x = stdin.readLineSync().toString();
var opc = int.parse(x);

if ( opc == 1 ){

print("------- Registrar Producto ----------");
stdout.write("Nombre del producto: ");
var x = stdin.readLineSync().toString();
var nombre = x;

stdout.write("Categoria: ");
x = stdin.readLineSync().toString();
var categoria = x;

stdout.write("ID: ");
x = stdin.readLineSync().toString();
var id = int.parse(x);

stdout.write("Garantia: ");
x = stdin.readLineSync().toString();
var garantia = int.parse(x);

stdout.write("Precio: ");
x = stdin.readLineSync().toString();
var precio = double.parse(x);

stdout.write("Iva: ");
x = stdin.readLineSync().toString();
var iva = double.parse(x);

Producto producto = new Producto(nombre,id,categoria,garantia,precio,iva);

}

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