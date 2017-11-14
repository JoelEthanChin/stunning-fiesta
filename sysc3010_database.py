#SYSC3010 Python Database script
#This class will control inserting information to the database as well as retrieving.
#The database will store alarms dictated from the user via an android app to control an alarm clock running on a RPi

import MySQLdb, urllib, sys, socket, time

def __init__(self):
#This method connects the current db to itself to confirm initialization
    self.connection = MySQLdb.connect(self.host, self.user, self.password, self.db)
    self.cursor = self.connection.cursor()

def insert(self, query):
#This method allows an entry to be added into the db
    try:
        self.cursor.execute(query)
        self.connection.commit()
    except:
        self.connection.rollback()

def query(self, query):
#This method switches queries
    cursor = self.connection.cursor( MySQLdb.cursors.DictCursor )
    cursor.execute(query)

    return cursor.fetchall()

def __del__(self):
#This method closes the current db
    self.connection.close()

def main():
    #create db access
    db = MySQLdb.connect(host = "localhost", user = "root", passwd ="J321226896c", db = "sysc3010")
    #create socket access
    #ss socket to send information
    ss = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    portS = int(textport)
    server_addressS = (host, portS)
    #sr socket to recieve information
    sr = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    portR = int(textport)
    server_addressR = (host, portR)
    #check if the db is properly initialized
    __init__(db)
    #cur is a cursor to execute selected queries
    cur = db.cursor()
    #selects data from table alarm from SQL query sysc3010
    cur.execute("SELECT * FROM alarm")
    #for loop sends all the alarms stored in the SQL table to headless RPi
    for row in cur.fetchall() :
        webDataS = urlopen(row[1])
        resultSend = webDataS.read().decode('utf-8')
        ss.sendto(resultSend.encode('utf-8'), server_address)

    #figure out how to recieve information from an android app
    #database class complete


main()
