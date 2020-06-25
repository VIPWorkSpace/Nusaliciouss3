package com.workspace.adminpanels.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.workspace.adminpanels.MainActivity;
import com.workspace.adminpanels.Model.addmenuModel;
import com.workspace.adminpanels.R;

import java.util.ArrayList;
import java.util.List;

public class DataAddMenuActivity extends AppCompatActivity {

    private Toolbar toolbarAdd;
    private static final int ImagePick = 1;
    private DatabaseReference mAddMenu;
    private StorageReference mStorage;
    private Spinner spinCategory, spinCatering;
    private TextView selectCategory,selectCatering;
    private Button btnUpload, btnSave;
    private TextInputEditText textNama, textDesc, textHarga, textKategori;
    private ProgressBar mProgress;
    private ImageView imagePreview;
    private Uri  photoLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_add_menu);

        toolbarAdd = findViewById(R.id.addToolbar);
        setSupportActionBar(toolbarAdd);
        toolbarAdd.setTitleTextColor(getResources().getColor(R.color.md_white_1000));
        getSupportActionBar().setTitle("Add New Menu");
        btnUpload = findViewById(R.id.btn_upload);
        btnSave = findViewById(R.id.btn_save);
        textNama = findViewById(R.id.textNameMenu);
        textDesc = findViewById(R.id.txtDescMenu);
        textHarga = findViewById(R.id.txtHargaMenu);
        textKategori = findViewById(R.id.txtKategori);
        imagePreview = findViewById(R.id.imgPreview);
        spinCategory = findViewById(R.id.spin_category);
        spinCatering = findViewById(R.id.spin_catering);
        selectCategory = findViewById(R.id.select_category);
        selectCatering = findViewById(R.id.select_catering);
        mProgress = findViewById(R.id.pb_menu);
        mStorage = FirebaseStorage.getInstance().getReference("Image Menu");
        mAddMenu = FirebaseDatabase.getInstance().getReference("DataMenu");

        uploadClick();
        saveClick();
        listCategory();
        listCatering();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImagePick && resultCode == RESULT_OK && data != null && data.getData() != null) {
            photoLocation = data.getData();
            Picasso.get().load(photoLocation).fit().centerCrop().into(imagePreview);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void chooseImage() {
        Intent choose = new Intent();
        choose.setType("image/*");
        choose.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(choose, ImagePick);
    }
    private void saveMenu() {

        if (photoLocation != null) {
            final StorageReference fileReferense = mStorage.child(System.currentTimeMillis() + "." + getFileExtension(photoLocation));
            fileReferense.putFile(photoLocation)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                            fileReferense.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String mImage = uri.toString();
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            mProgress.setProgress(0);
                                        }
                                    }, 10000);
                                    String textPaket = textNama.getText().toString().trim();
                                    String textDescs = textDesc.getText().toString().trim();
                                    Integer textharga = Integer.valueOf((textHarga.getText().toString().trim()));
                                    String textkateg = textKategori.getText().toString().trim();
                                    String textKatering = selectCatering.getText().toString();

                                    Toast.makeText(DataAddMenuActivity.this, "Save Successful", Toast.LENGTH_LONG).show();
                                    addmenuModel add = new addmenuModel(textPaket, textDescs, textharga,textkateg,textKatering, mImage);
                                    String imageId = mAddMenu.push().getKey();
                                    mAddMenu.child(imageId).setValue(add);
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(DataAddMenuActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progres = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgress.setProgress((int) progres);
                        }
                    });
        } else {
            Toast.makeText(this, "Tidak Ada file yang dipilih", Toast.LENGTH_SHORT).show();
        }

    }
    private void uploadClick(){
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });
    }
    private void saveClick(){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveMenu();
                Intent send = new Intent(DataAddMenuActivity.this, MainActivity.class);
                startActivity(send);
            }
        });
    }
    private void listCategory(){
        List<String> mListCategory = new ArrayList<>();
        mListCategory.add(0, "Choose Category");
        mListCategory.add("Nasi Kotak");
        mListCategory.add("Prasmanan");

        ArrayAdapter<String> categoryAdapter;
        categoryAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, mListCategory);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCategory.setAdapter(categoryAdapter);

        spinCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Choose Category")){

                } else {
                    selectCategory.setText(adapterView.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void listCatering(){
        List<String> mListCatering = new ArrayList<>();
        mListCatering.add(0,"Choose Catering");
        mListCatering.add("D'Pawon Catering");
        mListCatering.add("Roselia");

        ArrayAdapter<String> cateringAdapter;
        cateringAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, mListCatering);
        cateringAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCatering.setAdapter(cateringAdapter);

        spinCatering.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Choose Catering")){

                }else {
                    selectCatering.setText(adapterView.getSelectedItem().toString());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}