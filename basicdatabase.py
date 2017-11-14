# to create database
# 1) in terminal enter CREATE DATABASE databasename;
# 2) USE databasename;
# 3) CREATE TABLE IF NOT EXISTS tablename(id int(11) NOT NULL AUTO_INCREMENT,
# description varchar(45), PRIMARY KEY(id));

# to add entries into database from terminal
# 1)INSERT INTO databasename(description) VALUES ("whatever you want to enter");

# to add entries into database from python
# 1) try:
#         cursor.execute("""INSERT INTO tablename VALUES (%s,%s)""", (188,90))
#         db.commit()
#     except:
#         db.rollback()

import MySQLdb

db = MySQLdb.connect(host = "localhost", #your host
                     user="root",        #username
                     passwd="abcd",      #password
                     db = "sysc3010")  #name of the database

#create a Cursor object to execute query
cur = db.cursor()

#select data from table using SQL query
cur.execute("SELECT * FROM alarm")

#print the first and second column
for row in cur.fetchall() :
    print row[0], " ", row[1]
