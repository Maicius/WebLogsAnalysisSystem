package software.analysis.nju.CaptureLog;

import software.analysis.nju.ConfigureManager.ConfigurationManager;
import software.analysis.nju.Util.Util;
import software.analysis.nju.service.impl.HDFSServiceImpl;

import java.io.File;

public class main {
    public static void main(String args[]) throws Exception{
        upLoadThroughHDFS();

    }
    public static void upLoadThroughFTP() throws Exception{
        String hostName = ConfigurationManager.getString("FTP.HostName");
        int port = ConfigurationManager.getInteger("FTP.Port");
        String userName = ConfigurationManager.getString("FTP.UserName");
        String password = ConfigurationManager.getString("FTP.Password");

        String originFileName = Util.getOriginFileName();
        String fileName = ConfigurationManager.getString("FYDM")+ "_" + Util.getFileNamWithoutPath(originFileName);
        System.out.println(fileName);
        String pathName = ConfigurationManager.getString("PATH.RemoteFilePath");
        //上传到FTP服务器
        boolean flag = CaptureLog.uploadFileFromProduction(hostName, port, userName, password, pathName, fileName, originFileName);

        if(flag){
            Util.writeLog("  :    success\n");
        }else{
            Util.writeLog("  :    failed\n");
        }
    }

    public static void upLoadThroughHDFS() throws Exception{
        //原始路径
        String Path = ConfigurationManager.getString("PATH.OriginFilePath");
        //原始文件名（包含路径）
        String originFileName = Util.getOriginFileName();
        //要更改的文件名
        String fileName = ConfigurationManager.getString("FYDM")+ "_" + Util.getFileNamWithoutPath(originFileName);
        File file = new File(originFileName);
        String newFileName = Path + fileName;
        System.out.println("File origin name:" + originFileName);
        String dstName = ConfigurationManager.getString("hadoopIp") + "/tomcatLog/" + fileName;
        try {
            if (file.exists()) {
                boolean changeName = file.renameTo(new File(newFileName));
                //改名成功再上传文件
                if (changeName) {
                    System.out.println("开始上传到HDFS");
                    HDFSServiceImpl hdfsService = new HDFSServiceImpl();
                    hdfsService.copyFromLocalFile(newFileName, dstName);
                    Util.writeLog("  :  " + newFileName + " success\n");
                }
            }else{
                System.out.println("File not exists");
            }
        }catch (Exception e){
            e.printStackTrace();
            Util.writeLog("  :  " + newFileName + " Failec\n");
        }
    }
}
