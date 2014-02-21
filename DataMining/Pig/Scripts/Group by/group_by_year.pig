REGISTER '/users/level3/1103816b/Desktop/HotOrNot_repo/DataMining/Pig/Jars/piggybank.jar';
REGISTER '/users/level3/1103816b/Desktop/HotOrNot_repo/DataMining/Pig/Jars/mysql-connector-java-5.1.18-bin.jar';
REGISTER '/users/level3/1103816b/Desktop/HotOrNot_repo/DataMining/Pig/Jars/hotornot_27_01_14_no_const_with_bffrdr_in_exec.jar';

DEFINE GenerateVenueUDF com.anton.hadoop.pig.production.GenerateVenueUDF();

venues = LOAD 'venues_extended_2.csv' USING org.apache.pig.piggybank.storage.CSVLoader() AS (Name:chararray, Type:chararray, Latitude:chararray, Longitude:chararray, City:chararray, Country:chararray);
tweets = LOAD 'tweets_06_02.csv' USING org.apache.pig.piggybank.storage.CSVLoader() AS (Text:chararray, WeekDay:chararray, Month:chararray, Day:chararray, Time:chararray, Name:chararray, Year:chararray, Location:chararray, Language:chararray, Followerscount:chararray, Friendscount:chararray);

tweetsReduced = foreach tweets generate Text, Year;

venuesTweets = foreach tweetsReduced generate *, GenerateVenueUDF(Text) as Venue;

venueCounts = FOREACH (GROUP venuesTweets BY ($1, $2)) GENERATE group, COUNT($1) as counter;

flattenVenues = foreach venueCounts generate flatten(group), counter;

STORE flattenVenues INTO 'VenueYear' USING org.apache.pig.piggybank.storage.DBStorage('com.mysql.jdbc.Driver','jdbc:mysql://storo:3306/teamn', 'teamn', '8553mkpw','INSERT INTO VenueYear (day, venue_name, count) VALUES (?, ?, ?)');
