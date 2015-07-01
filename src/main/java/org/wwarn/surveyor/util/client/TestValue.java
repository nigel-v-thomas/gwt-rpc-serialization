package org.wwarn.surveyor.util.client;

import java.io.Serializable;

/**
 * Created by nigelthomas on 01/07/2015.
 */
public class TestValue implements Serializable {

    private String value;

    public TestValue() {
    }

    public TestValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestValue)) return false;

        TestValue testValue = (TestValue) o;

        return value == null ? (testValue.value == null) : value.equals(testValue.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
