class Cliente{
String _nombre,_apellido,_genero,_direccion,_email;
int _cc;

Cliente(this._nombre,this._apellido,this._cc,this._genero,this._email,this._direccion);

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

set cc(int cc){
  _cc = cc;
}

int get cc => _cc;

set genero (String genero){
  _genero = genero;
}

String get genero => _genero;

set email(String email){
  _email = email;
}

String get email => _email;

set direccion( String direccion ){
_direccion = direccion;
}

String get direccion => _direccion;
}