import 'dart:io';
import 'Tienda.dart';
import 'Vendedor.dart';

void main(){

print("------- Registrar tienda ----------");
stdout.write("Nombre de la tienda: ");
var x = stdin.readLineSync().toString();
var nombre = x;

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

Tienda t = new Tienda(nombre,direccion,email,telefono,id,nit);
nombre = "";

print("------- Registrar vendedor ----------");
stdout.write("Nombres del vendedor: ");
x = stdin.readLineSync().toString();
var nombreVendedor = x;

stdout.write("Apellidos del vendedor: ");
x = stdin.readLineSync().toString();
var apellido = x;

stdout.write("Numero de CC: ");
x = stdin.readLineSync().toString();
var cc = int.parse(x);

print("---------- Genero ---------");
print("|1| Masculinp");
print("|2| Femenino");
print("---------------------------");
stdout.write("Respuesta: ");
x = stdin.readLineSync().toString();
var pc = int.parse(x);
var genero = ( x == 1 )?"Masculino":"Femenino";

t.menu(nombreVendedor);
Vendedor vendedor = new Vendedor(nombreVendedor,apellido,cc,genero);
}