package com.workspace.adminpanels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.workspace.adminpanels.Activity.DataAddMenuActivity;
import com.workspace.adminpanels.Activity.DataMenuActivity;
import com.workspace.adminpanels.Activity.DataUserActivity;

public class MainActivity extends AppCompatActivity {

    GridLayout gridDashboard;
    TextView adminName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adminName = findViewById(R.id.textID);
        gridDashboard = findViewById(R.id.gridL);
        setSingleEvent(gridDashboard);
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
                        Intent in = new Intent(MainActivity.this, DataUserActivity.class);
                        startActivity(in);
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
