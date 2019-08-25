# Run spark scala application to do a word frequency check and save as a CSV format

1. Build the sbt using package sbt
2. then run this app with the text file you want to use
3. I am using the wiki article I have created in last python project @ <<location to add>>

#Learning
1. this is the first scala spark app
2. learned how to use sbt package management
3. found out that scala 2.13 with spark 2.4 does not works
4. scala 2.11 and spark 2.4 works perfectly
5. learned how to submit a spark application local cluster
6. learned how to use re-partition to have a single CSV file instead of multiple (depends on the split)
6. also learned that re-preparation is not a good idea for extensive data as this bring the data to the driver
7. used regex to clean the words

#Usage
time spark-submit --class wordCountSpark --master local[*] /home/sandipan/IdeaProjects/wordCountSpark/target/scala-2.11/wordcountspark_2.11-0.1.jar "/home/sandipan/Documents/wiki_articles/singleLargeData/wiki_eng_articles.txt.bz2"

