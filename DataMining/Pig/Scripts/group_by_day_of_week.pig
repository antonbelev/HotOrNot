REGISTER '/users/level3/1103816b/Desktop/HotOrNot_repo/DataMining/Pig/Jars/piggybank.jar';

venues = LOAD 'venues_extended_2.csv' USING org.apache.pig.piggybank.storage.CSVLoader() AS (Name:chararray, Type:chararray, Latitude:double, Longitude:double, City:chararray, Country:chararray);
tweets = LOAD 'tweets_extended.csv' USING org.apache.pig.piggybank.storage.CSVLoader() AS (Text:chararray, WeekDay:chararray, Day:int, Time:chararray, SMT:chararray, Year:int, Location:chararray, Language:chararray, Followers_count:int, Friends_count:int);

/* Create the Cartesian product of venues and tweets */
crossed = CROSS venues, tweets;

/* For each record, create a regex like '.*name.*' */
regexes = FOREACH crossed GENERATE *, CONCAT('.*', CONCAT(venues::Name, '.*')) AS regex;

/* Keep tweet-venue pairs where the tweet contains the venue name */
venueMentions = FILTER regexes BY Text MATCHES regex;

venueCounts = FOREACH (GROUP venueMentions BY (venues::Name, tweets::WeekDay)) GENERATE group, COUNT($1);

STORE venueCounts INTO 'group_by_day_of_week_result'
USING org.apache.pig.piggybank.storage.CSVExcelStorage(',', 'NO_MULTILINE', 'WINDOWS');