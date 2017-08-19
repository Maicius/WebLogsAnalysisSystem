package HbaseTest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import software.hbase.service.HBaseService;

public class HbaseConnectionTest {

    public static void main(String args[]) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        HBaseService hbaseService = (HBaseService) ac.getBean("HBaseService");
        try {
            hbaseService.showTables();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
