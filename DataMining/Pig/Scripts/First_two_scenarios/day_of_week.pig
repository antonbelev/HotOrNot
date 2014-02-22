REGISTER '/users/level3/1103816b/Desktop/HotOrNot_repo/DataMining/Pig/Jars/piggybank.jar';
REGISTER '/users/level3/1103816b/Desktop/HotOrNot_repo/DataMining/Pig/Jars/mysql-connector-java-5.1.18-bin.jar';

venues = LOAD 'venues_extended_2.csv' USING org.apache.pig.piggybank.storage.CSVLoader() AS (Name:chararray, Type:chararray, Latitude:chararray, Longitude:chararray, City:chararray, Country:chararray);
tweets = LOAD 'tweets_06_02.csv' USING org.apache.pig.piggybank.storage.CSVLoader() AS (Text:chararray, WeekDay:chararray, Month:chararray, Day:chararray, Time:chararray, Name:chararray, Year:chararray, Location:chararray, Language:chararray, Followerscount:int, Friendscount:int);

tweetsReduced = foreach tweets generate Text, WeekDay;
venuesReduced = foreach venues generate Name, Type;

--Create the Cartesian product of venues and tweets
crossed = CROSS venuesReduced, tweetsReduced;

--For each record, create a regex like '.*name.*'
regexes = FOREACH crossed GENERATE *, CONCAT('.*', CONCAT(venuesReduced::Name, '.*')) AS regex;

--Keep tweet-venue pairs where the tweet contains the venue name
venueMentions = FILTER regexes BY Text MATCHES regex;

venueCounts = FOREACH (GROUP venueMentions BY (venuesReduced::Name, venuesReduced::Type, tweetsReduced::WeekDay)) GENERATE group, COUNT($1) as counter;

flattenVenues = foreach venueCounts generate flatten(group), counter;

--Describe flattenVenues;
STORE flattenVenues INTO 'DayOfWeekHits' USING org.apache.pig.piggybank.storage.DBStorage('com.mysql.jdbc.Driver','jdbc:mysql://storo:3306/teamn', 'teamn', '8553mkpw','INSERT INTO DayOfWeekHits (name, type, weekday, hits) VALUES (?, ?, ?, ?)');
