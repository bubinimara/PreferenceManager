package com.github.bubinimara.preferencemanager;

import java.util.ArrayList;

/**
 * Created by davide on 27/09/16.
 */

public class MockObjectList {
    private ArrayList<MockObject> items = new ArrayList<>();

    public MockObjectList() {
    }

    public MockObjectList(ArrayList<MockObject> list) {
        setItems(list);
    }

    /**
     *  Be careful - Reference, Unmodifiable
     * @return the list of objects
     */
    public ArrayList<MockObject> getItems() {
        return items;
    }

    public void setItems(ArrayList<MockObject> items) {
        this.items.clear();
        if(items == null)
            return;

        for (MockObject mock :
                items) {
            this.items.add(mock);
        }
    }
}
