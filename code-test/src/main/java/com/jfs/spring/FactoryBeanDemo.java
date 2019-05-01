package com.jfs.spring;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author caiweiwei
 * @date 2019-04-17
 * @description 生产NormalBean的工厂Bean
 */
public class FactoryBeanDemo implements FactoryBean<NormalBean> {

    /**
     * 自定义属性
     */
    private int id;
    private String desc;

    /**
     * 返回需要加工或者装饰的bean实例
     * @return
     * @throws Exception
     */
    @Override
    public NormalBean getObject() throws Exception {
        NormalBean normalBean = new NormalBean();
        normalBean.setId(id);
        normalBean.setDesc(desc);
        return normalBean;
    }

    @Override
    public Class<?> getObjectType() {
        return NormalBean.class;
    }

    /**
     * 生产的bean是否为单例对象
     * @return
     */
    @Override
    public boolean isSingleton() {
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
