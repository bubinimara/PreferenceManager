package com.github.bubinimara.preferencemanager;

/**
 * Created by davide on 27/09/16.
 */
class MockObject {
    int id;
    String name;

    public MockObject(int id, String name) {
        this.id = id;
        this.name = name;
    }


    @Override
    public boolean equals(Object obj) {
        try {
            MockObject o = (MockObject) obj;
            return o.id == id && o.name.equals(name);
        } catch (Exception e) {
            return false;
        }


    }
}
