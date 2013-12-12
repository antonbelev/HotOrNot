REGISTER piggybank.jar
venues = LOAD 'venues_mid' USING org.apache.hcatalog.pig.HCatLoader();
tweets = LOAD 'tweets_mid' USING org.apache.hcatalog.pig.HCatLoader();


tweetsReduced = foreach tweets generate text;
venuesReduced = foreach venues generate name;


/* Create the Cartesian product of venues and tweets */
crossed = CROSS venuesReduced, tweetsReduced;

/* For each record, create a regex like '.*name.*' */
regexes = FOREACH crossed GENERATE *, CONCAT('.*', CONCAT(venuesReduced::name, '.*')) AS regex;


/* Keep tweet-venue pairs where the tweet contains the venue name */
venueMentions = FILTER regexes BY text MATCHES regex;

venueCounts = FOREACH (GROUP venueMentions BY venuesReduced::name) GENERATE group, COUNT($1) as counter;
venueCountsOrdered = order venueCounts by counter;

STORE venueCountsOrdered INTO 'Pig_output/venueCountsOrdered_mid.csv'
USING org.apache.pig.piggybank.storage.CSVExcelStorage(',', 'NO_MULTILINE', 'WINDOWS');