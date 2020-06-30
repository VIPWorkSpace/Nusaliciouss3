package com.workspace.adminpanels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.workspace.adminpanels.Activity.DataAddMenuActivity;
import com.workspace.adminpanels.Activity.DataMenuActivity;
import com.workspace.adminpanels.Activity.DataTransaksi;
import com.workspace.adminpanels.Activity.DataUserActivity;

public class MainActivity extends AppCompatActivity {

    private GridLayout gridDashboard;
    private TextView mUsername;
    private DatabaseReference dRef;
    String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsername = findViewById(R.id.textID);
        gridDashboard = findViewById(R.id.gridL);
        setSingleEvent(gridDashboard);


    }

    private void retrieveAdmin() {
    }

    private void setSingleEvent(GridLayout gridDashboard) {
        for (int i = 0; i < gridDashboard.getChildCount(); i++) {
            CardView iCardview = (CardView) gridDashboard.getChildAt(i);
            final int finals = i;
            iCardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (finals == 0) {
                        Intent dataMenu = new Intent(MainActivity.this, DataMenuActivity.class);
                        startActivity(dataMenu);
                    } else if (finals == 1) {
                        Intent addMenu = new Intent(MainActivity.this, DataAddMenuActivity.class);
                        startActivity(addMenu);
                    } else if (finals == 2) {
                        Intent dataUser = new Intent(MainActivity.this, DataUserActivity.class);
                        startActivity(dataUser);
                    } else if (finals == 3) {
                        Intent transaction = new Intent(MainActivity.this, DataTransaksi.class);
                        startActivity(transaction);
                    }
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
