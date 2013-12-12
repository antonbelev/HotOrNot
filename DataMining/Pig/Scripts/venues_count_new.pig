REGISTER '/users/level3/1103816b/Desktop/HotOrNot_repo/DataMining/Pig/piggybank.jar';
--REGISTER 'piggybank.jar';

venues = LOAD 'venues_extended_2.csv' USING org.apache.pig.piggybank.storage.CSVLoader() AS (Name:chararray, Type:chararray, Latitude:double, Longitude:double, City:chararray, Country:chararray);
tweets = LOAD 'tweets_extended.csv' USING org.apache.pig.piggybank.storage.CSVLoader() AS (Text:chararray, WeekDay:chararray, Day:int, Time:chararray, SMT:chararray, Year:int, Location:chararray, Language:chararray, Followers_count:int, Friends_count:int);

--DESCRIBE venues;

tweetsReduced = foreach tweets generate Text;
venuesReduced = foreach venues generate Name;

--DUMP venuesReduced;

--Create the Cartesian product of venues and tweets
crossed = CROSS venuesReduced, tweetsReduced;

--DUMP crossed;

--For each record, create a regex like '.*name.*'
regexes = FOREACH crossed GENERATE *, CONCAT('.*', CONCAT(venuesReduced::Name, '.*')) AS regex;


--Keep tweet-venue pairs where the tweet contains the venue name
venueMentions = FILTER regexes BY Text MATCHES regex;

venueCounts = FOREACH (GROUP venueMentions BY venuesReduced::Name) GENERATE group, COUNT($1) as counter;
venueCountsOrdered = order venueCounts by counter;

STORE venueCountsOrdered INTO 'venueCountsOrdered_mid.csv'
USING org.apache.pig.piggybank.storage.CSVExcelStorage(',', 'NO_MULTILINE', 'WINDOWS');
