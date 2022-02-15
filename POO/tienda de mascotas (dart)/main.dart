import 'dart:io';
import 'Tienda.dart';

void main(){

print("------- Registrar tienda ----------");
stdout.write("Nombre de la tienda: ");
var x = stdin.readLineSync().toString();
var nombreTienda = x;

stdout.write("Diección: ");
x = stdin.readLineSync().toString();
var direccion = x;

stdout.write("Email: ");
x = stdin.readLineSync().toString();
var email = x;

stdout.write("Telefono: ");
x = stdin.readLineSync().toString();
var telefono = int.parse(x);

stdout.write("ID: ");
x = stdin.readLineSync().toString();
var id = int.parse(x);

stdout.write("NIT: ");
x = stdin.readLineSync().toString();
var nit = int.parse(x);

Tienda t = new Tienda(nombreTienda,direccion,email,telefono,id,nit);

t.menu();

}