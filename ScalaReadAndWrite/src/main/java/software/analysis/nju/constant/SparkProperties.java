package software.analysis.nju.constant;

public interface SparkProperties {
    String SPARK_MASTER = "local[2]";
    String SPARK_APP_NAME = "TomcatLogAnalysis";

    //Table Log Data
    byte[] LOG_CF = "prop".getBytes();
    String LOG_TABLE_NAME = "LogData";
    byte[] DATE= "DATES".getBytes();
    byte[] STATE = "STATE".getBytes();
    byte[] METHOD = "METHOD".getBytes();
    byte[] BYTES = "BYTES".getBytes();
    byte[] IP = "IP".getBytes();

    //Table Log Analysis
    String ANA_TABLE_NAME = "LogAna";
    byte[] IP_CF = "IP".getBytes();
    byte[] BYTES_CF = "BYTES".getBytes();
    byte[] URL_CF = "URL".getBytes();
    byte[] REQ_CF = "REQ".getBytes();
    byte[] ME_STATE_CF = "METHOD_STATE".getBytes();
    byte[] ReqSum = "ReqSum".getBytes();
    byte[] IPList = "IPList".getBytes();
    byte[] IPSumVal = "IPSumVal".getBytes();
    byte[] IPTotalNum = "IPTotalNum".getBytes();
    byte[] TotalBytes = "TotalBytes".getBytes();
    byte[] BytesHourList = "BytesHourList".getBytes();
    byte[] BytesSecList = "BytesSecList".getBytes();
    byte[] MaxURL = "MaxURL".getBytes();
    byte[] URLList = "URLList".getBytes();
    byte[] MethodList = "MethodList".getBytes();
    byte[] StateList = "StateList".getBytes();
    byte[] ReqHourList = "ReqHourList".getBytes();
    byte[] RegSecList = "ReqSecList".getBytes();

}
