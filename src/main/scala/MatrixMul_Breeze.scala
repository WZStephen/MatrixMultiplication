
import breeze.linalg._

object MatrixMul_Breeze {
  def main(args: Array[String]): Unit = {
    //define row and column numbers
    val rowsM = 1000
    val colsM = 1000
    val rowsN = 1000
    val colsN = 1000

    //Assign random values to create two 1000 x 1000 matrix
    val M: DenseMatrix[Double] = DenseMatrix.rand(rowsM, colsM)
    val N: DenseMatrix[Double] = DenseMatrix.rand(rowsN, colsN)

    println("left Matrix: ")
    println(M)
    println("right Matrix: ")
    println(N)

    /** Dense Matrix MUltiplication using breeze*/
    val begin = System.nanoTime()
    println("Result using breeze: ")
    for (times <- 1 to 100) {
      val MN: DenseMatrix[Double] = M * N
      //println(MN)
      println("count: " + times);
    }
    val end = System.nanoTime()

    //Display processing time
    val duration = (end-begin)/1e9d
    //Display processing time
    println("ProcessTime of Dense Matrix Multiplication using breeze: " + duration + " second(s)")
    println("Average process time of Dense Matrix Multiplication using breeze: "  +  duration/100 + " second(s)")

  }
}