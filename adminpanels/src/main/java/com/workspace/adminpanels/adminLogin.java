package com.workspace.adminpanels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class adminLogin extends AppCompatActivity {

    private TextInputEditText tUsername,tPassword;
    private Button loginAdm, registerAdm;
    DatabaseReference loginRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        //pemanggilan void
        init();
        registerOnclick();
        loginOnclick();

    }

    private void init() {
        tUsername = findViewById(R.id.txtUsrname);
        tPassword = findViewById(R.id.txtPasswd);
        loginAdm = findViewById(R.id.btn_loginAdmin);
        registerAdm = findViewById(R.id.btn_registerAdmin);
    }

    private void registerOnclick() {
        registerAdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reg = new Intent(adminLogin.this, adminRegister.class);
                startActivity(reg);
            }
        });
    }

    private void loginOnclick() {
        loginAdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = tUsername.getText().toString();
                final String password = tPassword.getText().toString();

                if (TextUtils.isEmpty(username)){
                    Toast.makeText(adminLogin.this, "Input Username", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(password) || password.length() <6){
                    Toast.makeText(adminLogin.this, "Input Password", Toast.LENGTH_SHORT).show();
                }else {
                    loginRef = FirebaseDatabase.getInstance().getReference("Data").child("Admin").child(username);
                    loginRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                          if (dataSnapshot.exists()){
                            String passwordFromFirebase = dataSnapshot.child("password").getValue().toString();

                            if (password.equals(passwordFromFirebase)){
                                Intent login = new Intent(adminLogin.this, MainActivity.class);
                                startActivity(login);
                            }else {
                                Toast.makeText(adminLogin.this, "Password Salah", Toast.LENGTH_SHORT).show();
                            }

                          }else {
                              Toast.makeText(adminLogin.this, "Username Salah", Toast.LENGTH_SHORT).show();
                          }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }
}