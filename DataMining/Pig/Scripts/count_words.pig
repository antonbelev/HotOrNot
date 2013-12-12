REGISTER piggybank.jar
venues = LOAD 'venues_test_1' USING org.apache.hcatalog.pig.HCatLoader();
tweets = LOAD 'tweets_test_1' USING org.apache.hcatalog.pig.HCatLoader();

words = FOREACH tweets GENERATE FLATTEN(TOKENIZE(text)) AS word;
filtered_words = FILTER words BY word MATCHES '\\w+';

-- create a group for each word
word_groups = GROUP filtered_words BY word;

word_count = FOREACH word_groups GENERATE COUNT(filtered_words) AS count, group AS word;

-- order the records by count
ordered_word_count = ORDER word_count BY count DESC;

--venue_names = FOREACH venues GENERATE name;

--result = JOIN ordered_word_count BY word, venue_names BY name;

STORE ordered_word_count INTO 'ordered_word_count.csv'
USING org.apache.pig.piggybank.storage.CSVExcelStorage(',', 'NO_MULTILINE', 'WINDOWS');