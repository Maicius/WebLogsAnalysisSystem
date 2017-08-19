package software.analysis.nju.Accumulator

import org.apache.spark.util.AccumulatorV2
import software.analysis.nju.Entity.Entity.{DateResult, CourtResult}

import scala.collection.mutable

class AllDataAccumulator extends AccumulatorV2[CourtResult, mutable.Map[String, CourtResult]]{
  val resultMap: mutable.Map[String, CourtResult] = mutable.Map()
  override def isZero: Boolean = resultMap.isEmpty

  override def copy(): AccumulatorV2[CourtResult, mutable.Map[String, CourtResult]] = AllDataAccumulator.this

  override def reset(): Unit = resultMap.clear()

  override def add(v: CourtResult): Unit = {
    resultMap +=(v.rowK -> v)
  }

  override def merge(other: AccumulatorV2[CourtResult, mutable.Map[String, CourtResult]]): Unit = {
    this.value ++=other.value
  }

  override def value: mutable.Map[String, CourtResult] = resultMap
}
