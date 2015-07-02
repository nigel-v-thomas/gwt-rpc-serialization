package org.wwarn.surveyor.util.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.SerializationException;
import com.seanchenxi.gwt.storage.client.serializer.StorageSerializer;

import java.io.Serializable;

/**
 * Simple contract, takes isSerializable and returns a string
 */
public class SerializationUtil {
    private static final StorageSerializer storageSerializer = GWT.create(StorageSerializer.class);
    public SerializationUtil() {

    }

    public <T> String serialize(Class<T> aClass, T object1){
        String seralisedOutput = "";
        try {
            seralisedOutput = storageSerializer.serialize(aClass, object1);
        } catch (SerializationException e) {
            throw new IllegalStateException("unable to serialise", e);
        }
        return seralisedOutput;
    }

    public <T> T deserialize(Class<T> testValueClass, String test) {
        T instance;
        try {
            instance = storageSerializer.deserialize(testValueClass, test);
        } catch (SerializationException e) {
            throw new IllegalStateException("unable to serialise", e);
        }
        return instance;
    }
}
