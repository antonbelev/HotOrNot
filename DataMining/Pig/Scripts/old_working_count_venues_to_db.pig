REGISTER '/users/level3/1103816b/Desktop/HotOrNot_repo/DataMining/Pig/Jars/piggybank.jar';
REGISTER '/users/level3/1103816b/Desktop/HotOrNot_repo/DataMining/Pig/Jars/mysql-connector-java-5.1.18-bin.jar';

venues = LOAD 'venues_extended_small.csv' USING org.apache.pig.piggybank.storage.CSVLoader() AS (Name:chararray, Type:chararray, Latitude:double, Longitude:double, City:chararray, Country:chararray);
tweets = LOAD 'tweets_extended_small.csv' USING org.apache.pig.piggybank.storage.CSVLoader() AS (Text:chararray, WeekDay:chararray, Day:int, Time:chararray, SMT:chararray, Year:int, Location:chararray, Language:chararray, Followers_count:int, Friends_count:int);

tweetsReduced = foreach tweets generate Text;
venuesReduced = foreach venues generate Name;

--Create the Cartesian product of venues and tweets
crossed = CROSS venuesReduced, tweetsReduced;

--For each record, create a regex like '.*name.*'
regexes = FOREACH crossed GENERATE *, CONCAT('.*', CONCAT(venuesReduced::Name, '.*')) AS regex;

--Keep tweet-venue pairs where the tweet contains the venue name
venueMentions = FILTER regexes BY Text MATCHES regex;

venueCounts = FOREACH (GROUP venueMentions BY venuesReduced::Name) GENERATE group, COUNT($1) as counter;
venueCountsOrdered = order venueCounts by counter;

STORE venueCountsOrdered INTO 'VenueData' USING org.apache.pig.piggybank.storage.DBStorage('com.mysql.jdbc.Driver','jdbc:mysql://storo:3306/teamn', 'teamn', '8553mkpw','INSERT INTO VenueData (Name, Hits) VALUES (?, ?)');