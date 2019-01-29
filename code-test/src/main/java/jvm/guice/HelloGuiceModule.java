package jvm.guice;

import com.google.inject.Binder;
import com.google.inject.Module;

public class HelloGuiceModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind(HelloGuice.class).to(HelloGuiceImpl.class);
    }
}
