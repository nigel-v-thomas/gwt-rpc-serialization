/*
 * Copyright 2013 Xi CHEN
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wwarn.surveyor.util.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.seanchenxi.gwt.storage.client.StorageChangeEvent;
import com.seanchenxi.gwt.storage.client.StorageExt;
import com.seanchenxi.gwt.storage.client.StorageKey;
import com.seanchenxi.gwt.storage.client.StorageKeyFactory;
import com.seanchenxi.gwt.storage.client.serializer.StorageSerializer;
import org.jetbrains.annotations.NotNull;
import org.wwarn.surveyor.client.core.*;
import org.wwarn.surveyor.client.model.DataSourceProvider;
import org.wwarn.surveyor.client.model.DatasourceConfig;
import org.wwarn.surveyor.util.client.service.TestService;
import org.wwarn.surveyor.util.client.service.TestServiceAsync;
import org.wwarn.surveyor.util.shared.RpcTestMapKey;
import org.wwarn.surveyor.util.shared.RpcTestMapValue;
import org.wwarn.surveyor.util.shared.RpcTestValue;

import java.util.*;

/**
 * Created by: Xi
 */
public class StorageTest implements EntryPoint {
//    private static KeyProviderGetter KP;
//    private static final KeyFactoryGetter KG = new KeyFactoryGetter();

//    private static final TestServiceAsync TEST_SERVICE = GWT.create(TestService.class);
    private static final StorageSerializer OBJ_SERIALIZER = GWT.create(StorageSerializer.class);
    private static final SerializationUtil storageSerializer = GWT.create(SerializationUtil.class);

    public void onModuleLoad() {
//        KP = GWT.create(KeyProviderGetter.class);

//        testStorage(false, StorageExt.getLocalStorage(), new AsyncCallback<Boolean>() {
//            @Override
//            public void onFailure(Throwable throwable) {
//                throw new IllegalStateException(throwable);
//            }
//
//            @Override
//            public void onSuccess(Boolean aBoolean) {
//                testSerializationUtil();
//
//            }
//        });
        testSerializer();
        testSerializationUtil();
        testWithSurveyorCoreDataStructures();

//        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
//            @Override
//            public void execute() {
//                testSerializer();
//            }
//        });
    }

    private void testSerializer() {
        try {
            StorageTestUtil.traceEmptyLine();
            TestValue testObject1 = new TestValue("test");
            String test = OBJ_SERIALIZER.serialize(TestValue.class, testObject1);
            StorageTestUtil.trace(test, false);
            TestValue testObject2 = OBJ_SERIALIZER.deserialize(TestValue.class, test);
            StorageTestUtil.assertEquals("Serializer test", testObject1, testObject2);
        } catch (Exception e) {
            GWT.log("testSerializer - error", e);
        }
    }

    private void testSerializationUtil() {
        try {
            StorageTestUtil.traceEmptyLine();
            TestValue testObject1 = new TestValue("test");
            String test = storageSerializer.serialize(TestValue.class, testObject1);
            StorageTestUtil.trace(test, false);
            TestValue testObject2 = storageSerializer.deserialize(TestValue.class, test);
            StorageTestUtil.assertEquals("SerializationUtil test", testObject1, testObject2);
        } catch (Exception e) {
            GWT.log("testSerializer - error", e);
        }
    }

    public DataSchema fetchSampleDataSchema() {
        String dataSourceType = DataSourceProvider.LocalClientSideDataProvider.name();
        DatasourceConfig dataSourceConfig = new DatasourceConfig("uniqueID","f", dataSourceType);
        DataSchema schema = new DataSchema(dataSourceConfig);
        schema.addField("PY", DataType.DateYear);
        schema.addField("PUB", DataType.String);
        schema.addField("PTN", DataType.String);
        schema.addField("CLAT", DataType.CoordinateLat);
        schema.addField("CLON", DataType.CoordinateLon);
        schema.addField("PID", DataType.Integer);
        schema.addField("QI", DataType.String);
        schema.addField("SD", DataType.Date);
        return schema;
    }


    private QueryResult getTestQueryResult(){

        final RecordList recordLists = fetchSampleRecordList();
        FacetList facetList = fetchSampleFacetFields();
        final QueryResult queryResult = new QueryResult(recordLists, facetList);;
        return queryResult;
    }

    @NotNull
    private FacetList fetchSampleFacetFields() {
        return new FacetList();
    }

    @NotNull
    private RecordList fetchSampleRecordList() {
        final RecordListBuilder recordListBuilder = new RecordListBuilder(RecordListBuilder.CompressionMode.CANONICAL_WITH_INVERTED_INDEX, fetchSampleDataSchema());
        for (int i = 0; i < 7; i++) {
            recordListBuilder.addRecord("200"+i,"2b","3c","4d","5e","6f","7g", "180"+i);
        }
        return recordListBuilder.createRecordList("someUniqueID-asdfnasasdfljasdf");
    }

    private void testWithSurveyorCoreDataStructures() {
        testWithSurveyorCoreDataSchema();
        testWithSurveyorCoreRecordList();
        testWithSurveyorCoreQueryResult();
    }

    private void testWithSurveyorCoreDataSchema() {
        try {
            StorageTestUtil.traceEmptyLine();
            DataSchema testObject1 = fetchSampleDataSchema();
            String test = storageSerializer.serialize(DataSchema.class, testObject1);
            StorageTestUtil.trace(test, false);
            DataSchema testObject2 = storageSerializer.deserialize(DataSchema.class, test);
            StorageTestUtil.assertEquals("Surveyor core DataSchema test", testObject1, testObject2);
        } catch (Exception e) {
            GWT.log("testSerializer - error", e);
        }
    }

    private void testWithSurveyorCoreRecordList() {
        try {
            StorageTestUtil.traceEmptyLine();
            RecordList testObject1 = fetchSampleRecordList();
            String test = storageSerializer.serialize(RecordList.class, testObject1);
            StorageTestUtil.trace(test, false);
            RecordList testObject2 = storageSerializer.deserialize(RecordList.class, test);
            StorageTestUtil.assertEquals("Surveyor core RecordList test", testObject1, testObject2);
        } catch (Exception e) {
            GWT.log("testSerializer - error", e);
        }
    }

    private void testWithSurveyorCoreQueryResult() {
        try {
            StorageTestUtil.traceEmptyLine();
            QueryResult testObject1 = getTestQueryResult();
            String test = storageSerializer.serialize(QueryResult.class, testObject1);
            StorageTestUtil.trace(test, false);
            QueryResult testObject2 = storageSerializer.deserialize(QueryResult.class, test);
            StorageTestUtil.assertEquals("Surveyor core QueryResult test", testObject1, testObject2);
        } catch (Exception e) {
            GWT.log("testSerializer - error", e);
        }
    }

//    private void testStorage(boolean userKeyProvider, final StorageExt storage, final AsyncCallback<Boolean> callback) {
//        StorageTestUtil.prepare(storage);
//        scheduleRpcValueTests(userKeyProvider, new Scheduler.ScheduledCommand() {
//            @Override
//            public void execute() {
//                doTest(storage, callback);
//            }
//        });
//    }
//
//    private void scheduleRpcValueTests(final boolean userKP, final Scheduler.ScheduledCommand command){
//        TEST_SERVICE.getRpcTestValue(new AsyncCallback<RpcTestValue>() {
//            @Override
//            public void onFailure(Throwable caught) {
//                GWT.log("getRpcTestValue - onFailure", caught);
//            }
//
//            @Override
//            public void onSuccess(RpcTestValue result) {
//                StorageTestUtil.testPutValue(userKP ? KP.rpcTestValueKey() : KG.objectKey("getRpcTestValue"), result, new RpcTestValue());
//
//                TEST_SERVICE.getRpcTestValueList(new AsyncCallback<List<RpcTestValue>>() {
//                    @Override
//                    public void onFailure(Throwable caught) {
//                        GWT.log("getRpcTestValueList - onFailure", caught);
//                    }
//
//                    @Override
//                    public void onSuccess(List<RpcTestValue> result) {
//                        StorageTestUtil.testPutValue(userKP ? KP.rpcTestValueListKey() : KG.objectKey("getRpcTestValueList"), new ArrayList<>(result), new ArrayList<RpcTestValue>());
//
//                        TEST_SERVICE.getRpcTestValueStringMap(new AsyncCallback<Map<RpcTestMapKey, RpcTestMapValue>>() {
//                            @Override
//                            public void onFailure(Throwable caught) {
//                                GWT.log("getRpcTestValueStringMap - onFailure", caught);
//                            }
//
//                            @Override
//                            public void onSuccess(Map<RpcTestMapKey, RpcTestMapValue> result) {
//                                StorageTestUtil.testPutValue(userKP ? KP.rpcTestValueStringMapKey() : KG.objectKey("getRpcTestValueStringMap"), new HashMap<>(result), new HashMap<RpcTestMapKey, RpcTestMapValue>());
//
//                                command.execute();
//                            }
//                        });
//                    }
//                });
//            }
//        });
//    }
//    private void doTest(StorageExt storage, final AsyncCallback<Boolean> callback) {
//        StorageTestUtil.start();
////        final HandlerRegistration hr1 = OtherTest.listenerTest(storage, StorageChangeEvent.Level.STRING);
//        final Iterator<Scheduler.RepeatingCommand> iterator = new ArrayList<>(StorageTestUtil.getTests()).iterator();
//        Scheduler.get().scheduleIncremental(new Scheduler.RepeatingCommand() {
//            @Override
//            public boolean execute() {
//                Scheduler.RepeatingCommand next = iterator.next();
//                try {
//                    boolean isOK = next != null && next.execute();
//                    if (!isOK) {
//                        callback.onSuccess(false);
//                    }
//                    return isOK && iterator.hasNext();
//                } catch (Exception e) {
//                    callback.onFailure(e);
//                    return false;
//                } finally {
//                    if (next != null)
//                        iterator.remove();
//                    if (!iterator.hasNext()) {
//                        boolean isOK = StorageTestUtil.end();
////                        hr1.removeHandler();
//                        if (callback != null) callback.onSuccess(isOK);
//                    }
//                }
//            }
//        });
//    }

}
