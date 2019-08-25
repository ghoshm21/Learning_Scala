// in spark 2.2 import spark context
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

// main program
object wordCountSpark {
  def main(args: Array[String]): Unit = {

    // variables
    // val inputData = "/home/sandipan/Documents/wiki_articles/hi.txt"
    // val inputData = "/home/sandipan/Documents/wiki_articles/singleLargeData/*.txt"
    // val inputData = "/home/sandipan/Documents/wiki_articles/data/newfile*"

    // get the input file locations
    val inputData = args(0)
    val outputData = "/home/sandipan/Documents/wiki_articles/out/wordFrequency.csv"

    //Create a SparkContext to initialize Spark setMaster("local[*]")
    val conf = new SparkConf().setAppName("wordCountSpark")
    val spark = new SparkContext(conf)

    val regex = "[//.//?//,//-//;]".r

    // process data, caching to reduce the overhead processing .persist(StorageLevel.DISK_ONLY_2)
    val textData = spark.textFile(inputData)
    // val counts = textData.flatMap(line => line.split(" ")).map(word => (word,1)).reduceByKey(_+_).sortBy(_._2,false)
    val counts = textData.flatMap(line => regex.replaceAllIn(line, "").split(" +")).map(word => (word,1)).reduceByKey(_+_).sortBy(_._2,false)
    counts.take(20).foreach(x=>println(x))
    //overrides does not work
    // counts.foreach(x=>println(x))
    // println(counts.count())

    // writing to a csv file using hadoop merge functions
    counts.repartition(1).saveAsTextFile(outputData)
    spark.stop()
  }
}

//real	6m43.017s
//  user	34m17.210s
//  sys	1m16.445s

//real	5m48.860s
//  user	31m9.826s
//  sys	0m41.864s


// works with scala= 2.12.2 and spark=2.4.3
//import java.io
//import org.apache.spark.sql.SparkSession
//
//object wordCountSpark {
//  def main(args: Array[String]): Unit = {
//
//    // variables
//    // val inputData = "/home/sandipan/Documents/wiki_articles/*.txt"
//    val inputData = "/home/sandipan/Documents/wiki_articles/singleLargeData/*.txt"
//
//    //Create a SparkContext to initialize Spark
//    val spark = SparkSession.builder.master("local").appName("wordCountSpark").getOrCreate()
//    import spark.implicits._
//    // process data
//    val textData = spark.read.textFile(inputData).cache()
//    val counts = textData.flatMap(line => line.split(" ")).map(word => (word,1))
//    counts.take(20).foreach(x=>println(x))
//    //overrides does not work
////    counts.foreach(x=>println(x))
////    println(counts.count())
//    spark.stop()
//  }
//}