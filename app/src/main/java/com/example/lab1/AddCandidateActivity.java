package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddCandidateActivity extends AppCompatActivity {

    Button addTo;
    Button addImage;
    Button addImage2;

    ImageView imageView;
    ImageView imageView2;
    EditText nameET;
    EditText basicEduET;
    EditText pastET;
    EditText workET;
    EditText earningET;


    private Bitmap bitmap;
    final int Image_Capture_Code = 1;
    File file;
    String imageFilePath;

    private static final String DEFAULT_PICTURE_NAME = "Example.jpg";
    private static final int REQUEST_TAKE_PHOTO = 1;
    private Uri photoUri;
    private static String imgName="";

    DatabaseManager databaseManager;


    //helper functions
    private void dispatchTakePictureIntent(int photoNum) {

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        Intent takePictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(
                getPackageManager()) != null) {
            File photoFile = createImageFile();
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P)
                photoUri = Uri.fromFile(photoFile);
            else
                photoUri = FileProvider.getUriForFile(
                        getApplicationContext(),
                        BuildConfig.APPLICATION_ID, photoFile);
            imgName=photoUri.toString();

            takePictureIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION |
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(takePictureIntent, photoNum);
        }
    }

    private File createImageFile()  {
        String state = Environment.getExternalStorageState();
        File filesDir;
        // Make sure it's available
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            filesDir = new File(
                    Environment.getExternalStorageDirectory().toString() ,
                    "CameraExample");
        } else {
            //            // Load another directory, probably local memory
            //        filesDir = new File(getFilesDir(),"Images");
            filesDir = new File(getExternalFilesDir(null),
                    "CameraExample");
        }

        if(!filesDir.exists()) filesDir.mkdirs();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
        String myimage= "IMG_" + dateFormat.format(new Date()) + ".jpg" ;

        return new File(filesDir,myimage);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_candidate);
        final int Image_Capture_Code = 1;

        setTitle("Add Record");

        nameET = findViewById(R.id.cnameET);
        basicEduET = findViewById(R.id.pqualET);
        pastET = findViewById(R.id.cpastET);
        workET = findViewById(R.id.cworkET);
        earningET = findViewById(R.id.cpropertyET);
        imageView = findViewById(R.id.imgView);
        imageView2 = findViewById(R.id.imgView2);


        addTo = findViewById(R.id.button);
        addImage = findViewById(R.id.imgadd);
        addImage2 = findViewById(R.id.imgadd2);
        databaseManager = new DatabaseManager(this);
        databaseManager.open();

        String str= Environment.getExternalStorageDirectory() + "/CameraExample";
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();

        addTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = nameET.getText().toString();
                final String basicEdu = basicEduET.getText().toString();
                final String past = pastET.getText().toString();
                final String work = workET.getText().toString();
                final String earning = earningET.getText().toString();


                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageInByte = baos.toByteArray();
                Log.e("ERRRR",imageInByte.toString());

                Bitmap bitmap2 = ((BitmapDrawable) imageView2.getDrawable()).getBitmap();
                ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
                bitmap2.compress(Bitmap.CompressFormat.JPEG, 100, baos2);
                byte[] imageInByte2 = baos2.toByteArray();
                Log.e("ERRRR",imageInByte2.toString());

                databaseManager.insert(name, basicEdu, past, work, earning, imageInByte, imageInByte2);

                Intent main = new Intent(AddCandidateActivity.this, CRUDActivity.class);

                startActivity(main);
            }
        });

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent(1);
            }
        });

        addImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent(2);
            }
        });
    }


    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO) {
            if (resultCode == RESULT_OK) {
                imageView.setImageURI(Uri.parse(imgName));
                galleryAddPic();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            }
        } else {
            if (resultCode == RESULT_OK) {
                imageView2.setImageURI(Uri.parse(imgName));
                galleryAddPic();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(
                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(imgName);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }
}
