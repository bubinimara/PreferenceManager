package com.github.bubinimara.preferencemanager;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.core.deps.guava.base.Strings;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

/**
 * Created by davide on 21/09/16.
 *
 * Test for single thread
 */
@RunWith(AndroidJUnit4.class)
public class PreferenceManagerTest {
    MockObject mockObject = new MockObject(12,"mock object");
    private Context context;
    PreferenceManager<MockObject> preferenceManager;
    private PreferenceManager<Integer> integerPreferenceManager;
    private PreferenceManager<String> stringPreferenceManager;

    @Before
    public void setUp() throws Exception {
        context = InstrumentationRegistry.getContext();
    }

    @After
    public void tearDown() throws Exception {
        preferenceManager = PreferenceManager.getPreferenceManager(context,MockObject.class);
        preferenceManager.clear();

        stringPreferenceManager = PreferenceManager.getPreferenceManager(context,String.class);
        stringPreferenceManager.clear();
        integerPreferenceManager = PreferenceManager.getPreferenceManager(context,Integer.class);
        integerPreferenceManager.clear();
    }


    @Test
    public void loadTest() throws Exception {
        preferenceManager = PreferenceManager.getPreferenceManager(context,MockObject.class);
        preferenceManager.store(mockObject);

        preferenceManager = PreferenceManager.getPreferenceManager(context,MockObject.class);
        assertEquals(mockObject,preferenceManager.load());
    }

    @Test
    public void storeTest() throws Exception {
        MockObject object = new MockObject(2,"mock 2");

        preferenceManager = PreferenceManager.getPreferenceManager(context,MockObject.class);
        preferenceManager.store(mockObject);
        assertEquals(mockObject,preferenceManager.load());

        preferenceManager = PreferenceManager.getPreferenceManager(context,MockObject.class);
        preferenceManager.store(object);
        assertEquals(object,preferenceManager.load());
    }

    @Test
    public void clearTest() throws Exception {
        preferenceManager = PreferenceManager.getPreferenceManager(context,MockObject.class);
        preferenceManager.store(mockObject);
        assertEquals(mockObject,preferenceManager.load());

        preferenceManager = PreferenceManager.getPreferenceManager(context,MockObject.class);
        preferenceManager.clear();
        assertNull(preferenceManager.load());

    }

    @Test
    public void instanceTest() throws Exception {
        preferenceManager = PreferenceManager.getPreferenceManager(context,MockObject.class);
        assertEquals(preferenceManager,PreferenceManager.getPreferenceManager(context,MockObject.class));
    }

    @Test
    public void typeTest() throws Exception {
        integerPreferenceManager = PreferenceManager.getPreferenceManager(context, Integer.class);
        stringPreferenceManager = PreferenceManager.getPreferenceManager(context, String.class);

        assertNotNull(stringPreferenceManager);
        assertNotNull(integerPreferenceManager);

        assertEquals(stringPreferenceManager,PreferenceManager.getPreferenceManager(context, String.class));
        assertEquals(integerPreferenceManager,PreferenceManager.getPreferenceManager(context, Integer.class));

        assertNull(PreferenceManager.getPreferenceManager(context, String.class).load());
        assertNull(PreferenceManager.getPreferenceManager(context, Integer.class).load());

        stringPreferenceManager.store("string");
        integerPreferenceManager.store(3);

        assertEquals("string",PreferenceManager.getPreferenceManager(context, String.class).load());
        assertEquals(3,PreferenceManager.getPreferenceManager(context, Integer.class).load().intValue());

        PreferenceManager.getPreferenceManager(context, Integer.class).clear();
        assertEquals("string",PreferenceManager.getPreferenceManager(context, String.class).load());
        assertNull(PreferenceManager.getPreferenceManager(context, Integer.class).load());

        stringPreferenceManager.store("string 2");
        assertEquals("string 2",PreferenceManager.getPreferenceManager(context, String.class).load());
        assertNull(PreferenceManager.getPreferenceManager(context, Integer.class).load());

    }
}
