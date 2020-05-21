import com.netteans.examples.dubbo.test.interfaces.only.EmbeddedZooKeeper;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class BootStrap {
    public static void main(String[] args) {
         new EmbeddedZooKeeper(12181, false).start();
        // wait for embedded zookeeper start completely.
        ClassPathXmlApplicationContext context = null;
        try {
            Thread.sleep(1000);
            context = new ClassPathXmlApplicationContext("spring/dubbo-demo-provider.xml");
            context.start();
            System.out.println("dubbo service started");
            System.in.read();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            if (context != null)
                context.close();
        }
    }
}
