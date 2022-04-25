import 'Tienda.dart';

class Comprar {
  double _valor;
  int _idCompra;
  String _fechaCompra;
  double _descuento;
  double _iva;

  Comprar(this._valor, this._idCompra, this._fechaCompra, this._descuento,
      this._iva);

//-----------------------------------
//         Encapculamiento          |
//-----------------------------------

  set valor(double valor) {
    _valor = valor;
  }

  double get valor => _valor;

  set idCompra(int idCompra){
    _idCompra = idCompra;
  }

  int get idCompra => _idCompra;

  set fechaCompra(String fechaCompra){
    _fechaCompra = fechaCompra;
  }
  
  String get fechaCompra => _fechaCompra;

  set descuento(double descuento){
    _descuento = descuento;
  }

  double get descuento => _descuento;

  set iva (double iva){
    _iva = iva;
  }

  double get iva => _iva;
}
