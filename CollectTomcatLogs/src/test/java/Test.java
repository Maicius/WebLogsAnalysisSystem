import software.analysis.nju.Util.Util;

public class Test {
    public static void main(String args[]){
        testGetYesterDay();
        testGetFileName();
        Util.getOriginFileName();
    }
    private static void testGetYesterDay(){
        System.out.println(Util.getYesterday());
    }

    private static void testGetFileName(){
        System.out.println(Util.getFileNamWithoutPath("/Users/maicius/RestoreData/src/main/resources/200_localhost_access_log.2017-08-08.txt"));
    }

}

