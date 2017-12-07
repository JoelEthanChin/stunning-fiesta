int buzzerPin = 9;
int buttonPin = 3;
int val;

void setup(){
  
  pinMode(buzzerPin, OUTPUT);
  pinMode(buttonPin, INPUT);
  
}

void loop(){
  
  if(digitalRead(buttonPin) == HIGH){
    
    tone(buzzerPin, 1000);
    
  }
  else{
    noTone(buzzerPin);
  }
  
}
