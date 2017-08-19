package HbaseTest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import software.hbase.hbase.dao.LogDataDao;
import software.hbase.hbase.dataObject.LogData;
import software.hbase.service.HBaseService;

public class HbaseInsertTest {
    public static void main(String args[]) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        HBaseService hbaseService = (HBaseService) ac.getBean("HBaseService");
        LogDataDao logDataDao = (LogDataDao) ac.getBean("logDataDao");
        try {
            LogData logData = new LogData();
            logData.setLogId("TJGYTEST");
            logData.setBytes("8555");
            logData.setDates("2017-08-09");
            logData.setFydm("TEST");
            logData.setIp("192.168.1.1");
            logData.setMethods("GET");
            logData.setURL("/login.action");
            logData.setState("200");
            logDataDao.save(logData);
            System.out.println("插入成功");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
