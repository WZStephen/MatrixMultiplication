
/** Dense Matrix MUltiplication using Handcoded Scala code*/
object MatrixMul_Handcode_Scala {
  def main(args: Array[String]): Unit = {
    val rowsM = 1000
    val colsM = 1000
    val rowsN = 1000
    val colsN = 1000

    //generate matrix
    val M = Array.ofDim[Double](rowsM,colsM)
    val N = Array.ofDim[Double](rowsN,colsN)
    val MN = Array.ofDim[Double](1000,1000)

    //Assign random value to matrix M
    for(i <- 0 until M.length){
      for(j <- 0 until M.length){
        val tmp = scala.util.Random
        M(i)(j) = tmp.nextDouble()
      }
    }
    //Assign random value to matrix N
    for(i <- 0 until N.length){
      for(j <- 0 until N.length){
        val tmp = scala.util.Random
        N(i)(j) = tmp.nextDouble()
      }
    }

    println("left Matrix: ")
    for(i <- 0 until M.length){
      for(j <- 0 until M.length) {
        print(M(i)(j) + " ")
      }
      println()
    }
    println("right Matrix: ")
    for(i <- 0 until N.length){
      for(j <- 0 until N.length) {
        print(N(i)(j) + " ")
      }
      println()
    }
    println()

    //matrix multiplication
    val begin = System.nanoTime()
    for (i <- 0 to 999){
      for(j <- 0 to 999){
        var sum: Double = 0.0
        for(k <- 0 to 999){
          sum = sum + M(i)(k)*N(k)(j)
        }
        MN(i)(j) = sum
      }
    }
    val end = System.nanoTime()

    //Display result matrix
    println("result Matrix: ")
    for(i <- 0 until MN.length){
      for(j <- 0 until MN.length) {
        print(MN(i)(j) + " ")
      }
      println()
    }
    println()
    //Display processing time
    val duration = (end-begin)/1e9d
    println("Process time of Dense Matrix Multiplication using Hand-coded scala: "  +  duration + " second(s)")
    println("Average process time of Dense Matrix Multiplication using Hand-coded scala: "  +  duration/100 + " second(s)")

  }
}


