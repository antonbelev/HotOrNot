REGISTER '/users/level3/1103816b/Desktop/HotOrNot_repo/DataMining/Pig/Jars/piggybank.jar';
REGISTER '/users/level3/1103816b/Desktop/HotOrNot_repo/DataMining/Pig/Jars/mysql-connector-java-5.1.18-bin.jar';

venues = LOAD 'venues_extended_2.csv' USING org.apache.pig.piggybank.storage.CSVLoader() AS (Name:chararray, Type:chararray, Latitude:chararray, Longitude:chararray, City:chararray, Country:chararray);

STORE venues INTO 'VenueCompleteInformation' USING org.apache.pig.piggybank.storage.DBStorage('com.mysql.jdbc.Driver','jdbc:mysql://storo:3306/teamn', 'teamn', '8553mkpw','INSERT IGNORE INTO VenueCompleteInformation (name, type, latitude, longitude, city, country) VALUES (?, ?, ?, ?, ?, ?)');

