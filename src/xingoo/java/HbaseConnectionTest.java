package xingoo.java;

import org.apache.log4j.Logger;

/**
 * Created by xinghailong on 2017/6/30.
 */

public class HbaseConnectionTest {
    private static Logger log = Logger.getLogger(HbaseConnectionTest.class);
    private static int size = 10000;

    public static void main(String[] args) throws Exception {
//        System.setProperty("hive.metastore.uris", "thrift://hnode1:9083");
//        SparkConf sparkConf = new SparkConf().setAppName("hbase-conccurent-test").setMaster("local[*]");
//        JavaSparkContext sc = new JavaSparkContext(sparkConf);
//        sc.setLogLevel("WARN");
//        SQLContext sqlContext = new HiveContext(sc);
//
//
//        List<String> phones = sqlContext.sql("select cell_phone from dw.member where his_time='2017-06-29' limit "+size)
//                .toJSON().toJavaRDD().map(JSON::parseObject).map(json -> json.getString("cell_phone")).collect();
//        System.out.println(phones.size());
//        CountDownLatch begin = new CountDownLatch(1);
//        CountDownLatch end = new CountDownLatch(size);
//
//        //设置最大的并发数量为60
//        ExecutorService exec = Executors.newFixedThreadPool(100);
//        phones.forEach(phone -> exec.execute(new ConnectionRequest("phone="+phone, begin, end)));
//
//        long startTime = System.currentTimeMillis();
//
//        begin.countDown();//初始化线程完成，可以开始了！
//        try {
//            end.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            System.out.println("测试完成!");
//            System.out.println("成功条数: " + ConnectionRequest.success);
//            System.out.println("失败条数: " + ConnectionRequest.fail);
//            System.out.println("超时条数: " + ConnectionRequest.timeout);
//            System.out.println("成功比率: " + (ConnectionRequest.success.doubleValue() / size)+"%");
//            long costTime = System.currentTimeMillis() - startTime;
//            System.out.println("总请求处理时间: " + costTime + " ms");
//            System.out.println("平均处理时间（总请求处理时间/条数）: " + costTime / size+ " ms");
//        }
//        exec.shutdown();
//        log.info("main method end");

    }
}
