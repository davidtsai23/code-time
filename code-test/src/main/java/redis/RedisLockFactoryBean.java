package redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.FactoryBean;

/**
 * FactoryBean
 * 调用getBean("redisLock") 时，Spring通过反射机制发现RedisLockFactoryBean实现了FactoryBean的接口，
 * 这时Spring容器就调用接口方法CarFactoryBean#getObject()方法返回。
 * 如果希望获取CarFactoryBean的实例，
 * 则需要在使用getBean(beanName) 方法时在beanName前显示的加上 "&" 前缀，例如getBean("&redisLock")
 */
public class RedisLockFactoryBean implements FactoryBean<RedissonClient> {

    private String pass;
    private String host;
    private String port;

    public RedissonClient redissonClient;

    @Override
    public RedissonClient getObject() throws Exception {
        Config config = new Config();

        if (pass!=null && !"".equals(pass)){
            config.useSingleServer().setAddress(host + ":" + port).setPassword(pass);
        }else{
            config.useSingleServer().setAddress(host + ":" + port);
        }
        redissonClient = Redisson.create(config);
        return redissonClient;
    }

    public void destroy() {
        redissonClient.shutdown();
    }

    @Override
    public Class<?> getObjectType() {
        return RedissonClient.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
