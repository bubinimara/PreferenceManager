package com.github.bubinimara.preferencemanager;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.intent.IntentMonitorRegistry;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.R.id.list;
import static org.hamcrest.Matchers.is;

/**
 * Created by davide on 27/09/16.
 */
@RunWith(AndroidJUnit4.class)
public class PreferenceLoaderTest {
    MockObject mockObject = new MockObject(2,"mock object");

    PreferenceLoader<MockObject> preferenceLoader = new PreferenceLoader<>(InstrumentationRegistry.getContext(),MockObject.class);
    PreferenceLoader<MockObjectList> objectListPreferenceLoader = new PreferenceLoader<>(InstrumentationRegistry.getContext(),MockObjectList.class);

    @Before
    public void setUp() throws Exception {
        //preferenceLoader = new PreferenceLoader<>(InstrumentationRegistry.getContext(),MockObject.class);

    }

    @After
    public void tearDown() throws Exception {
        preferenceLoader.clear();
        objectListPreferenceLoader.clear();

    }

    @Test
    public void storeTest() throws Exception {
        MockObject mockObject1 = new MockObject(3,"mock object 3");
        Assert.assertNull(preferenceLoader.load());

        preferenceLoader.store(mockObject1);
        Assert.assertEquals(mockObject1,preferenceLoader.load());

        preferenceLoader.store(mockObject);
        Assert.assertEquals(mockObject,preferenceLoader.load());

    }

    @Test
    public void loadTest() throws Exception {
        Assert.assertNull(preferenceLoader.load());

        preferenceLoader.store(mockObject);
        Assert.assertEquals(mockObject,preferenceLoader.load());
    }

    @Test
    public void listTest() throws Exception {
        int sizeList = 10;
        ArrayList<MockObject> list = new ArrayList<>(sizeList);
        for (int i = 0; i < sizeList; i++) {
            list.add(new MockObject(i,"mock "+i));
        }



        Assert.assertNull(objectListPreferenceLoader.load());

        objectListPreferenceLoader.store(new MockObjectList(list));

        MockObjectList mockObjectList = objectListPreferenceLoader.load();
        Assert.assertNotNull(mockObjectList);
        Assert.assertNotNull(mockObjectList.getItems());
        Assert.assertThat(sizeList,is(mockObjectList.getItems().size()));
        for (int i = 0; i < sizeList; i++) {
            Assert.assertEquals(list.get(i),mockObjectList.getItems().get(i));
        }

    }

    @Test
    public void clearTest() throws Exception {

        Assert.assertNull(preferenceLoader.load());

        preferenceLoader.store(mockObject);
        Assert.assertEquals(mockObject,preferenceLoader.load());

        preferenceLoader.clear();
        Assert.assertNull(preferenceLoader.load());

    }
}
