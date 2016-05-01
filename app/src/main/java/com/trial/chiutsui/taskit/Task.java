package com.trial.chiutsui.taskit;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by chiutsui on 4/24/16.
 */
public class Task implements Serializable {
    private static final String TAG = "Task";

    private String mName;
    private Date mDueDate;
    private boolean mComplete;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Date getDueDate() {
        return mDueDate;
    }

    public void setDueDate(Date dueDate) {
        mDueDate = dueDate;
    }

    public boolean isComplete() {
        return mComplete;
    }

    public void setComplete(boolean complete) {
        mComplete = complete;
    }

    public String toString() {
        return mName;
    }

}
