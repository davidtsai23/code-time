package com.jfs.scala

object MyModule {

  def factorial(n:Int) : Int = {
    def go(n:Int,acc:Int) : Int =
      if(n<=0) acc
      else go(n-1,n*acc)

    go(n,1)

  }

  def main(args: Array[String]): Unit = {
    println(MyModule.feibo(3))
  }


  def feibo(n:Int) = {
    def go(result:Int) : Int =
      if(n<0) -1
      else if(n==0) 0
      else if(n==1 || n==2) 1
      else (n-1)+(n-2)

    go(n)
  }
}
