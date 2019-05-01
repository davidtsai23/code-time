package com.jfs.scala

object MyModule {

  /**
    * 阶乘
    * @param n
    * @return
    */
  def factorial(n:Int) : Int = {
    def go(n:Int,acc:Int) : Int =
      if(n<=0) acc
      else go(n-1,n*acc)

    go(n,1)

  }

  def main(args: Array[String]): Unit = {
    //println(MyModule.feibo(3))
    println(formatResult("abs",-42,abs))
  }


  /**
    * 斐波那切数列0 1 1 2 3 5
    * @param n
    * @return
    */
  def feibo(n:Int) = {
    def go(result:Int) : Int =
      if(n<0) -1
      else if(n==0) 0
      else if(n==1 || n==2) 1
      else (n-1)+(n-2)

    go(n)
  }

  def abs(n:Int) = {
    Math.abs(n)
  }

  /**
    * 高阶函数
    * @param name
    * @param n
    * @param f 表示一个接收int类型参数并返回int结果的函数
    * @return
    */
  def formatResult(name:String,n:Int,f:Int =>Int) = {
    val msg = "The %s of %d is %d ."
    msg.format(name,n,f(n));
  }


}
