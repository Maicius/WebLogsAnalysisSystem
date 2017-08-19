package software.hbase.Entity;

public class QueryData {
    private Long reqSum;
    private String IPList;
    private String IPSumVa;
    private String IPTotalNum;
    private String TotoBytes;
    private String ReqHourList;
    private String ReqSecList;
    private String MaxURL;
    private String Method;
    private String State;
    private String URLList;
    private String rowK;
    private String BytesSecList;
    private String BytesHourList;

    public Long getReqSum() {
        return reqSum;
    }

    public void setReqSum(Long reqSum) {
        this.reqSum = reqSum;
    }

    public String getIPList() {
        return IPList;
    }

    public void setIPList(String IPList) {
        this.IPList = IPList;
    }

    public String getIPSumVa() {
        return IPSumVa;
    }

    public void setIPSumVa(String IPSumVa) {
        this.IPSumVa = IPSumVa;
    }

    public String getIPTotalNum() {
        return IPTotalNum;
    }

    public void setIPTotalNum(String IPTotalNum) {
        this.IPTotalNum = IPTotalNum;
    }

    public String getTotoBytes() {
        return TotoBytes;
    }

    public void setTotoBytes(String totoBytes) {
        TotoBytes = totoBytes;
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

    public String getMaxURL() {
        return MaxURL;
    }

    public void setMaxURL(String maxURL) {
        MaxURL = maxURL;
    }

    public String getMethod() {
        return Method;
    }

    public void setMethod(String method) {
        Method = method;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getURLList() {
        return URLList;
    }

    public void setURLList(String URLList) {
        this.URLList = URLList;
    }

    public String getRowK() {
        return rowK;
    }

    public void setRowK(String rowK) {
        this.rowK = rowK;
    }

    public String getBytesSecList() {
        return BytesSecList;
    }

    public void setBytesSecList(String bytesSecList) {
        BytesSecList = bytesSecList;
    }

    public String getBytesHourList() {
        return BytesHourList;
    }

    public void setBytesHourList(String bytesHourList) {
        BytesHourList = bytesHourList;
    }
}
