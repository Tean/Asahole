import com.netteans.examples.dubbo.test.interfaces.only.EmbeddedZooKeeper;
import com.netteans.examples.dubbo.test.interfaces.only.ITest;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class BootStrap {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/dubbo-demo-consumer.xml");
        context.start();
        ITest demoService = (ITest) context.getBean("demoService");
        String hello = demoService.test();
        System.out.println(hello);
    }
}
