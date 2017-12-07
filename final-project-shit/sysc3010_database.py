#SYSC3010 Python Database script
#This class will control inserting information to the database as well as retrieving.
#The database will store alarms dictated from the user via an android app to control an alarm clock running on a RPi

import urllib, sys, socket, time, serial

def init_Serial():
    global ser
    
    try:
        ser = serial.Serial('/dev/ttyACM0', 9600)
    except IOError:
        printf('ACM0 failed, proceeding with next port')
    
    try:
        ser = serial.Serial('/dev/ttyACM1', 9600)
    except IOError:
        printf('ACM1 failed, proceeding with next port')

    try:
        ser = serial.Serial('/dev/ttyACM2', 9600)
    except IOError:
        printf('ACM3 failed, proceeding with next port')

    try:
        ser = serial.Serial('/dev/ttyACM0', 9600)
    except IOError:
        printf('All ports failed, PLS CONNECT THE ARDUINO, ty :3')
    
def retCountryCity(weatInfo):
    return weatInfo[1:].split(delim)

def retAlarm(alrmInfo):
    return alrmInfo.split(delim)

def sendHigh():

    ser.write('1')

def receive():
    
    ser.read()

def main():

    ser
    delim = '.' #string delimiter

    init_Serial()

    #create socket access

    #ss socket to send information
    ss = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    portS = int(sys.argv[1]) #Specify port # during execution
    server_addressS = ('localhost', portS)

    #sr socket to recieve information
    sr = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    portR = int(sys.argv[2]) #Specify port # during execution
    server_addressR = ('10.0.0.', portR)
    sr.bind(server_addressR)

    #sa socket to send confirmation to android
    sa = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    portA = int(sys.argv[3]) #Specify port # during execution
    server_addressA = ("192.168.0.101", portA)
    
    try:
        country = ""
        city = ""
        alarm = ""
        isPM = ""
        isRecieved = ""
        
        #recieve from Android
        while (country == ""):
            packet1 = sr.recvfrom(1024) #recieve country
            country = packet1[0]
            print(country)
            isRecieved = "LOL"    
            sa.sendto(isRecieved.encode('utf-8'), server_addressA)
            isRecieved = ""
            
        while (city == ""):    
            packet2 = sr.recvfrom(1024) #recieve city
            city = packet2[0]
            print(city)
            isRecieved = "LOL"
            sa.sendto(isRecieved.encode('utf-8'), server_addressA)
            isRecieved = ""
            
        while(alarm == ""):
            packet3 = sr.recvfrom(1024) #recieve alarm
            alarm = packet3[0]
            print(alarm)
            isRecieved = "LOL"
            sa.sendto(isRecieved.encode('utf-8'), server_addressA)
            isRecieved = ""
            
        while(isPM == ""):
            packet4 = sr.recvfrom(1024) #recieve AM/PM
            isPM = packet4[0]
            print(isPM)
            isRecieved = "LOL"
            sa.sendto(isRecieved.encode('utf-8'), server_addressA)
            isRecieved = ""
            
        print("Packets Recieved")
        

        sa.close()
    except IOError:#if ioerror is true, an error has occurred
        isSent = False
        ss.sendto(isSent.encode('utf-8'), server_addressS)

    ss.sendto(country.encode('utf-8'), server_addressS)
    ss.sendto(city.encode('utf-8'), server_addressS)


    main()
