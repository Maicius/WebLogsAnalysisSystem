package software.analysis.nju.Entity

import scala.collection.mutable

object Entity{

  //用String 代替所有的特殊类型，如map， list
  //DateResult用于存储一每一天的处理结果
  //rowkey为date + 法院代码
  // eg:20170808GY，表示天津高院2017-08-08这一天的数据信息
  case class DateResult(rowK:String, reqSum: String,
                        IPList: String, IPSumVal: String, IPTotalNum: String,
                        ReqHourList: String, ReqSecList: String,
                        TotalBytes: String, BytesHourList: String, BytesSecList:String,
                        MaxURL: String, URLList: String, Method: String, State: String)

  //CourtResult用于存储临时存储每个法院每一天的数据
  //累加各个法院数据之后转化为DateResult类型数据
  //注意这里大量使用List[(String， Int)]而不是Map[(Map, Int)]是因为Map保存的数据不知为何获取不到
  case class CourtResult(rowK:String, reqSum: Long,
                         IPSumVal: Int, IPTotalNum: Int, IPRank: List[(String, Int)],
                         ReqHourMap: List[(String, Int)], ReqSecMap: List[(String, Int)],
                         TotalBytes: Long, BytesHourMap: List[(String, Long)],
                         BytesSecMap: List[(String, Long)],
                         MaxURL: List[(String, Int)], URLRank: List[(String, Int)],
                         MethodMap: List[(String, Int)], StateMap: List[(String, Int)])

}
