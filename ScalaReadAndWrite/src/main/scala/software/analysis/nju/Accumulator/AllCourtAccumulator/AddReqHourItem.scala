package software.analysis.nju.Accumulator.AllCourtAccumulator

import org.apache.spark.util.AccumulatorV2

import scala.collection.mutable

class AddReqHourItem extends AccumulatorV2[mutable.Map[String, Int], mutable.Map[String, Int]]{
  private var map: mutable.Map[String, Int] =mutable.Map()
  override def isZero: Boolean = map.isEmpty

  override def copy(): AccumulatorV2[mutable.Map[String, Int], mutable.Map[String, Int]] = AddReqHourItem.this

  override def reset(): Unit = map.clear()

  override def add(v: mutable.Map[String, Int]): Unit = {

  }

  override def merge(other: AccumulatorV2[mutable.Map[String, Int], mutable.Map[String, Int]]): Unit = {
    this.map ++=map
  }

  override def value: mutable.Map[String, Int] = map
}
