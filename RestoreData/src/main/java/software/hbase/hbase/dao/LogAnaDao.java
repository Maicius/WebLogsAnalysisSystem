package software.hbase.hbase.dao;

import org.springframework.stereotype.Repository;
import software.hbase.hbase.dataObject.LogAna;

@Repository
public class LogAnaDao extends BaseHbaseDao<LogAna> {

    public LogAnaDao() throws NoSuchFieldException, SecurityException {
        super(LogAna.class);
    }
}
