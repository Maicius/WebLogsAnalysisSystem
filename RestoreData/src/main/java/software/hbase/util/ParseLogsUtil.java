package software.hbase.util;

import software.hbase.hbase.dataObject.LogData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.*;

public class ParseLogsUtil {

    public static List<LogData> parseLogsUtil(String fileName) throws Exception {
        List<LogData> logList = new ArrayList<>();

        FileReader reader = new FileReader(fileName);
        BufferedReader br = new BufferedReader(reader);
        String str = null;
        int iplength = 0;
        String ip = null;
        int timebegin = 0;
        int timeend = 0;
        String date = null;
        Date dates;
        String method = null;
        int methodend = 0;
        String url = null;
        int urlend = 0;
        int statebegin = 0;
        String state = null;
        int stateend = 0;
        String bytes = null;
        String sql = null;
        String fydm = "120100 210";
        int id = 100000;
        Date d = new Date();
        System.out.println(d);
        while ((str = br.readLine()) != null) {
            // 每行一条记录  分析记录写入数据库
            //ip
            //				str = br.readLine();
            iplength = str.indexOf("-");
            ip = str.substring(0, iplength - 1);
            //				System.out.println("ip:"+ip);
            //time
            timebegin = str.indexOf("[");
            timeend = str.indexOf("+0800]");
            date = str.substring(timebegin + 1, timeend - 1);
            SimpleDateFormat sf = new SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss", Locale.ENGLISH);
            dates = sf.parse(date);
            //				System.out.println("date:"+dates);
            //method
            str = str.substring(timeend + 8);
            //				System.out.println(str);
            methodend = str.indexOf("/");
            method = str.substring(0, methodend - 1);
            //				System.out.println(method);
            urlend = str.indexOf("HTTP/1.1");
            url = str.substring(methodend + 1, urlend);
            url = url.trim();
            if (url.contains("tjspxt")) {
                if (url.length() == 6) {
                    continue;
                }
                url = url.substring(7);
            }
            if (url.contains("jsessionid=")) {
                url = url.substring(0, url.indexOf("jsessionid=") - 1);
            }
            if (url.contains("resources")) {
                //包含资源请求的过滤掉
                continue;
            } else if (url.equals("") || url == "") {
                //过滤空白url
                url = "login.do";
            //					//continue;
            } else if (url.indexOf("?") > 0) {
                url = url.substring(0, url.indexOf("?"));
            }
            //过滤部分url
            if (url.equals("gjShow.aj") || url.equals("getQxtxCount.aj") || url.equals("qnsjajCount.aj")
                    || url.equals("wjajCount.aj") || url.equals("csxajCount.aj") || url.equals("getLctxCountWithoutIgnored.aj")
                    || url.equals("favicon.ico") || url.equals("connectDetection.aj")) {
                continue;
            }
            //				System.out.println(url);
            str = str.substring(urlend + 1);
            statebegin = str.indexOf("HTTP/1.1");
            str = str.substring(statebegin + 10);
            //				System.out.println(str);
            stateend = str.indexOf(" ");
            state = str.substring(0, stateend);
            //				System.out.println(state);
            bytes = str.substring(stateend + 1);
            //				System.out.println(bytes);

            //再转换为sql.Date对象
            java.sql.Timestamp d2 = new java.sql.Timestamp(dates.getTime());

            //赋值给logData
            LogData logData = new LogData();
            logData.setLogId(getRowKeyPart1(fileName) + id);
            logData.setFydm(fydm);
            logData.setState(state);
            logData.setIp(ip);
            logData.setURL(url);
            logData.setBytes(bytes);
            logData.setMethods(method);
            logData.setDates(d2.toString());
            logList.add(logData);
            //sql = "insert into ACCESSLOG values('"+fydm+"','"+d2+"',"+id+",'"+ip+"','"+method+"','"+url+"','"+state+"','"+bytes+"')";
            id++;
        }
        Date d1 = new Date();
        System.out.println(d1);
        br.close();
        reader.close();
        return logList;
    }

    public static String getRowKeyPart1(String fileName){
        StringTokenizer st = new StringTokenizer(fileName, "/");
        String realFileName = "";
        while(st.hasMoreTokens()){
            realFileName = st.nextToken();
        }
        String fydm = realFileName.substring(0, 3);
        return  (getShortDate(realFileName.substring(25, 35)) + fydm);
    }

    public static String getShortDate(String date){

        StringTokenizer st = new StringTokenizer(date, "-");
        String shortDate = "";
        while(st.hasMoreTokens()){
            shortDate +=st.nextToken();
        }
        return shortDate;
    }
}
