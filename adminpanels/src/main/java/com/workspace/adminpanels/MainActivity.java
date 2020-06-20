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
    Toolbar toolbar;
    GridLayout gridDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.mToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Dashboard");
        gridDashboard = findViewById(R.id.gridL);
        setSingleEvent(gridDashboard);

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .addProfiles( new ProfileDrawerItem().withName("Bintang").withEmail("bintang@gmail.com").withIcon(R.drawable.person))
                .build();

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Home").withIcon(R.drawable.home);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Akun").withIcon(R.drawable.person);
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("Add Menu").withIcon(R.drawable.person);
        SecondaryDrawerItem item3 = new SecondaryDrawerItem().withIdentifier(3).withName("test");
        //create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1.withIdentifier(1),
                        item2.withIdentifier(2),
                        item4.withIdentifier(4),
                        new DividerDrawerItem(),
                        item3.withIdentifier(3)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Activity activity = null;
                        Class activityClass;
                        switch (position){
                            case 1:
                                activityClass = MainActivity.class;
                                break;
                            case 2:
                                activityClass = DataUserActivity.class;
                                Intent i = new Intent(MainActivity.this, DataUserActivity.class);
                                view.getContext().startActivity(i);
                            case 3:
                                activityClass = DataAddMenuActivity.class;
                                Intent add = new Intent(MainActivity.this, DataAddMenuActivity.class);
                                view.getContext().startActivity(add);
                                break;
                            default:
                                activityClass = MainActivity.class;
                        } try {
                            activity = (Activity) activityClass.newInstance();
                        } catch (Exception e){
                            e.printStackTrace();
                        }

                        return true;

                    }
                })
                .build();

        result.openDrawer();
        result.closeDrawer();
        result.setSelection(1);
    }
    private void setSingleEvent(GridLayout gridDashboard){
        for (int i = 0; i < gridDashboard.getChildCount();i++){
            CardView iCardview = (CardView) gridDashboard.getChildAt(i);
            final int finals = i;
            iCardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (finals == 0){
                        Intent dataUser = new Intent(MainActivity.this, DataUserActivity.class);
                        startActivity(dataUser);
                    }else if (finals == 1){
                        Intent dataMenu = new Intent(MainActivity.this, DataMenuActivity.class);
                        startActivity(dataMenu);
                    }else if (finals == 2){
                        Intent in = new Intent(MainActivity.this, DataUserActivity.class);
                        startActivity(in);
                    }else if (finals == 3){
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
