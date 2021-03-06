REGISTER '/users/level3/1103816b/Desktop/HotOrNot_repo/DataMining/Pig/piggybank.jar';

venues = LOAD '/users/level3/1103816b/Desktop/HotOrNot_repo/DataMining/Pig/venues_extended.csv' USING org.apache.pig.piggybank.storage.CSVLoader();
tweets = LOAD '/users/level3/1103816b/Desktop/HotOrNot_repo/DataMining/Pig/tweets_extended.csv' USING org.apache.pig.piggybank.storage.CSVLoader();

--tweetsReduced = foreach tweets generate tweets::Text;
venuesReduced = foreach venues generate venues::Name;

DUMP venuesReduced;

--Create the Cartesian product of venues and tweets
--crossed = CROSS venuesReduced, tweetsReduced;

--For each record, create a regex like '.*name.*'
--regexes = FOREACH crossed GENERATE *, CONCAT('.*', CONCAT(venuesReduced::name, '.*')) AS regex;


--Keep tweet-venue pairs where the tweet contains the venue name
--venueMentions = FILTER regexes BY text MATCHES regex;

--venueCounts = FOREACH (GROUP venueMentions BY venuesReduced::name) GENERATE group, COUNT($1) as counter;
--venueCountsOrdered = order venueCounts by counter;

--STORE venueCountsOrdered INTO '/users/level3/1103816b/Desktop/HotOrNot_repo/DataMining/Pig/venueCountsOrdered_mid.csv'
--USING org.apache.pig.piggybank.storage.CSVExcelStorage(',', 'NO_MULTILINE', 'WINDOWS');