REGISTER '/users/level3/1103816b/Desktop/HotOrNot_repo/DataMining/Pig/Jars/piggybank.jar';
REGISTER '/users/level3/1103816b/Desktop/HotOrNot_repo/DataMining/Pig/Jars/mysql-connector-java-5.1.18-bin.jar';
--REGISTER '/users/level3/1103816b/Desktop/HotOrNot_repo/DataMining/Pig/Jars/hotornot_20_01_14_no_parameter_constructor.jar';
REGISTER '/users/level3/1103816b/Desktop/HotOrNot_repo/DataMining/Pig/Jars/hotornot_27_01_14_no_const_with_bffrdr_in_exec.jar';

DEFINE GenerateVenueUDF com.anton.hadoop.pig.production.GenerateVenueUDF();

--DEFINE GenerateVenueUDF org.gla.anton.udf.production.GenerateVenueUDF('/users/level3/1103816b/Desktop/HotOrNot_repo/DataMining/Pig/venues_regex.txt');
--venues_extended_2.csv
--tweets_extended.csv
--/users/level3/1103816b/Desktop/HotOrNot_repo/DataMining/Pig/Data/venues_mid.csv
--/users/level3/1103816b/Desktop/HotOrNot_repo/DataMining/Pig/Data/tweets_mid.csv

venues = LOAD 'venues_extended_2.csv' USING org.apache.pig.piggybank.storage.CSVLoader() AS (Name:chararray, Type:chararray, Latitude:double, Longitude:double, City:chararray, Country:chararray);
tweets = LOAD 'tweets_extended.csv' USING org.apache.pig.piggybank.storage.CSVLoader() AS (Text:chararray, WeekDay:chararray, Day:int, Time:chararray, SMT:chararray, Year:int, Location:chararray, Language:chararray, Followers_count:int, Friends_count:int);

tweetsReduced = foreach tweets generate Text;

DUMP tweetsReduced;

--venuesTweets = foreach tweetsReduced generate *, GenerateVenueUDF(Text);

--venueCounts = FOREACH (GROUP venuesTweets BY $1) GENERATE group, COUNT($1) as counter;
--venueCountsOrdered = order venueCounts by counter;

--STORE venueCountsOrdered INTO 'VenueData' USING org.apache.pig.piggybank.storage.DBStorage('com.mysql.jdbc.Driver','jdbc:mysql://storo:3306/teamn', 'teamn', '8553mkpw','INSERT INTO VenueData (Name, Hits) VALUES (?, ?)');

