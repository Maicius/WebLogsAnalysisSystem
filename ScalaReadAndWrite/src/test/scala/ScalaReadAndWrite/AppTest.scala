package ScalaReadAndWrite

import java.util.Date

import org.junit._
import Assert._
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.Scan
import org.apache.hadoop.hbase.filter.{CompareFilter, RegexStringComparator, RowFilter}
import org.apache.hadoop.hbase.mapred.TableOutputFormat
import org.apache.hadoop.hbase.mapreduce.{TableInputFormat, TableMapReduceUtil}
import org.apache.hadoop.mapred.JobConf
import org.apache.spark.{SparkConf, SparkContext}
import software.analysis.nju.Accumulator.DateResultAccumulator
import software.analysis.nju.analysis.AnalysisByCourt
import software.analysis.nju.constant.SparkProperties
import software.analysis.nju.util.GetDate

@Test
class AppTest {

    @Test
    def testDate() = {
        println(GetDate.getYesterday)
    }

    @Test
    def testSlice() = {
        val str = "20170808-10:10:10"
        println(str.slice(1, 9))
    }

    @Test
    def testAnalysis() = {
        val sparkConf = new SparkConf().setAppName(SparkProperties.SPARK_APP_NAME).setMaster(SparkProperties.SPARK_MASTER)
        val scan: Scan= new Scan()
        val rowRegexp = "20170808200+[0-9]{6}"
        val begin: Date = new Date()
        val sc = new SparkContext(sparkConf)
        val conf = HBaseConfiguration.create()
        val finalMap = new DateResultAccumulator()
        sc.register(finalMap, "FINALMAP")
        conf.set(TableInputFormat.INPUT_TABLE, SparkProperties.LOG_TABLE_NAME)
        val filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new RegexStringComparator(rowRegexp))
        scan.setFilter(filter)
        val scan_str= TableMapReduceUtil.convertScanToString(scan)
        val jobConfig: JobConf = new JobConf(conf, this.getClass)
        //    jobConfig.set("mapreduce.output.fileoutputformat.outputdir", "/")
        jobConfig.setOutputFormat(classOf[TableOutputFormat])
        jobConfig.set(TableOutputFormat.OUTPUT_TABLE, SparkProperties.ANA_TABLE_NAME)
        conf.set(TableInputFormat.SCAN,scan_str)
        AnalysisByCourt.AnalysisByCourtAndDay(sc, conf, "20170808200", jobConfig, finalMap)
        val end: Date = new Date()
        println("分析耗时:" + (end.getTime - begin.getTime))
    }

//    @Test
//    def testKO() = assertTrue(false)

}


