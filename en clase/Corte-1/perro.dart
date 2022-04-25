
class perro{

var color = "", nombre = "";
int edad = 0;
double altura = 0.0;


perro({int edad: 0, double altura: 0.0 ,String nombre: "", String color: ""}){

this.edad = edad;
this.nombre = nombre;
this.altura = altura;
this.color = color;

}

void ladrar(){
print("guao");
}

void comer(){
print("rrrrr");
}

void correr(){
print("");

}

void dormir (){
print("zzz,zzz");
}

}

void main(){

perro p = new perro(edad: 10, altura: 1.0, color: "blanco", nombre: "Mia");
p.ladrar();
perro(edad: 12, altura: 1.5, color: "cafe", nombre: "Kira").ladrar();
}