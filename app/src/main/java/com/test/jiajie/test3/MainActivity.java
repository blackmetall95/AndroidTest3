package com.test.jiajie.test3;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    public void startCamera(View view){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null)
        {
            File photoFile = null;
            try{
                photoFile = createImgFile();
            } catch (IOException ex) {
                Toast.makeText(this, "Could not create ImageFile", Toast.LENGTH_SHORT).show();
            }
            //Continue only if ImageFile is created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this, "com.test.jiajie.test3.fileprovider", photoFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    String mPhotoPath;
    private File createImgFile() throws IOException {
        //Create image filename
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_"+timeStamp +"_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName, /*prefix*/
                ".jpg",        /*suffix*/
                storageDir     /*directory*/
        );
        //Save a file: path for use with ACTION_VIEW intents
        mPhotoPath = image.getAbsolutePath();
        return image;
    }
}
