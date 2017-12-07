int touchPin = 11;
int buzzerPin = 9;

char LCD_ON = 'L';
char LCD_OFF = 'N';

boolean buzzerState;
boolean touchState;

int buttonState = 0;

int offButtonPin = 3;

int alarmON, pressed = 0;

//Set up code, used to initialize all pin-ports and their respective role,
// setup will run once.
void setup() {
 
 Serial.begin(9600); // set up Serial library at 9600 bps
 
 pinMode(buzzerPin, OUTPUT);
 pinMode(touchPin, INPUT);
 pinMode (offButtonPin, OUTPUT);
 
 //boolean alarmOn = Serial.read();
 
}

void loop() {
  
  if (Serial.available() > 0){
    
    alarmON = Serial.read();
    if (alarmON == '1'){ //if false skip to second if statement.
    
      while (digitalRead(offButtonPin) == LOW){
        toggleBuzzer();
      }
      
      turnAlarmOFF();
    }
  } 
  
  if(digitalRead(touchPin) == HIGH && pressed % 2 == 0) {
    pressed++;
    sendData(LCD_ON);
    delay(1000);
  }
  
  if(digitalRead(touchPin) == HIGH && pressed % 2 == 1) {
    pressed++;
    sendData(LCD_OFF);
    delay(1000);
  }
}

//serialEvent()

/* Method used to sound the active Buzzer, uses built-in libraries, 
 * such as Tone and noTone and delays to make an alarm-clock noise. 
 */
void toggleBuzzer(){
  tone(buzzerPin, 1000);
  delay(500);
  noTone(buzzerPin);
  delay(500);
  
}

/* Method used to turn off the active buzzer off, this method 
 * will be called when a button is pressed.
 */
void turnAlarmOFF(){
  noTone(buzzerPin);
  alarmON = 0;
}

//method used to send information to the main database.
void sendData(char s){
  Serial.println(s);    
}



