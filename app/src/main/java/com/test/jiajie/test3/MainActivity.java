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
            try {
                photoFile = createImgFile();
            } catch (IOException ex) {
                Toast.makeText(this, "File not created", Toast.LENGTH_SHORT).show();
            }
            if (photoFile!= null) {
                Uri photoURI = FileProvider.getUriForFile(this,"com.test.jiajie.test3.fileprovider", photoFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
                mediaScan();
            }
        }
    }

    public File getAlbumStorageDir(String albumName) {
        //Get the directory for the user's public pictures directory
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs())
        {
            Toast.makeText(this, "Directory not created", Toast.LENGTH_SHORT).show();
        }
        return file;
    }

    String mPhotoPath;
    private File createImgFile() throws IOException {
        //Create Image file
        String timeStamp = new SimpleDateFormat("yyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp +"_";
        File image = File.createTempFile(
                imageFileName,                  /*prefix*/
                ".jpg",                         /*suffix*/
                getAlbumStorageDir("TestAlbum") /*directory*/
        );
        //Save a file: path for use with ACTION_VIEW intents
        mPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void mediaScan() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    public void toDoList(View view) {
        Intent toDoIntent = new Intent(this, ToDoActivity.class);
        startActivity(toDoIntent);
    }
}
