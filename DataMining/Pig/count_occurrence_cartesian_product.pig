venues = LOAD 'venues_test_1' USING org.apache.hcatalog.pig.HCatLoader();
tweets = LOAD 'tweets_test_1' USING org.apache.hcatalog.pig.HCatLoader();

/* Create the Cartesian product of venues and tweets */
crossed = CROSS venues, tweets;

/* For each record, create a regex like '.*name.*' */
regexes = FOREACH crossed GENERATE *, CONCAT('.*', CONCAT(venues::name, '.*')) AS regex;



/* Keep tweet-venue pairs where the tweet contains the venue name */
venueMentions = FILTER regexes BY text MATCHES regex;


venueCounts = FOREACH (GROUP venueMentions BY venues::name) GENERATE group, COUNT($1);

STORE venueCounts into 'venueCounts_new';