package HbaseTest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import software.hbase.hbase.dao.LogDataDao;
import software.hbase.hbase.dataObject.LogData;
import software.hbase.util.ParseLogsUtil;

import java.util.Date;
import java.util.List;

public class HbaseBatchInsertTest {
    public static void main(String args[]) throws Exception{
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        LogDataDao logDataDao = (LogDataDao) ac.getBean("logDataDao");
        System.out.println("begin to parse....");
        List<LogData> list = ParseLogsUtil.parseLogsUtil("/Users/maicius/RestoreData/src/main/resources/200_localhost_access_log.2017-08-08.txt");
        System.out.println("parse end...\nbegin to insert");
        Date a = new Date();
        logDataDao.saveBatch(list);
        Date b = new Date();
        System.out.println("插入历时:" + (b.getTime() - a.getTime()) + "毫秒");
    }
}
