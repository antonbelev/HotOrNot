A = load '/users/level3/1103816b/Desktop/HotOrNot_repo/DataMining/Pig/input.txt' AS (word:chararray);
B = foreach A generate flatten(TOKENIZE((chararray)$0)) as word;
C = group B by word;
D = foreach C generate COUNT(B), group;
--DUMP D;
STORE D into '/users/level3/1103816b/Desktop/HotOrNot_repo/DataMining/Pig/wordcount';
