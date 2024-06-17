package com.heavymaverick.stringadvanced;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private ImageView imageViewStar;
    private String url = "https://www.forbes.com/celebrities/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imageViewStar = findViewById(R.id.imageViewStar);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        Pattern patternImg = Pattern.compile("https://(.*?)&quot;");
        Pattern patternName = Pattern.compile("profile-info__item profile-info__item--name\">(.*?)</span>");
        Matcher matcherImg = patternImg.matcher(url);
        Matcher matcherName = patternName.matcher(url);
        while (matcherImg.find()) {
            Log.i("MyName", matcherImg.group(1));
        }
        while (matcherName.find()) {
            Log.i("MyName", matcherName.group(1));
        }
        getContent();
    }
    private static class DownloadContentTask extends AsyncTask<String,Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            URL url = null;
            HttpURLConnection httpURLConnection = null;
            StringBuilder result = new StringBuilder();
            try {
                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = bufferedReader.readLine();
                while (line != null){
                    result.append(line);
                    line = bufferedReader.readLine();
                }
                return result.toString();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (httpURLConnection != null){
                    httpURLConnection.disconnect();
                }
            }
        }
    }
    private void getContent(){
        DownloadContentTask task = new DownloadContentTask();
        try {
            String content = task.execute(url).get();
            Log.i("MyResult", content);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    private static class DownloadImageTask extends AsyncTask<String, Void, Bitmap>{
        @Override
        protected Bitmap doInBackground(String... strings) {
            URL url = null;
            HttpURLConnection httpURLConnection = null;
            StringBuilder result = new StringBuilder();
            try {
                url = new URL(strings[0]);
                InputStream inputStream = httpURLConnection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
