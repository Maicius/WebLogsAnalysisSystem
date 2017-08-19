package software.analysis.nju.util

import software.analysis.nju.Accumulator.URLAccumulator

import scala.collection.mutable

/**
  * 做与URL有关的分析
  */
object DoURLAnalysis {
  def getURLMap(URL: String, uRLAccumulator: URLAccumulator):URLAccumulator ={
    uRLAccumulator.add(URL)
    uRLAccumulator
  }

  def getURLRank10(uRLAccumulator: URLAccumulator): List[(String, Int)] = {
    uRLAccumulator.value.toList.sortWith(_._2 > _._2).take(10)
  }

  def getMaxURL(uRLAccumulator: URLAccumulator): List[(String, Int)] = {
    val list = uRLAccumulator.value.toList.sortWith(_._2 > _._2)
    //println("maxURL:" + list.take(1))
    list.take(1)
  }
}
