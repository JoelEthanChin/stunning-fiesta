# to create database
# 1) in terminal enter CREATE DATABASE databasename;
# 2) USE databasename;
# 3) CREATE TABLE IF NOT EXISTS tablename(id int(11) NOT NULL AUTO_INCREMENT,
# description varchar(45), PRIMARY KEY(id));

# to add entries into database
# 1)INSERT INTO databasename(description) VALUES ("whatever you want to enter")

import MySQLdb

db = MySQLdb.connect(host = "localhost", #your host
                     user="root",        #username
                     passwd="root",      #password
                     db = "pythonstop")  #name of the database

#create a Cursor object to execute query
cur = db.cursor()

#select data from table using SQL query
cur.execute("SELECT * FROM examples")

#print the first and second column
for row in cur.fetchall() :
    print row[0], " ", row[1]
