#Main program
#will run all sender and receiver programs including server and database programs

import sys, serial

ser = serial.Serial('/dev/ttyACM0', 9600)


def toSend(info):

    ser.write(info)

def sendHigh():

    ser.write(b'1')

def main():

    sendHigh()
    

main()
