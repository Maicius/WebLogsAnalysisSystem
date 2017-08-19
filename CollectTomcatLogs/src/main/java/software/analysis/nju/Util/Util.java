package software.analysis.nju.Util;

import software.analysis.nju.ConfigureManager.ConfigurationManager;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

public class Util {
    public static String getYesterday(){
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date time=cal.getTime();
        return (new SimpleDateFormat("yyyy-MM-dd").format(time));
    }

    public static String getFileNamWithoutPath(String fileName){
        StringTokenizer st = new StringTokenizer(fileName, "/");
        String realFileName = "";
        while(st.hasMoreTokens()){
            realFileName = st.nextToken();
        }
        return realFileName;
    }

    /**
     * 获取当前文件夹下tomcat日志文件名
     */
    public static String getOriginFileName(){
        String originPath  = ConfigurationManager.getString("PATH.OriginFilePath");
        String originFileName = originPath + "localhost_access_log."+getYesterday()+".txt";
        System.out.println(originFileName);
        return originFileName;
    }

    //每次上传之后保存日志，日志信息为：日期 + success/failed
    //如果上传失败，则还有保存失败到返回码，比如：530 ->用户名错误
    public static void writeLog(String content) throws Exception {
        String rawContent= new String(); //原有txt内容
        String s1 = new String();//内容更新
        content = getYesterday() + content;
        String fileName = ConfigurationManager.getString("LogFileName");

        File f = new File(fileName);
        boolean newfile = false;
        if (f.exists()) {
            System.out.println("文件存在");
            newfile = true;
        } else {
            System.out.println("文件不存在");
            newfile = f.createNewFile();// 不存在则创建
        }
        BufferedReader input = new BufferedReader(new FileReader(f));

        while ((rawContent = input.readLine()) != null) {
            s1 += rawContent + "\n";
        }
        input.close();
        s1 += content;
        if(newfile) {
            BufferedWriter output = new BufferedWriter(new FileWriter(f));
            output.write(s1);
            output.close();
        }
    }
}
