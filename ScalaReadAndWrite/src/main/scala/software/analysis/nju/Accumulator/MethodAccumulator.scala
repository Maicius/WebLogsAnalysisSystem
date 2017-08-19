package software.analysis.nju.Accumulator

import org.apache.spark.util.AccumulatorV2
import software.analysis.nju.util.ParseMapToJson

import scala.collection.mutable

class MethodAccumulator extends AccumulatorV2[String, mutable.Map[String, Int]]{
  private var methodMap: mutable.Map[String, Int] = mutable.Map()

  override def isZero: Boolean = methodMap.isEmpty

  override def copy(): AccumulatorV2[String, mutable.Map[String, Int]] = MethodAccumulator.this

  override def reset(): Unit = methodMap.clear()

  override def add(v: String): Unit = {
    if(methodMap.contains(v)){
      methodMap.update(v, methodMap(v) + 1)
    }else{
      methodMap += (v -> 1)
    }
  }

  override def merge(other: AccumulatorV2[String, mutable.Map[String, Int]]): Unit ={
    this.value ++= other.value
  }

  override def value: mutable.Map[String, Int] = methodMap

  override def toString(): String = {
    ParseMapToJson.map2Json(methodMap)
  }
}
