/*
Instituo Tecnologico de Costa Rica
CE1103 Algoritmos y Estructuras de Datos I
Primer proyecto Connect-Dots
Implementacion controlador
Autor: Fabian Gutierrez Jimenez
Profesor: Luis Alonso Barboza Artavia
*/

//se incluye la biblioteca keyboard
#include <Keyboard.h> 

//se asignan los pines de los botones del Arduino Pro Micro
void setup() {
  pinMode(2, INPUT_PULLUP);
  pinMode(3, INPUT_PULLUP);
  pinMode(4, INPUT_PULLUP);
  pinMode(5, INPUT_PULLUP);
  pinMode(6, INPUT_PULLUP);
}

//Se asigna a cada boton la tecla que se quiere emular con los botones
void loop() {
  //Al pin 2 se le asigna la tecla Enter
  if (digitalRead(2) == LOW) {
    Keyboard.write(KEY_RETURN);
    delay(200);
  }
  //Al pin 3 se le asigna la tecla flecha derecha
  if (digitalRead(3) == LOW) {
    Keyboard.write(KEY_RIGHT_ARROW);
    delay(200);  
  }
  //Al pin 4 se le asigna la tecla flecha arriba
  if (digitalRead(4) == LOW) {
    Keyboard.write(KEY_UP_ARROW);
    delay(200);
  }
  //Al pin 5 se le asigna la tecla flecha izquierda
  if (digitalRead(5) == LOW) {
    Keyboard.write(KEY_LEFT_ARROW);
    delay(200);
  }
  //Al pin 6 se le asigna la tecla flecha abajo
  if (digitalRead(6) == LOW) {
    Keyboard.write(KEY_DOWN_ARROW);
    delay(200);
  }
}
