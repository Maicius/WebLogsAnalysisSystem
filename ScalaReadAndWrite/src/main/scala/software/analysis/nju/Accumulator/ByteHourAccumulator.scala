package software.analysis.nju.Accumulator

import org.apache.spark.util.AccumulatorV2
import software.analysis.nju.util.ParseMapToJson

import scala.collection.mutable

class ByteHourAccumulator extends AccumulatorV2[(String, Long), mutable.Map[String, Long]]{
  private var ByteHourMap:mutable.Map[String, Long] = mutable.Map()

  override def isZero: Boolean = {
    ByteHourMap.isEmpty
  }

  override def copy(): AccumulatorV2[(String, Long), mutable.Map[String, Long]] = {
    ByteHourAccumulator.this
  }
  override def reset(): Unit = {
    ByteHourMap.clear()
  }
  //以时间为单位计算bytes
  override def add(v: (String, Long)): Unit = {
    val hour = v._1.slice(11, 13)
    //println(hour)
    if(ByteHourMap.contains(hour)){
      ByteHourMap.update(hour, ByteHourMap(hour) + v._2)
    }else{
      ByteHourMap +=(hour -> v._2)
    }
  }

  override def merge(other: AccumulatorV2[(String, Long), mutable.Map[String, Long]]): Unit = {
    this.value ++=other.value
  }
  override def value: mutable.Map[String, Long] = {
    ByteHourMap
  }

  override def toString(): String = {
    ParseMapToJson.map2Json2(ByteHourMap)
  }
}
