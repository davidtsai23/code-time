package com.jfs.jvm;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PropertyChangeListenerTest {

    private String a;

    PropertyChangeSupport listeners = new PropertyChangeSupport(this);

    public PropertyChangeListenerTest() {
        this.a = "a";
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        //发布监听事件
        firePropertyChange("a", "a", "b");
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        listeners.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener){
        listeners.addPropertyChangeListener(listener);
    }


    /**
     * 触发属性改变的事件
     */
    protected void firePropertyChange(String prop, Object oldValue, Object newValue) {
        listeners.firePropertyChange(prop, oldValue, newValue);
    }


    public static void main(String[] args) {
        PropertyChangeListenerTest beans = new PropertyChangeListenerTest();
        beans.addPropertyChangeListener(new PropertyChangeListener(){

            public void propertyChange(PropertyChangeEvent evt) {
                System.out.println("OldValue:"+evt.getOldValue());
                System.out.println("NewValue:"+evt.getNewValue());
                System.out.println("tPropertyName:"+evt.getPropertyName());
            }});
        beans.setA("test");
    }
}
