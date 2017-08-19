package software.analysis.nju.Accumulator.AllCourtAccumulator

import org.apache.spark.util.AccumulatorV2

import scala.collection.mutable

class AddIPRankItem extends AccumulatorV2[List[(String, Int)], mutable.Map[String, Int]] {
  private var iPRankIMap: mutable.Map[String, Int] = mutable.Map()
  override def isZero: Boolean = iPRankIMap.isEmpty

  override def copy(): AccumulatorV2[List[(String, Int)], mutable.Map[String, Int]] = AddIPRankItem.this

  override def reset(): Unit = iPRankIMap.clear()

  override def add(v: List[(String, Int)]): Unit = {
    for(item <- v){
      if(iPRankIMap.contains(item._1)){
        iPRankIMap.update(item._1, iPRankIMap(item._1) + item._2)
      }else{
        iPRankIMap. +=(item._1 ->item._2)
      }
    }
  }

  override def merge(other: AccumulatorV2[List[(String, Int)], mutable.Map[String, Int]]): Unit = {
    this.value ++=other.value
  }

  override def value: mutable.Map[String, Int] = iPRankIMap
}
