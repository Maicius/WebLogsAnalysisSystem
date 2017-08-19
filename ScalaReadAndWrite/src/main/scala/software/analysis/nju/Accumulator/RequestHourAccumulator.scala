package software.analysis.nju.Accumulator

import org.apache.spark.util.AccumulatorV2
import software.analysis.nju.util.ParseMapToJson

import scala.collection.mutable

class RequestHourAccumulator extends AccumulatorV2[String, mutable.Map[String, Int]]{
  private val reHourMap: mutable.Map[String, Int] = mutable.Map()
  override def isZero: Boolean = reHourMap.isEmpty

  override def copy(): AccumulatorV2[String, mutable.Map[String, Int]] = RequestHourAccumulator.this

  override def reset(): Unit = reHourMap.clear()

  override def add(v: String): Unit = {
    val hour = v.slice(11, 13)
    if(reHourMap.contains(hour)){
      reHourMap.update(hour, reHourMap(hour) + 1)
    }else{
      reHourMap +=(hour -> 1)
    }
  }

  override def merge(other: AccumulatorV2[String, mutable.Map[String, Int]]): Unit = {
    this.value ++=other.value
  }

  override def value: mutable.Map[String, Int] = reHourMap

  override def toString(): String = {
    ParseMapToJson.map2Json(reHourMap);
  }
}
