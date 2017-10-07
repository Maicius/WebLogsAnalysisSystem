package HbaseTest;

import software.hbase.hbase.dataObject.LogData;
import software.hbase.util.ParseLogsUtil;

import java.util.Date;
import java.util.List;

public class ParseLogTest {
    public static void main(String args[]) throws Exception{
        testGetRowKeyPart1();
        testParseLog();
    }

    public static void testGetRowKeyPart1() throws Exception{
        System.out.println(ParseLogsUtil.getRowKeyPart1("/Users/maicius/RestoreData/src/main/resources/200_localhost_access_log.2017-08-23.txt"));
    }
    public static void testParseLog() throws Exception{
        Date begin = new Date();
        List<LogData> list = ParseLogsUtil.parseLogsUtil("/Users/maicius/RestoreData/src/main/resources/200_localhost_access_log.2017-08-23.txt");
        for(LogData logData: list){
            System.out.println("fydm: " + logData.getFydm() + "\t" +"date:"+logData.getDates()+"\t" +
                    "id:" + logData.getLogId() + "\t" + "ip" + logData.getIp() + "\t" +
                    "URL:" + logData.getURL() + "\t" + "method" + logData.getMethods() + "\t" +
                    "bytes:" + logData.getBytes() + "\t" + "" + "state:" + logData.getState());
        }
        Date end = new Date();
        System.out.println("Total Parse Lines:" + list.size());
        System.out.println("Total Time:" + (end.getTime() - begin.getTime()) + "mill sec");
    }
}
