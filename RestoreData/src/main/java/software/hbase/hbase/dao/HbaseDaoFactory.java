package software.hbase.hbase.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HbaseDaoFactory {
	@Autowired
	private LogDataDao logDataDao;

	public BaseHbaseDao getDao(Class<?> daoType){
		if(daoType.equals(LogDataDao.class)){
			return logDataDao;
		}
		return null;
	}

}
