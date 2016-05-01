package com.trial.chiutsui.taskit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskListActivity extends AppCompatActivity {

    private static final String TAG = "TaskListActivity";
    private static final int EDIT_TASK_REQUEST=10;
    private static final int CREATE_TASK_REQUEST=9;

    ListView list;

    private ArrayList<Task> mItems;
    private int mItemLastClicked;
    private TaskAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        mItems = new ArrayList<Task>();
        mItems.add(new Task());
        mItems.get(0).setName("Task 1");

        list = (ListView) findViewById(R.id.taskList);

        mAdapter = new TaskAdapter(mItems);

        list.setAdapter(mAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mItemLastClicked = i;

                Intent intent = new Intent(TaskListActivity.this, TaskActivity.class);

                Task task = (Task) adapterView.getAdapter().getItem(i);

                intent.putExtra(TaskActivity.EXTRA, task);

                startActivityForResult(intent, EDIT_TASK_REQUEST);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_task_list,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.addTask) {
            Intent intent = new Intent(TaskListActivity.this, TaskActivity.class);

            startActivityForResult(intent, CREATE_TASK_REQUEST);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        Task task;

        if (resultCode==RESULT_OK) {
            switch (requestCode) {
                case EDIT_TASK_REQUEST:
                    task = (Task) data.getSerializableExtra(TaskActivity.EXTRA);
                    mItems.set(mItemLastClicked,task);
                    mAdapter.notifyDataSetChanged();
                    break;
                case CREATE_TASK_REQUEST:
                    task = (Task) data.getSerializableExtra(TaskActivity.EXTRA);
                    mItems.add(task);
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }

    }

    private class TaskAdapter extends ArrayAdapter<Task> {
        TaskAdapter(ArrayList<Task> tasks) {
            super(TaskListActivity.this, R.layout.task_list_row, R.id.task_item_name, tasks);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = super.getView(position, convertView, parent);

            Task task = getItem(position);

            TextView taskName = (TextView) convertView.findViewById(R.id.task_item_name);
            CheckBox taskCheck = (CheckBox) convertView.findViewById(R.id.task_item_checkBox);

            taskName.setText(task.getName());
            taskCheck.setChecked(task.isComplete());

            return convertView;
        }
    }
}
