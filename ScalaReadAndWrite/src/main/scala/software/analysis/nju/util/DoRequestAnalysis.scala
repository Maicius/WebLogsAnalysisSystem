package software.analysis.nju.util

import software.analysis.nju.Accumulator.{RequestHourAccumulator, RequestSecAccumulator}

import scala.collection.mutable

object DoRequestAnalysis {
  def getRequestHourMap(request:String, requestHourAccumulator: RequestHourAccumulator):
                                                                                RequestHourAccumulator ={
    requestHourAccumulator.add(request)
    requestHourAccumulator
  }

  def getRequestSecMap(request: String, requestSecAccumulator: RequestSecAccumulator):
                                                                                RequestSecAccumulator = {
    requestSecAccumulator.add(request)
    requestSecAccumulator
  }
}
