package software.analysis.nju.util

import software.analysis.nju.Accumulator.IPMapAccumulator

import scala.collection.mutable

object DoIPAnalysis {
  //获取IP的Map，Map类型为[ip地址, 数量]
  def getIpMap(ip: String, IpMap: IPMapAccumulator): IPMapAccumulator = {
    IpMap.add(ip)
    IpMap
  }

  def getIpRank10(IpMap: IPMapAccumulator): List[(String, Int)]= {
    val list = IpMap.value.toList.sortWith(_._2 > _._2)
    list.take(10)
  }

  // 总访问次数，即所有IPMap的value
  def getIpSumValue(IpMap: IPMapAccumulator): Int = {
    var tmp: Int = 0
    IpMap.value.values.foreach((value: Int) =>tmp =  tmp + value)
    tmp
  }

  //返回IP总数（不包括重复的）
  def getIpTotalNum(IpMap: IPMapAccumulator): Int = {
    val length = IpMap.value.toList.length
    length
  }
}
