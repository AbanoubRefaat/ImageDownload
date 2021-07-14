package com.example.android.downloadimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ImageView downloadedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadedImage = (ImageView)findViewById(R.id.imageView3);


    }

    public void download (View view){

       // https://cdn.imgbin.com/13/8/10/imgbin-stewie-griffin-brian-griffin-peter-griffin-lois-griffin-chris-griffin-peter-griffin-family-guy-hVkgdfDjtxbC2ciLQ3bg7ZnAq.jpg

        Downloader task = new Downloader();
        Bitmap image;
        try {
            image = task.execute("https://cdn.imgbin.com/13/8/10/imgbin-stewie-griffin-brian-griffin-peter-griffin-lois-griffin-chris-griffin-peter-griffin-family-guy-hVkgdfDjtxbC2ciLQ3bg7ZnAq.jpg").get();
                downloadedImage.setImageBitmap(image);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public class Downloader extends AsyncTask<String,Void,Bitmap>{


        @Override
        protected Bitmap doInBackground(String... urls) {

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream in = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(in);

                return bitmap;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}
