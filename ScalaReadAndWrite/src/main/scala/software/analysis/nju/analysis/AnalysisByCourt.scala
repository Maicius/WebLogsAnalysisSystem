/*
 * This example reads a row of time series sensor data
 * calculates the the statistics for the hz data
 * and then writes these statistics to the stats column family
 *
 */

package software.analysis.nju.analysis

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.client.Put
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.mapred.JobConf
import org.apache.spark.{SparkConf, SparkContext}
import software.analysis.nju.Accumulator._
import software.analysis.nju.Entity.Entity.{DateResult, CourtResult}
import software.analysis.nju.constant.SparkProperties
import software.analysis.nju.util._
object AnalysisByCourt extends Serializable {
/**
    *统计每个法院每天的数据
  * 注意累加器的使用:
  * 声明  -->  注册 --> 计算-->清空
 */
  def AnalysisByCourtAndDay(sc: SparkContext, conf: Configuration, rowKey:String,
                            jobConf: JobConf, finalMap: DateResultAccumulator):CourtResult =  {

    val IpMap = new IPMapAccumulator()
    val StateMap = new StateAccumulator()
    val URLMap = new URLAccumulator()
    val ByteMap = new ByteHourAccumulator()
    val ByteSecMap = new ByteSecAccumulator()
    val methodMap = new MethodAccumulator()
    val requestHourMap = new RequestHourAccumulator()
    val requestSecMap = new RequestSecAccumulator()

    //累加器必须先注册再使用
    sc.register(IpMap, "IPMap")
    sc.register(StateMap, "StateMap")
    sc.register(URLMap, "URLMap")
    sc.register(ByteMap, "ByteMap")
    sc.register(ByteSecMap, "BYTESSecMap")
    sc.register(methodMap, "MethodMap")
    sc.register(requestHourMap, "RequestHourMap")
    sc.register(requestSecMap, "RequestSecMap")

    // 构造RDD
    val hBaseRDD = sc.newAPIHadoopRDD(conf, classOf[TableInputFormat],
      classOf[org.apache.hadoop.hbase.io.ImmutableBytesWritable],
      classOf[org.apache.hadoop.hbase.client.Result])
    //count值为所有的请求数量
    val count = hBaseRDD.count()
    hBaseRDD.foreach { case (_, result) => {
      //println(result)
      val state = Bytes.toString(result.getValue(SparkProperties.LOG_CF, SparkProperties.STATE))
      val dates = Bytes.toString(result.getValue(SparkProperties.LOG_CF, SparkProperties.DATE))
      val method = Bytes.toString(result.getValue(SparkProperties.LOG_CF, SparkProperties.METHOD))
      val URL = Bytes.toString(result.getValue(SparkProperties.LOG_CF, SparkProperties.URL_CF))
      val bytes = Bytes.toString(result.getValue(SparkProperties.LOG_CF, SparkProperties.BYTES))
      val ip = Bytes.toString(result.getValue(SparkProperties.LOG_CF, SparkProperties.IP))

      //分析请求方法，eg: GET, POST
      DoMethodAnalysis.getMethodMap(method, methodMap)
      //按秒统计流量
      DoBytesAnalysis.getSecBytesData(dates,bytes, ByteSecMap)
      //按小时统计流量
      DoBytesAnalysis.getHourBytesData(dates, bytes, ByteMap)
      //分析URL
      DoURLAnalysis.getURLMap(URL,URLMap)
      //分析请求状态，eg:200, 404
      DoStateAnalysis.getStateMap(state, StateMap)
      //分析IP
      DoIPAnalysis.getIpMap(ip, IpMap)
      //按小时统计请求数量
      DoRequestAnalysis.getRequestHourMap(dates, requestHourMap)
      //按秒统计请求数
      DoRequestAnalysis.getRequestSecMap(dates, requestSecMap)
    }}
    val ipSumVal = DoIPAnalysis.getIpSumValue(IpMap)
    val ipTotalNum = DoIPAnalysis.getIpTotalNum(IpMap)
    val ipRankList = DoIPAnalysis.getIpRank10(IpMap)
    val totalBytes = DoBytesAnalysis.getByteSum(ByteMap)
    val maxURL = DoURLAnalysis.getMaxURL(URLMap)
    val URLRankList = DoURLAnalysis.getURLRank10(URLMap)
    //构造最终的数据对象
    //全部用Json格式保存对象
    val dateResult = DateResult(rowKey, count.toString, ParseMapToJson.map2JsonList(ipRankList), ipSumVal.toString, ipTotalNum.toString,
                                              requestHourMap.toString(), requestSecMap.toString(),
                                              totalBytes.toString, ByteMap.toString(), ByteSecMap.toString(),
                                              ParseMapToJson.map2JsonList(maxURL), ParseMapToJson.map2JsonList(URLRankList),
                                              methodMap.toString(), StateMap.toString())

    //此对象将被用于计算法院总数据
    /**
      * 此处存在疑问，如果用mutable.map而不用List，属性的值无法在外面获取
      */
    val dateResult2 = CourtResult(rowKey, count, ipSumVal, ipTotalNum, ipRankList,
                                              requestHourMap.value.toList, requestSecMap.value.toList, totalBytes,
                                              ByteMap.value.toList, ByteSecMap.value.toList, maxURL,
                                              URLRankList, methodMap.value.toList, StateMap.value.toList)

    finalMap.add(dateResult)
    println("IPSumVal:" +rowKey+ "  :"+ ipSumVal)
    println("reqSum:" + rowKey + ":" + dateResult2.reqSum)
    println("Out foreach:" + ByteMap.value)
    println("Hour Request:" + requestHourMap.value)
    println("Sec Request:" + requestSecMap.value)
    println("Out foreach:" + Bytes.toBytes(ByteSecMap.value.toString()))
    println("SecMap:" + Bytes.toString(Bytes.toBytes(ByteSecMap.value.toString())))
    println("Out foreach:" + DoBytesAnalysis.getByteSum(ByteMap))
    println("MapRank:" + DoIPAnalysis.getIpRank10(IpMap))
    println("IpSum:" + DoIPAnalysis.getIpSumValue(IpMap))
    println("IpSum:" + DoIPAnalysis.getIpTotalNum(IpMap))

    //一轮分析完之后清空累加器
    IpMap.reset()
    URLMap.reset()
    ByteMap.reset()
    ByteSecMap.reset()
    methodMap.reset()
    StateMap.reset()
    requestHourMap.reset()
    requestSecMap.reset()

    //返回值
    dateResult2
  }


}
