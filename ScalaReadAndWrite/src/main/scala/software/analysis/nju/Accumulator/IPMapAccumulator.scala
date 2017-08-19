package software.analysis.nju.Accumulator

import org.apache.spark.util.{AccumulatorContext, AccumulatorMetadata, AccumulatorV2}
import software.analysis.nju.util.ParseMapToJson

import scala.collection.mutable

class IPMapAccumulator extends AccumulatorV2[String, mutable.Map[String, Int]]{

  private var IPMap: mutable.Map[String, Int] = mutable.Map()

  override def isZero: Boolean = {
    IPMap.isEmpty
  }

  override def copy(): AccumulatorV2[String, mutable.Map[String ,Int]] = {
    val accumulatorV2 = new IPMapAccumulator()
    var iPMap: mutable.Map[String, Int] = mutable.Map()
    for((x, y) <- this.IPMap){
      iPMap +=(x -> y)
    }
    accumulatorV2.IPMap = iPMap
    accumulatorV2
  }

  override def reset(): Unit = {
    IPMap.clear()
  }

  override def value: mutable.Map[String, Int] = {
    IPMap
  }

  override def add(v: String): Unit = {
    if (IPMap.contains(v)){
      IPMap.update(v,IPMap(v)+1)
    }else{
      IPMap +=(v->1)
    }
  }

  override def merge(other: AccumulatorV2[String, mutable.Map[String, Int]]): Unit = {
    this.value ++= other.value
  }
  override def copyAndReset(): AccumulatorV2[String, mutable.Map[String, Int]] = super.copyAndReset()

  override def toString(): String = {
    ParseMapToJson.map2Json(IPMap)
  }

}
