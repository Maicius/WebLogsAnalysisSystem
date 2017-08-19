/**
 * 
 */
package software.analysis.nju.service;

import java.io.IOException;


public interface HDFSService {
	public void copyFromLocalFile(String src,String dst) throws Exception;
	
	public byte[] downloadFromHadoop(String src) throws IOException;
}
