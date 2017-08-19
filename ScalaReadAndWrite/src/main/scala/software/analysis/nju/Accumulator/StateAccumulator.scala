package software.analysis.nju.Accumulator

import org.apache.spark.util.AccumulatorV2
import software.analysis.nju.util.ParseMapToJson

import scala.collection.mutable

class StateAccumulator extends AccumulatorV2[String, mutable.Map[String, Int]]{
  private val stateMap: mutable.Map[String, Int] = mutable.Map()
  override def isZero: Boolean = stateMap.isEmpty

  override def copy(): AccumulatorV2[String, mutable.Map[String, Int]] = StateAccumulator.this

  override def reset(): Unit = stateMap.clear()

  override def add(v: String): Unit = {
    if(stateMap.contains(v)){
      stateMap.update(v, stateMap(v) + 1)
    }else{
      stateMap +=(v ->1)
    }
  }

  override def merge(other: AccumulatorV2[String, mutable.Map[String, Int]]): Unit = {
    this.value ++=other.value
  }

  override def value: mutable.Map[String, Int] = {
    stateMap
  }

  override def toString(): String = {
    ParseMapToJson.map2Json(stateMap)
  }
}
