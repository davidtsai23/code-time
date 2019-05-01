package spring;

import com.jfs.spring.FactoryBeanDemo;
import com.jfs.spring.NormalBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-config.xml");
        NormalBean normalBean = (NormalBean) context.getBean("normal");
        System.out.println(normalBean);

        FactoryBeanDemo factoryBeanDemo = (FactoryBeanDemo) context.getBean("&factory");
        System.out.println(factoryBeanDemo);

        NormalBean factory = (NormalBean) context.getBean("factory");
        System.out.println(factory);
        NormalBean factory2 = (NormalBean) context.getBean("factory");
        System.out.println(factory.equals(factory2));
    }
}
