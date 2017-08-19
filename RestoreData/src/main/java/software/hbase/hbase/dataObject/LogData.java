package software.hbase.hbase.dataObject;

import software.hbase.hbase.HbaseColumn;
import software.hbase.hbase.HbaseTable;

/**
 * 保存原始数据
 */
@HbaseTable(name="LogData", rowKey = "LogId")
public class LogData implements java.io.Serializable{
    public String LogId;

    @HbaseColumn(family = "prop", qualifier = "FYDM")
    public String fydm;

    @HbaseColumn(family = "prop", qualifier = "DATES")
    public String dates;

    @HbaseColumn(family = "prop", qualifier = "IP")
    public String ip;

    @HbaseColumn(family = "prop", qualifier = "URL")
    public String URL;

    @HbaseColumn(family = "prop", qualifier = "STATE")
    public String state;

    @HbaseColumn(family = "prop", qualifier = "METHOD")
    public String methods;

    @HbaseColumn(family = "prop", qualifier = "BYTES")
    public String bytes;

//    public TomcatLogs(){}
//    public TomcatLogs(String fydm, String dates, String LogId, String ip, String method,
//                      String URL, String state, String bytes){
//        this.fydm = fydm;
//        this.dates = dates;
//        this.LogId = LogId;
//        this. ip =ip;
//        this.methods = method;
//        this.URL = URL;
//        this.state  = state;
//        this.bytes = bytes;
//    }
    public LogData(){}
    public LogData(String LogId, String fydm, String dates, String ip, String URL, String state, String methods, String bytes){
        this.LogId = LogId;
        this.fydm = fydm;
        this.dates = dates;
        this.ip = ip;
        this.URL = URL;
        this.state = state;
        this.methods = methods;
        this.bytes = bytes;
    }
    public String getFydm() {
        return fydm;
    }

    public void setFydm(String fydm) {
        this.fydm = fydm;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getLogId() {
        return LogId;
    }

    public void setLogId(String id) {
        this.LogId = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBytes() {
        return bytes;
    }

    public void setBytes(String bytes) {
        this.bytes = bytes;
    }

    public String getMethods() {
        return methods;
    }

    public void setMethods(String methods) {
        this.methods = methods;
    }
}
