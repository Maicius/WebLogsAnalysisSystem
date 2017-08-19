package software.hbase.hbase.dataObject;

import software.hbase.hbase.HbaseColumn;
import software.hbase.hbase.HbaseTable;

/**
 * 保存分析结果的实体类
 */
@HbaseTable(name="LogAna", rowKey = "dateCourt")
public class LogAna implements java.io.Serializable {

    public String dateCourt;

    @HbaseColumn(family = "REQ", qualifier = "ReqSum")
    public String ReqSum;

    @HbaseColumn(family = "IP", qualifier = "IPList")
    public String IPList;

    @HbaseColumn(family = "IP", qualifier = "IPSumVal")
    public String IPSumVal;
    @HbaseColumn(family = "IP", qualifier = "IPTotalNum")
    public String IPTotalNum;

    @HbaseColumn(family = "BYTES", qualifier = "TotalBytes")
    public String TotalBytes;

    @HbaseColumn(family = "BYTES", qualifier = "BytesHourList")
    public String BytesHourList;

    @HbaseColumn(family = "BYTES", qualifier = "BytesSecList")
    public String BytesSecList;

    @HbaseColumn(family = "URL", qualifier = "MaxURL")
    public String MaxURL;

    @HbaseColumn(family = "URL", qualifier = "URLList")
    public String URLList;

    @HbaseColumn(family = "METHOD_STATE", qualifier = "MethodList")
    public String MethodList;

    @HbaseColumn(family = "METHOD_STATE", qualifier = "StateList")
    public String StateList;

    @HbaseColumn(family = "REQ", qualifier = "ReqHourList")
    public String ReqHourList;

    @HbaseColumn(family = "REQ", qualifier = "ReqSecList")
    public String ReqSecList;

    public LogAna(){}

    public String getDateCourt() {
        return dateCourt;
    }

    public void setDateCourt(String dateCourt) {
        this.dateCourt = dateCourt;
    }

    public String  getReqSum() {
        return ReqSum;
    }

    public void setReqSum(String  reqSum) {
        ReqSum = reqSum;
    }

    public String getIPList() {
        return IPList;
    }

    public void setIPList(String IPList) {
        this.IPList = IPList;
    }

    public String getIPSumVal() {
        return IPSumVal;
    }

    public void setIPSumVal(String IPSumVal) {
        this.IPSumVal = IPSumVal;
    }

    public String  getIPTotalNum() {
        return IPTotalNum;
    }

    public void setIPTotalNum(String IPTotalNum) {
        this.IPTotalNum = IPTotalNum;
    }

    public String  getToTalBytes() {
        return TotalBytes;
    }

    public void setToTalBytes(String  toTalBytes) {
        TotalBytes = toTalBytes;
    }

    public String getBytesHourList() {
        return BytesHourList;
    }

    public void setBytesHourList(String bytesHourList) {
        BytesHourList = bytesHourList;
    }

    public String getBytesSecList() {
        return BytesSecList;
    }

    public void setBytesSecList(String bytesSecList) {
        BytesSecList = bytesSecList;
    }

    public String getMaxURL() {
        return MaxURL;
    }

    public void setMaxURL(String maxURL) {
        MaxURL = maxURL;
    }

    public String getURLList() {
        return URLList;
    }

    public void setURLList(String URLList) {
        this.URLList = URLList;
    }

    public String getMethodList() {
        return MethodList;
    }

    public void setMethodList(String methodList) {
        MethodList = methodList;
    }

    public String getStateList() {
        return StateList;
    }

    public void setStateList(String stateList) {
        StateList = stateList;
    }

    public String getReqHourList() {
        return ReqHourList;
    }

    public void setReqHourList(String reqHourList) {
        ReqHourList = reqHourList;
    }

    public String getReqSecList() {
        return ReqSecList;
    }

    public void setReqSecList(String reqSecList) {
        ReqSecList = reqSecList;
    }
}
