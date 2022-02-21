class Producto{
String _nombre,_categoria;
int _id,_garantia;
double _precio,_iva;

Producto(this._nombre,this._id,this._categoria,this._garantia,this._precio,this._iva);

//-----------------------------------
//          Metodos
//-----------------------------------


//-----------------------------------
//          Encapculamiento
//-----------------------------------

set nombre(String nombre){
  _nombre = nombre;
}

String get nombre => _nombre;

set id(int id){
  _id = id;
}

int get id => _id;

set categoria(String categoria){
  _categoria = categoria;
}

String get categoria => _categoria;

set garantia(int garantia){
  _garantia = garantia;
}

int get garantia => _garantia;

set precio(double precio){
  _precio = precio;
}

double get precio => _precio;

set iva(double iva){
  _iva = iva;
}

double get iva => _iva;
}