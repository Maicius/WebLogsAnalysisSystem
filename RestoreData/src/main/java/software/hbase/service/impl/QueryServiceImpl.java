package software.hbase.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.hbase.Entity.ConditionQueryData;
import software.hbase.hbase.dao.LogAnaDao;
import software.hbase.hbase.dataObject.LogAna;
import software.hbase.service.QueryService;
import software.hbase.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class QueryServiceImpl implements QueryService{

    @Autowired
    private LogAnaDao logAnaDao;

    @Override
    public String queryAllData() throws Exception {
        String yesterday = DateUtil.getYesterday();
        //000表示一天中所有法院的数据的代码
//        String rowKey = yesterday + "000";
        String rowKey = "20170808" + "000";
        LogAna dataSets = logAnaDao.findById(rowKey);
        //将bytes转为MB
        int totalBytes = Integer.parseInt(dataSets.getToTalBytes());
        totalBytes = totalBytes / 1024 / 1024;
        dataSets.setToTalBytes(String.valueOf(totalBytes));
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(dataSets);
        return json;
    }

    @Override
    public List<LogAna> conditionQuery(ConditionQueryData data) throws Exception {
        String yesterday = DateUtil.getYesterday();
        //        String rowKey = yesterday + "000";
        String rowKey = "";
        List<String> rowKeys = new ArrayList<>();
        for(String court: data.getCourtList()){
//            rowKey = data.getDateBegin() + court;
            rowKey = "20170808" + court;
            rowKeys.add(rowKey);
        }
        List<LogAna> list = logAnaDao.findByIds(rowKeys);

        return list;
    }

}
