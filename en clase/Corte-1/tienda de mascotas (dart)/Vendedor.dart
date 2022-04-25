class Vendedor{

  int _cc;
  String _nombre, _apellido, _genero;
  
  Vendedor(this._nombre,this._apellido,this._cc,this._genero);

//-----------------------------------
//          Encapculamiento
//-----------------------------------

set nombre(String nombre){
  _nombre = nombre;
}

String get nombre => _nombre;

set apellido(String apellido){
  _apellido = apellido;
}

String get apellido => _apellido;

set genero (String genero){
  _genero = genero;
}

String get genero => _genero;

set cc(int cc){
  _cc = cc;
}

int get cc => _cc;
}