package xingoo.scala;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by xinghailong on 2017/6/28.
 */
public class IterTest {
    public static void main(String[] args) {
        Iterable<String> iter = new MySet();
//        List list = (List)iter;
        List list = Arrays.asList(iter);
    }
}

class MySet implements  Iterable<String> {

    @Override
    public Iterator<String> iterator() {
        return null;
    }
}
