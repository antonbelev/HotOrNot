REGISTER piggybank.jar
venues = LOAD 'venues_mid' USING org.apache.hcatalog.pig.HCatLoader();
tweets = LOAD 'tweets_times' USING org.apache.hcatalog.pig.HCatLoader();

/* Create the Cartesian product of venues and tweets */
crossed = CROSS venues, tweets;

/* For each record, create a regex like '.*name.*' */
regexes = FOREACH crossed GENERATE *, CONCAT('.*', CONCAT(venues::name, '.*')) AS regex;

/* Keep tweet-venue pairs where the tweet contains the venue name */
venueMentions = FILTER regexes BY text MATCHES regex;

venueCounts = FOREACH (GROUP venueMentions BY (venues::name, tweets::dayofweek)) GENERATE group, COUNT($1);

STORE venueCounts INTO 'Pig_output/dayofweek_count.csv'
USING org.apache.pig.piggybank.storage.CSVExcelStorage(',', 'NO_MULTILINE', 'WINDOWS');