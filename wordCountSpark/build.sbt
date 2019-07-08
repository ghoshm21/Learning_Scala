name := "wordCountSpark"

version := "0.1"

//scalaVersion := "2.13.0"

//--------------- WORKING -----------------------------------------------
scalaVersion := "2.11.12"
// https://mvnrepository.com/artifact/org.apache.spark/spark-core
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.3"
libraryDependencies += "org.apache.hadoop" % "hadoop-hdfs" % "2.5.2"


//--------------- NOT WORKING -----------------------------------------------
// https://mvnrepository.com/artifact/org.apache.spark/spark-core
//libraryDependencies += "org.apache.spark" %% "spark-core" % "2.2.0"

//notworking
//scalaVersion := "2.12.8"
//libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.3"