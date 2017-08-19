package software.hbase.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.hbase.hbase.dao.LogDataDao;
import software.hbase.hbase.dataObject.LogData;
import software.hbase.service.LogDataService;
import software.hbase.util.ParseLogsUtil;

import java.util.Date;
import java.util.List;

@Service("LogDataService")
public class LogDataServiceImpl implements LogDataService {
    @Autowired
    private LogDataDao logDataDao;
    @Override
    public void insertLogs()throws Exception {
        List<LogData> list = ParseLogsUtil.parseLogsUtil("/Users/maicius/RestoreData/src/main/resources/200_localhost_access_log.2017-08-08.txt");
        System.out.println("parse end...\nbegin to insert");
        Date a = new Date();
        logDataDao.saveBatch(list);
        Date b = new Date();
        System.out.println("插入历时:" + (b.getTime() - a.getTime()) + "毫秒");
    }
}
