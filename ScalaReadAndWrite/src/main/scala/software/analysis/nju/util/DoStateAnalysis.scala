package software.analysis.nju.util

import software.analysis.nju.Accumulator.{IPMapAccumulator, StateAccumulator, URLAccumulator}

import scala.collection.mutable

object DoStateAnalysis {

  //返回method的种类和次数
  def getMethod(method: String, methodMap: mutable.Map[String, Int]): mutable.Map[String, Int] = {
    if(methodMap.contains(method)){
      methodMap(method) +=1
    }
    else{
      methodMap +=(method -> 1)
    }
    methodMap
  }

  def getStateMap(state: String, stateAccumulator: StateAccumulator): StateAccumulator = {
    stateAccumulator.add(state)
    stateAccumulator
  }

//  def getState(state: String): Int = {
//
//  }

}
