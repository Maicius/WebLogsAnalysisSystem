package software.analysis.nju.util

import software.analysis.nju.Accumulator.{ByteHourAccumulator, ByteSecAccumulator}

import scala.collection.mutable

/**
  * 处理流量数据
  * 流量即每次请求返回的数据体积
  */
object DoBytesAnalysis {
  def getHourBytesData(date: String, bytes: String, ByteMap: ByteHourAccumulator): ByteHourAccumulator = {
    val byte = changeToLong(bytes)
    ByteMap.add((date, byte))
    ByteMap
  }
  def getSecBytesData(date: String, bytes:String, ByteSecMap: ByteSecAccumulator): ByteSecAccumulator = {
    val byte = changeToLong(bytes)
    ByteSecMap.add((date, byte))
    ByteSecMap
  }

  def getByteSum(ByteMap: ByteHourAccumulator): Long = {
    var tmp: Long = 0
    ByteMap.value.values.foreach((value: Long) =>tmp =  tmp + value)
    tmp
  }
  def changeToLong(x: String): Long = {
    println(x)
    x match {
      case null => 0L
      case "-" => 0L
      case _ => x.toLong
    }
  }

  def addBytesMap(tmp: mutable.Map[String, Long], map:mutable.Map[String, Long]): mutable.Map[String, Long] = {
    for(item <- map){
      if(tmp.contains(item._1)){
        tmp.update(item._1, tmp(item._1) + item._2)
      }
      else{
        tmp +=(item._1 -> item._2)
      }
    }
    tmp
  }
}
