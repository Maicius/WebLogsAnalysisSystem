package software.analysis.nju.analysis

import software.analysis.nju.Accumulator.{AllDataAccumulator, DateResultAccumulator}
import software.analysis.nju.Entity.Entity.{CourtResult, DateResult}
import software.analysis.nju.util.{DoBytesAnalysis, MapUtil, ParseMapToJson}

import scala.collection.mutable

object AnalysisAllCourt {
/**
*根据每个法院每天的数据统计一天中所有法院数据
  * 计算结果也保存到LogAna中，rowKey为：日期 + 000
 */
  def getAllCourtData(resultMap: AllDataAccumulator, rowKey: String, finalMap: DateResultAccumulator): DateResultAccumulator = {
    var tmpReqSum: Long = 0
    var tmpIPList: mutable.Map[String, Int] = mutable.Map()
    var tmpIPSumVal: Int = 0
    var tmpIPTotalNum: Int = 0
    var tmpReqHourList: mutable.Map[String, Int] = mutable.Map()
    var tmpReqSecList: mutable.Map[String, Int] = mutable.Map()
    var tmpTotalBytes: Long= 0L
    var tmpBytesHourList: mutable.Map[String, Long] = mutable.Map()
    var tmpBytesSecList: mutable.Map[String, Long] = mutable.Map()
    var tmpMaxURL: mutable.Map[String, Int] = mutable.Map()
    var tmpURLList: mutable.Map[String, Int] = mutable.Map()
    var tmpStateMap: mutable.Map[String, Int] = mutable.Map()
    var tmpMethodMap: mutable.Map[String, Int] = mutable.Map()

    for(result <- resultMap.value.values){
      tmpIPSumVal +=result.IPSumVal
      tmpIPTotalNum +=result.IPTotalNum
      tmpIPList = MapUtil.addMapListItem(tmpIPList, result.IPRank)

      tmpReqSum +=result.reqSum
      println("rowKey:" + result.rowK + "  HourList:" + result.ReqHourMap.toString())
      tmpReqHourList = MapUtil.addMapListItem(tmpReqHourList, result.ReqHourMap)
      println("rowKey:" + result.rowK + "  SecList:" + result.ReqSecMap.toString())
      tmpReqSecList = MapUtil.addMapListItem(tmpReqSecList, result.ReqSecMap)

      tmpTotalBytes +=result.TotalBytes
      tmpBytesHourList = MapUtil.addMapListItemLong(tmpBytesHourList, result.BytesHourMap)
      tmpBytesSecList =MapUtil.addMapListItemLong(tmpBytesSecList, result.BytesSecMap)

      tmpURLList = MapUtil.addMapListItem(tmpURLList, result.URLRank)
      tmpMaxURL = MapUtil.addMapListItem(tmpMaxURL, result.MaxURL)

      tmpStateMap = MapUtil.addMapListItem(tmpStateMap, result.StateMap)
      tmpMethodMap = MapUtil.addMapListItem(tmpMethodMap, result.MethodMap)
    }
    val IPRank10 = ParseMapToJson.map2JsonList(MapUtil.getMax10(tmpIPList))
    val maxURL = ParseMapToJson.map2JsonList(MapUtil.getMax(tmpMaxURL))
    val URLRank10 = ParseMapToJson.map2JsonList(MapUtil.getMax10(tmpURLList))
    val dateResult: DateResult = DateResult(rowKey, tmpReqSum.toString,IPRank10, tmpIPSumVal.toString, tmpIPTotalNum.toString,
                                              ParseMapToJson.map2Json(tmpReqHourList), ParseMapToJson.map2Json(tmpReqSecList),
                                              tmpTotalBytes.toString, ParseMapToJson.map2Json2(tmpBytesHourList), ParseMapToJson.map2Json2(tmpBytesSecList),
                                              maxURL, URLRank10, ParseMapToJson.map2Json(tmpMethodMap),  ParseMapToJson.map2Json(tmpStateMap))
    finalMap.add(dateResult)
    finalMap
  }
}
