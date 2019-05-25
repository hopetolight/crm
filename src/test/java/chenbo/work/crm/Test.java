package chenbo.work.crm;

import java.util.Random;

public class Test {

    @org.junit.Test
    public void test(){
        Long l = ~(-1L << 5L);
        Long ends = (long) (new Random().nextInt(29) + 1);
        System.out.println(ends);
    }
}
