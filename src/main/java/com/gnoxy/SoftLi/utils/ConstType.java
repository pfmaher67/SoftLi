package com.gnoxy.SoftLi.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Enumeration;

public class ConstType implements java.io.Serializable, Cloneable {

    private int value;
    private transient String desc;

    // a hashtable of hashtables...
    private static final HashMap<String, HashMap> types = new HashMap<>();

    protected ConstType(int value, String desc) {
        this.value = value;
        this.desc = desc;
        checkForDupes(this);
        storeType(this);
    }

    private void checkForDupes(ConstType type) {
        String className = type.getClass().getName();

        HashMap<Integer, ConstType> values;

        values = types.get(className);

        if (values != null) {
            if (values.get(type.getValue()) != null) {
                System.out.println("No Dupes Allowed: " + className + "=" + type);
                throw (new RuntimeException());
            }
        }
    }

    private void storeType(ConstType type) {
        String className = type.getClass().getName();

        HashMap<Integer, ConstType> values;

        synchronized (types) // avoid race condition for creating inner table
        {
            values = types.get(className);

            if (values == null) {
                values = new HashMap();
                types.put(className, values);
            }
        }

        values.put(type.getValue(), type);
    }

    public static ConstType getByValue(Class classRef, int value) {
        ConstType type = null;

        String className = classRef.getName();

        HashMap<Integer, ConstType> values = types.get(className);

        if (values != null) {
            type = (ConstType) values.get(value);
        }

        return (type);
    }

    public static Enumeration elements(Class classRef) {
        String className = classRef.getName();

        HashMap<Integer, ConstType> values = types.get(className);

        if (values != null) {
            return Collections.enumeration(values.keySet());
        } else {
            return null;
        }
    }

    public static int getMaxValue(Class classRef) {
        int max = -1;

        Enumeration e = elements(classRef);

        while (e.hasMoreElements()) {
            ConstType type = (ConstType) e.nextElement();

            int tmp = type.getValue();

            if (tmp > max) {
                max = tmp;
            }
        }

        return (max);
    }

    public int getValue() {
        return value;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ConstType)) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if ((this.getClass() == obj.getClass())
                && (this.getValue() == ((ConstType) obj).getValue())) {
            return true;
        }

        return false;
    }
}
