package com.mishobu.camarauno;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private final int PICTURE_ACTIVITY_CODE = 1;
    File mFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        launchTakePhoto();
    }

    private void launchTakePhoto()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String FILENAME = "sdcard/photo.jpg";
        mFile = new File(FILENAME);
        Uri outputFileUri = Uri.fromFile(mFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(intent, PICTURE_ACTIVITY_CODE);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Toast.makeText(MainActivity.this, requestCode + "", Toast.LENGTH_LONG).show();
        if (requestCode == PICTURE_ACTIVITY_CODE)
        {
            if (resultCode == RESULT_OK)
            {
                ImageView imageView = (ImageView) findViewById(R.id.imageView1);
                Uri inputFileUri = Uri.fromFile(mFile);
                imageView.setImageURI(inputFileUri);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
