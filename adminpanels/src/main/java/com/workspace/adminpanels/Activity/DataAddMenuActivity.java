package com.workspace.adminpanels.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.workspace.adminpanels.R;

public class DataAddMenuActivity extends AppCompatActivity {

    Toolbar toolbarAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_add_menu);

        toolbarAdd = findViewById(R.id.addToolbar);
        setSupportActionBar(toolbarAdd);
        toolbarAdd.setTitleTextColor(getResources().getColor(R.color.md_white_1000));
        getSupportActionBar().setTitle("Add New Menu");
    }
}