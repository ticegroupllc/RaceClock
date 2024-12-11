package com.example.myapplication.TypeClasses;

import java.util.Date;

// Model class
public final class Item {
    private Date timestamp;

    public Item(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getName() {
        return 0;
    }
}
