package org.wwarn.surveyor.util.client;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

/**
 * Created by nigelthomas on 01/07/2015.
 */
public class SimplePojo implements IsSerializable, Serializable {
    private String foo = "foo";
    private String bar = "bar";

    public SimplePojo(String foo, String bar) {
        this.foo = foo;
        this.bar = bar;
    }

    private SimplePojo() {
    }

    public String getFoo() {
        return foo;
    }

    public String getBar() {
        return bar;
    }
}
