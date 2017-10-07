package software.analysis.nju.analysis

import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.Scan
import org.apache.hadoop.hbase.filter.{CompareFilter, RegexStringComparator, RowFilter}
import org.apache.hadoop.hbase.mapred.TableOutputFormat
import org.apache.hadoop.hbase.mapreduce.{TableInputFormat, TableMapReduceUtil}
import org.apache.hadoop.mapred.JobConf
import org.apache.spark.{SparkConf, SparkContext}
import software.analysis.nju.Accumulator.{AllDataAccumulator, DateResultAccumulator}
import software.analysis.nju.DAO.ParseObjectToPut
import software.analysis.nju.Entity.Entity.CourtResult
import software.analysis.nju.constant.SparkProperties
import software.analysis.nju.util._

object Analysis {

  def main(args: Array[String]): Unit = {

    //获取昨天的日期和法院编号，用于构造正则表达式
    val yesterday: String = "20170808"
    val courtList: List[String] = CourtInfo.getCourtMap.values.toList
    val sparkConf = new SparkConf().setAppName(SparkProperties.SPARK_APP_NAME).setMaster(SparkProperties.SPARK_MASTER)
    val scan: Scan= new Scan()
    val sc = new SparkContext(sparkConf)
    val conf = HBaseConfiguration.create()
    conf.set(TableInputFormat.INPUT_TABLE, SparkProperties.LOG_TABLE_NAME)
    conf.set(TableOutputFormat.OUTPUT_TABLE, SparkProperties.ANA_TABLE_NAME)
    val jobConfig: JobConf = new JobConf(conf, this.getClass)
    jobConfig.setOutputFormat(classOf[TableOutputFormat])
    jobConfig.set(TableOutputFormat.OUTPUT_TABLE, SparkProperties.ANA_TABLE_NAME)

    val finalMap = new DateResultAccumulator()
    val resultMap = new AllDataAccumulator()
    sc.register(finalMap, "finalMap")
    sc.register(resultMap, "ResultMap")
    for (court <- courtList) {
      //根据正则表达式匹配RowKey
      val rowRegexp = yesterday + court + "+[0-9]{6}"
      val filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new RegexStringComparator(rowRegexp))
      scan.setFilter(filter)
      val scan_str= TableMapReduceUtil.convertScanToString(scan)
      conf.set(TableInputFormat.SCAN,scan_str)
      val eachResult: CourtResult = AnalysisByCourt.AnalysisByCourtAndDay(sc, conf, yesterday + court, jobConfig, finalMap)
      resultMap.add(eachResult)
    }

    //000表示全部法院
    val rowKey = yesterday + "000"
    //计算所有法院数据，计算结果存储在finalMap里
    AnalysisAllCourt.getAllCourtData(resultMap, rowKey, finalMap)
    //生成RDD
    val initRdd = sc.makeRDD(finalMap.value.values.toArray)
    //存储数据到Hbase
    initRdd.map(dateResult => ParseObjectToPut.putDateResult(dateResult)).saveAsHadoopDataset(jobConfig)

    resultMap.reset()
    finalMap.reset()
    sc.stop()
  }

}
