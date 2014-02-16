REGISTER '/users/level3/1103816b/Desktop/HotOrNot_repo/DataMining/Pig/Jars/piggybank.jar';
REGISTER '/users/level3/1103816b/Desktop/HotOrNot_repo/DataMining/Pig/Jars/mysql-connector-java-5.1.18-bin.jar';
REGISTER '/users/level3/1103816b/Desktop/HotOrNot_repo/DataMining/Pig/Jars/hotornot_09_01_14_no_constructor.jar';

DEFINE GenerateVenueUDF com.anton.hadoop.pig.production.GenerateVenueUDF();
--DEFINE GenerateVenueUDF org.gla.anton.udf.production.GenerateVenueUDF('/users/level3/1103816b/Desktop/HotOrNot_repo/DataMining/Pig/venues_regex.txt');

venues = LOAD 'venues_extended_2.csv' USING org.apache.pig.piggybank.storage.CSVLoader() AS (Name:chararray, Type:chararray, Latitude:chararray, Longitude:chararray, City:chararray, Country:chararray);
tweets = LOAD 'tweets_06_02.csv' USING org.apache.pig.piggybank.storage.CSVLoader() AS (Text:chararray, WeekDay:chararray, Month:chararray, Day:chararray, Time:chararray, Name:chararray, Year:chararray, Location:chararray, Language:chararray, Followerscount:chararray, Friendscount:chararray);

tweetsReduced = foreach tweets generate Text, Followers_count;

venuesTweets = foreach tweetsReduced generate *, GenerateVenueUDF(Text);

venuesTweetsFiltered = FILTER venuesTweets BY Followers_count >= 800;

venueCounts = FOREACH (GROUP venuesTweetsFiltered BY $1) GENERATE group, COUNT($1) as counter;
venueCountsOrdered = order venueCounts by counter;

--DUMP venueCountsOrdered;

STORE venueCountsOrdered INTO 'VenueData' USING org.apache.pig.piggybank.storage.DBStorage('com.mysql.jdbc.Driver','jdbc:mysql://storo:3306/teamn', 'teamn', '8553mkpw','INSERT INTO VenueData (Name, Hits) VALUES (?, ?)');