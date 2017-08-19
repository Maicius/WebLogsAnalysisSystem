package software.hbase.service;

import software.hbase.Entity.ConditionQueryData;
import software.hbase.hbase.dataObject.LogAna;

import java.util.List;

public interface QueryService {
    String queryAllData() throws Exception;
    List<LogAna> conditionQuery(ConditionQueryData data) throws Exception;
}
