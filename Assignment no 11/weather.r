library(RSQLite) 
library(DBI) 
db<-dbConnect(SQLite(),dbname="test3") 
dbSendQuery(conn=db,"CREATE TABLE weather_real(year INT,day TEXT,station TEXT,snowfall INT,temp INT)") 
dbSendQuery(conn=db,"INSERT INTO weather_real (year,day,station,snowfall,temp )VALUES(2014,'tuesday','aa',10,30),(2015,'friday','bb',10,20),(2013,' saturday','cc',9,10),(2015,'tuesday','rr',22,30),(2013,'tuesday','bb' ,16,30),(2011,'saturday','oo',10,30),(2018,'tuesday','aa',10,23),(2017,'tuesday','aa',20,20),(2016,'friday','aa',18,12),(2018,'monday','aa ',10,21),(2013,'sunday','aa',10,24),(2016,'tuesday','pp',10,35)") 

dbListTables(db) 
dbListFields(db,"weather_real") 
dbReadTable(db,"weather_real") 
result<-dbSendQuery(conn=db,"SELECT day,station,max(snowfall) FROM weather_real WHERE year=2013") 
dbFetch(result) 


