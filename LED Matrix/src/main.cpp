#include "Maps.h"

void setup(){
  lc.shutdown(0,false);       //The MAX72XX is in power-saving mode on startup
  lc.setIntensity(0,5);      // Set the brightness to maximum value
  lc.clearDisplay(0);         // and clear the display

  Wire.begin(4); // Join I2C bus with adress #4
  Wire.onReceive(recieveEvent); // Send command 
  Serial.begin(9600);
}

void loop(){
  printByte(ledImages[0]);

  delay(1000);
}

void recieveEvent(int i){
  int icon = Wire.read(); // Recieve the icon for state (0, 1, or 2)
  printByte(ledImages[icon]); // Send along icon to print image
}
