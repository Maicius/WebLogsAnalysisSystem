package software.analysis.nju.DAO

import org.apache.hadoop.hbase.client.Put
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.util.Bytes
import software.analysis.nju.Entity.Entity.DateResult
import software.analysis.nju.constant.SparkProperties

object ParseObjectToPut {
  def putDateResult(dateResult: DateResult): (ImmutableBytesWritable, Put) = {
    val put = new Put(Bytes.toBytes(dateResult.rowK))
    // add to column family data, column  data values to put object
    put.add(SparkProperties.BYTES_CF, SparkProperties.BytesHourList, Bytes.toBytes(dateResult.BytesHourList))
    put.add(SparkProperties.BYTES_CF, SparkProperties.BytesSecList, Bytes.toBytes(dateResult.BytesSecList))
    put.add(SparkProperties.BYTES_CF, SparkProperties.TotalBytes, Bytes.toBytes(dateResult.TotalBytes))
    put.add(SparkProperties.IP_CF, SparkProperties.IPList, Bytes.toBytes(dateResult.IPList))
    put.add(SparkProperties.IP_CF, SparkProperties.IPSumVal, Bytes.toBytes(dateResult.IPSumVal))
    put.add(SparkProperties.IP_CF, SparkProperties.IPTotalNum, Bytes.toBytes(dateResult.IPTotalNum))
    put.add(SparkProperties.REQ_CF, SparkProperties.ReqSum, Bytes.toBytes(dateResult.reqSum))
    put.add(SparkProperties.REQ_CF, SparkProperties.ReqHourList, Bytes.toBytes(dateResult.ReqHourList))
    put.add(SparkProperties.REQ_CF, SparkProperties.RegSecList, Bytes.toBytes(dateResult.ReqSecList))
    put.add(SparkProperties.URL_CF, SparkProperties.MaxURL, Bytes.toBytes(dateResult.MaxURL))
    put.add(SparkProperties.URL_CF, SparkProperties.URLList, Bytes.toBytes(dateResult.URLList))
    put.add(SparkProperties.ME_STATE_CF, SparkProperties.StateList, Bytes.toBytes(dateResult.State))
    put.add(SparkProperties.ME_STATE_CF, SparkProperties.MethodList, Bytes.toBytes(dateResult.Method))
    (new ImmutableBytesWritable(Bytes.toBytes(dateResult.rowK)), put)
  }
}
