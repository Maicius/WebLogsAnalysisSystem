package software.analysis.nju.util

import org.apache.spark.util.CollectionAccumulator
import software.analysis.nju.Accumulator.MethodAccumulator

import scala.collection.mutable

object DoMethodAnalysis {
  def getMethodMap(method: String, methodMap: MethodAccumulator):
                                                                      MethodAccumulator = {
    methodMap.add(method)
    methodMap
  }
}
