#include <SFE_BMP180.h>
#include <Wire.h>

SFE_BMP180 pressure;

void setup()
{
  Serial.begin(9600);
  pressure.begin();
}

//void send_data(double data, String key)
//{
//  String data_string = String(data);
//  int data_length = data_string.length() + 1; // прибавляем к размеру пакета 1 байт, чтобы разместить ключ
//  byte data_preambula[] = {data_length};
//  
//  Serial.write(data_preambula, sizeof(data_preambula));  // передаём 1 байт, значение которого - размер следующего пакета с данными (температура или давление)
//  delay(50);
//  Serial.print(key + data_string); // каждый символ как отдельный байт, вместе представляют собой пакет с данными (ключ + значение)
//  delay(50);  
//}

void send_data(double data, String key)
{
  String data_string = String(data);
  int data_length = data_string.length(); // прибавляем к размеру пакета 1 байт, чтобы разместить ключ
  byte data_preambula[] = {data_length};
  
  Serial.write(data_preambula, sizeof(data_preambula));  // передаём 1 байт, значение которого - размер следующего пакета с данными (температура или давление)
  delay(50);
  Serial.print(data_string); // каждый символ как отдельный байт, вместе представляют собой пакет с данными (ключ + значение)
  delay(50);  
}

void loop()
{
  char status;
  double T,P;
  status = pressure.startTemperature();
  if (status != 0)
  {
    delay(status);
    status = pressure.getTemperature(T);
    if (status != 0)
    {
      status = pressure.startPressure(3);
      if (status != 0)
      {
        delay(status);
        status = pressure.getPressure(P,T);
        if (status != 0)
        {
          // key string may be consist of only ONE CHARACTER symbol!
          send_data(T, "T"); // передаём значение и ключ
          send_data(P*0.75, "P");
        }
        else Serial.println("error retrieving pressure measurement 1\n");
      }
      else Serial.println("error starting pressure measurement 2\n");
    }
    else Serial.println("error retrieving temperature measurement 3\n");
  }
  else Serial.println("error starting temperature measurement 4\n");
  
  delay(720000); // PAUSE
}
