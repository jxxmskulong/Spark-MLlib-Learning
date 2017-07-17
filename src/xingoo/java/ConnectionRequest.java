package xingoo.java;

import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionRequest implements Runnable {
    public static AtomicInteger success = new AtomicInteger(0);
    public static AtomicInteger fail = new AtomicInteger(0);
    public static AtomicInteger timeout = new AtomicInteger(0);
    private final String hostString = "http://recommend.test.66buy.com.cn/publics/recommend/listing/query?";
    private String param;

    private CountDownLatch begin;
    private CountDownLatch end;

    ConnectionRequest(String param, CountDownLatch begin,CountDownLatch end) {
        this.param = param;
        this.begin = begin;
        this.end = end;
    }

    @Override
    public void run() {
        URL url;
        try {
            begin.await();//等待所有的线程初始化完成后，才开始执行
            url = new URL(hostString + param);
            URLConnection URLconnection = url.openConnection();

            HttpURLConnection connection = (HttpURLConnection) URLconnection;
            connection.setConnectTimeout(3000);
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                success.addAndGet(1);
//                IOUtils.readLines(connection.getInputStream()).forEach(line -> System.out.println(line));
            } else {
                fail.addAndGet(1);
            }
        } catch (SocketTimeoutException e) {
            timeout.addAndGet(1);
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            end.countDown();
        }

    }

}