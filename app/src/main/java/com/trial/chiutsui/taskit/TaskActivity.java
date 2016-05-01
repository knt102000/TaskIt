package com.trial.chiutsui.taskit;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class TaskActivity extends AppCompatActivity {
    private static final String TAG = "TaskActivity";

    protected static final String EXTRA = "Task_Extra";

    private Calendar mcal;
    private Task mTask;
    private Button mDateBtn;
    private EditText mTaskName;
    private CheckBox mCompleteChkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        mTask = (Task) getIntent().getSerializableExtra(EXTRA);

        if (mTask==null) {
            mTask = new Task();
        }

        mTaskName = (EditText) findViewById(R.id.task_name);
        mDateBtn = (Button) findViewById(R.id.task_date);
        mCompleteChkBox = (CheckBox) findViewById(R.id.task_done);
        Button submitBtn = (Button) findViewById(R.id.saveBtn);

        mcal = Calendar.getInstance();

        mTaskName.setText(mTask.getName());

        if (mTask.getDueDate() == null) {
            mcal.setTime(new Date());
            mDateBtn.setText(R.string.newDate);
        } else {
            updateCalendarBtn();
            mcal.setTime(mTask.getDueDate());
        }

        mCompleteChkBox.setChecked(mTask.isComplete());

        mDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dpd = new DatePickerDialog(TaskActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        mcal.set(Calendar.YEAR, year);
                        mcal.set(Calendar.MONTH, monthOfYear);
                        mcal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        updateCalendarBtn();
                    }
                }, mcal.get(Calendar.YEAR), mcal.get(Calendar.MONTH), mcal.get(Calendar.DAY_OF_MONTH));

                dpd.show();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTask.setName(mTaskName.getText().toString());
                mTask.setComplete(mCompleteChkBox.isChecked());
                mTask.setDueDate(mcal.getTime());

                Intent intent = new Intent();
                intent.putExtra(EXTRA, mTask);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void updateCalendarBtn() {
        DateFormat df = DateFormat.getDateInstance();
        mDateBtn.setText(df.format(mcal.getTime()));
    }
}
