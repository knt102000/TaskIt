package com.trial.chiutsui.taskit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TaskListActivity extends AppCompatActivity {

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        Task[] items = new Task[3];
        items[0] = new Task();
        items[1] = new Task();
        items[2] = new Task();

        list = (ListView) findViewById(R.id.taskList);

        list.setAdapter(new ArrayAdapter<Task>(this, android.R.layout.simple_list_item_1, items));
    }
}
