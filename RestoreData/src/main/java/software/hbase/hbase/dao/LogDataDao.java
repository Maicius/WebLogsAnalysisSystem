package software.hbase.hbase.dao;

import org.springframework.stereotype.Repository;
import software.hbase.hbase.dataObject.LogData;

@Repository
public class LogDataDao extends BaseHbaseDao<LogData> {
    public LogDataDao() throws NoSuchFieldException, SecurityException{
        super(LogData.class);
    }
}
