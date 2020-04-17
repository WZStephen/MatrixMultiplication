import java.util.Random

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.linalg._
import org.apache.spark.mllib.linalg.distributed.{IndexedRow, IndexedRowMatrix, RowMatrix}

object MatrixMul_Spark {
  def main(args: Array[String]): Unit = {
    val appName = "Matrix Multiplication Test"
    val conf = new SparkConf().setAppName(appName).setMaster("local[16]")
    val sc = new SparkContext(conf)

    //generate left matrix
    val r = scala.util.Random
    val rows_ =
      for (i <- 0 to 1000-1)
        yield (i,(for (j <- 0 to 1000-1)
          yield r.nextDouble).toArray)
    val rows = sc.parallelize(rows_.toSeq).map{case (i,rw) => IndexedRow(i,Vectors.dense(rw))}
    val rows2_ = for (i <- 0 to 1000-1) yield (i,(for (j <- 0 to 1000-1) yield r.nextDouble).toArray)
    val rows2 = sc.parallelize(rows2_.toSeq).map{case (i,rw) => IndexedRow(i,Vectors.dense(rw))}
    val indexedRowMatrix = new IndexedRowMatrix(rows).toBlockMatrix().cache()
    //print right matrix
    indexedRowMatrix.blocks.collect.foreach(println)
    //validate the matrix
    indexedRowMatrix.validate()

    //generate right matrix
    val localMatrix = new IndexedRowMatrix(rows2).toBlockMatrix().cache()
    //print right matrix
    localMatrix.blocks.collect.foreach(println)
    //validate the matrix
    localMatrix.validate()

    //Matrix Multiplication
    val begin = System.nanoTime()
    indexedRowMatrix.multiply(localMatrix).blocks.collect.foreach(println)
    val end = System.nanoTime()

    //Display processing time
    val duration = (end-begin)/1e9d
    println("ProcessTime of Dense Matrix Multiplication using Spark: "+ duration + " second(s)")
    println("Average process time of Dense Matrix Multiplication using Spark: "  +  duration/100 + " second(s)")

  }
}
