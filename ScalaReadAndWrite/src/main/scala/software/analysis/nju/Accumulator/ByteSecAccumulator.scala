package software.analysis.nju.Accumulator

import org.apache.spark.util.AccumulatorV2
import software.analysis.nju.util.ParseMapToJson

import scala.collection.mutable

/**
  * 以秒为单位统计流量
  * 因为一个Accumulator只能传出一个值，所以这个累加器不能与ByteAccumulator合并
  *
  */
class ByteSecAccumulator  extends AccumulatorV2[(String, Long), mutable.Map[String, Long]]{
  private var BytesSecMap: mutable.Map[String, Long] = mutable.Map()
  override def isZero: Boolean = {
    BytesSecMap.isEmpty
  }

  override def copy(): AccumulatorV2[(String, Long), mutable.Map[String, Long]] = {
    ByteSecAccumulator.this
  }

  override def reset(): Unit = {
    BytesSecMap.clear()
  }

  override def add(v: (String, Long)): Unit = {
    val sec = v._1.slice(0, 19)
    if(BytesSecMap.contains(sec)){
      BytesSecMap.update(sec, BytesSecMap(sec) + v._2)
    }else{
      BytesSecMap +=(sec -> v._2)
    }
  }

  override def merge(other: AccumulatorV2[(String, Long), mutable.Map[String, Long]]): Unit = {
    this.value ++= other.value
  }

  override def value: mutable.Map[String, Long] = {
    BytesSecMap
  }
  override def toString(): String = {
    ParseMapToJson.map2Json2(BytesSecMap)
  }
}
