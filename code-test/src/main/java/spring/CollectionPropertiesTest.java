package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CollectionPropertiesTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-application.xml");

        CollectionProperties a = (CollectionProperties)context.getBean("collectionProperties");
        System.out.println(a.getObjectList().get(0));

    }
}
