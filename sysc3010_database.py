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
