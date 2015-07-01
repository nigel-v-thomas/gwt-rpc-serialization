package org.wwarn.surveyor.util.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.SerializationException;
import com.seanchenxi.gwt.storage.client.serializer.StorageSerializer;

public class SerializationUtilGwtTest extends GWTTestCase {

    @Override
    public String getModuleName() {
        return "org.wwarn.surveyor.util.SerializationUtilTest";
    }

    public void testSerializationUtil() throws Exception {
        delayTestFinish(5000);
        SerializationUtil storageSerializer = GWT.create(SerializationUtil.class);
        final TestValue testValue = new TestValue("foo");
        String serializedString = storageSerializer.serialize(TestValue.class, testValue);
        assertNotNull(serializedString);
        final SimplePojo object = new SimplePojo("foo", "bar");
        serializedString = storageSerializer.serialize(SimplePojo.class, object);
        assertNotNull(serializedString);
        finishTest();

    }

    public void testSerializerDirectly() throws SerializationException {
        StorageSerializer storageSerializer =
                GWT.create(StorageSerializer.class)
//                new com.seanchenxi.gwt.storage.client.serializer.StorageRPCSerializerImpl()
                ;


        delayTestFinish(5000);
        final TestValue testValue = new TestValue("foo");
        String serializedString = storageSerializer.serialize(TestValue.class, testValue);
        assertNotNull(serializedString);
        final SimplePojo object = new SimplePojo("foo", "bar");
        serializedString = storageSerializer.serialize(SimplePojo.class, object);
        assertNotNull(serializedString);
        finishTest();
    }
}
