package software.analysis.nju.service.impl;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import software.analysis.nju.ConfigureManager.ConfigurationManager;
import software.analysis.nju.service.HDFSService;

import java.io.IOException;
public class HDFSServiceImpl implements HDFSService,InitializingBean,DisposableBean{
	private FileSystem fs;

	public void afterPropertiesSet() throws Exception {
		Configuration conf = new Configuration();
		conf.set("mapred.job.tracker", ConfigurationManager.getString("jobTracker"));
		conf.set("fs.default.name", ConfigurationManager.getString("hadoopIp"));
		System.out.println("初始化成功");
		fs = FileSystem.get(conf);
	}

	public void destroy() throws Exception {
		fs.close();
	}
	
	public void copyFromLocalFile(String src,String dst) throws Exception{
		afterPropertiesSet();
		Path srcPath = new Path(src);
		Path dstPath = new Path(dst);
		if(fs.exists(dstPath)){
			return;
		}
		fs.copyFromLocalFile(srcPath, dstPath);
	}

	public byte[] downloadFromHadoop(String src) throws IOException {
		Path srcPath = new Path(src);
		if(fs.exists(srcPath)){
			FSDataInputStream in = fs.open(srcPath);
			FileStatus stat = fs.getFileStatus(srcPath);
			//创建缓冲
			byte[] buffer = new byte[Integer.parseInt(String.valueOf(stat.getLen()))];
			in.readFully(0,buffer);
			in.close();
			return buffer;
		}else{
			throw new IOException("文件不存在！");
		}
	}
	
}
