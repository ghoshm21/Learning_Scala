import java.io.File

import scala.collection.mutable
import scala.io.Source
import scala.collection.mutable.Map
import scala.language.postfixOps

object wordCount extends App{
  // empty mutable Map
  val wordCountMap:mutable.Map[String, Int] = Map()

  //get list of files
  def getFileList(dir: String): List[File] = {
    val d = new File(dir)
    if (d.exists() && d.isDirectory){
      d.listFiles().filter(_.isFile).toList
    }else List[File]()

  }

    //to replace all the punctuations
    val regex = "[//.//?//,//-]".r
    // initialize count val
    var count: Int = 0
    //the file input dir
    val inputDir = "/root/Documents/books"
    //get all the files under this dir
    val files = getFileList(inputDir)
    //loop into the individual file
    files.foreach { (fileName: File) =>{
      println("processing: "+(fileName.toString))
      // use buffered sources to read the data line by line
      val bufferedSource = Source.fromFile(fileName)
      // for lines in the file, clean the line, split into words based on one or more space
      for (line <- bufferedSource.getLines()) {

        val cleanLine = regex.replaceAllIn(line, "").split(" +").toList

        for (word <- cleanLine) {
          //check if the word exist then increase the count, else insert the word
          if (wordCountMap.contains(word)) {
            wordCountMap += (word.toString -> (wordCountMap(word) + 1))
          } else {
            wordCountMap += (word.toString -> 0)
          }

        }

      }

      bufferedSource.close
      // sorting the order
      println(wordCountMap.toSeq.sortWith(_._2 > _._2))
    }

    }

}
