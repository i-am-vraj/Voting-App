package com.example.lab1;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.lab1.channelClass.CHANNEL_1_ID;
import static com.example.lab1.channelClass.CHANNEL_2_ID;

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


    private NotificationManagerCompat notificationManager;

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

        //disappear
//        imageView.setVisibility(View.GONE);
//        imageView2.setVisibility(View.GONE);
//        addImage.setVisibility(View.GONE);
//        addImage2.setVisibility(View.GONE);

        notificationManager = NotificationManagerCompat.from(this);

        String str= Environment.getExternalStorageDirectory() + "/CameraExample";
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();

//        addTo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final String name = nameET.getText().toString();
//                final String basicEdu = basicEduET.getText().toString();
//                final String past = pastET.getText().toString();
//                final String work = workET.getText().toString();
//                final String earning = earningET.getText().toString();
//
////
////                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
////                ByteArrayOutputStream baos = new ByteArrayOutputStream();
////                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
////                byte[] imageInByte = baos.toByteArray();
////                Log.e("ERRRR",imageInByte.toString());
////
////                Bitmap bitmap2 = ((BitmapDrawable) imageView2.getDrawable()).getBitmap();
////                ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
////                bitmap2.compress(Bitmap.CompressFormat.JPEG, 100, baos2);
////                byte[] imageInByte2 = baos2.toByteArray();
////                Log.e("ERRRR",imageInByte2.toString());
//
//                //databaseManager.insert(name, basicEdu, past, work, earning, imageInByte, imageInByte2);
//
//                Intent main = new Intent(AddCandidateActivity.this, CRUDActivity.class);
//
//                Intent activityIntent = new Intent(AddCandidateActivity.this, CRUDActivity.class);
//                PendingIntent contentIntent = PendingIntent.getActivity(AddCandidateActivity.this,
//                        0, activityIntent, 0);
//
//                Intent broadcastIntent = new Intent(AddCandidateActivity.this, NotificationReceiver.class);
//                broadcastIntent.putExtra("toastMessage", name);
//
//                PendingIntent actionIntent = PendingIntent.getBroadcast(AddCandidateActivity.this,
//                        0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//                Notification notification = new NotificationCompat.Builder(AddCandidateActivity.this, CHANNEL_1_ID)
//                        .setSmallIcon(R.drawable.modiji)
//                        .setContentTitle(name)
//                        .setContentText(basicEdu)
//                        .setPriority(NotificationCompat.PRIORITY_HIGH)
//                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
//                        .setColor(Color.BLUE)
//                        .setContentIntent(contentIntent)
//                        .setAutoCancel(true)
//                        .setOnlyAlertOnce(true)
//                        .addAction(R.mipmap.ic_launcher, "Action 1", actionIntent)
//                        .build();
//
//                notificationManager.notify(1, notification);
//
//                Toast.makeText(AddCandidateActivity.this, "NO NOTIFICATION", Toast.LENGTH_SHORT).show();
//
//                //startActivity(main);
//            }
//        });

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


    public void sendOnChannel1(View v) {
        final String name = nameET.getText().toString();
        final String basicEdu = basicEduET.getText().toString();
        final String past = pastET.getText().toString();
        final String work = workET.getText().toString();
        final String earning = earningET.getText().toString();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is Channel 1");

            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "Channel 2",
                    NotificationManager.IMPORTANCE_LOW
            );
            channel2.setDescription("This is Channel 2");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }

        Intent activityIntent = new Intent(this,
                ModifyCandidateActivity.class);

        activityIntent.putExtra("cid",8);
        activityIntent.putExtra("pqual", basicEdu);
        activityIntent.putExtra("cname", name);
        activityIntent.putExtra("cwork", work);
        activityIntent.putExtra("cpast", past);
        activityIntent.putExtra("cproperty", earning);

        
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, activityIntent, 0);

        Intent broadcastIntent = new Intent(this,
                NotificationReceiver.class);
        broadcastIntent.putExtra("toastMessage", name);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this,
                0, broadcastIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Bitmap largeIcon = BitmapFactory.decodeResource(
                getResources(), R.drawable.modiji);

        Notification notification = new NotificationCompat.Builder(
                this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.modiji)
                .setContentTitle(name)
                .setContentText(basicEdu)
                .setLargeIcon(largeIcon)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("hello")
                        .setBigContentTitle(name)
                        .setSummaryText(basicEdu))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.BLUE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher, "Toast",
                        actionIntent)
                .build();



        databaseManager.insert(name, basicEdu, past, work, earning);
        notificationManager.notify(1, notification);
    }
}
