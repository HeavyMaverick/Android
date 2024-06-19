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
import java.util.ArrayList;
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
    private ArrayList<String> urls;
    private ArrayList<String> names;
    private ArrayList<Button> buttons;
    private int numberRight;
    private int questionNumber;

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
        buttons = new ArrayList<>();
        buttons.add(button0);
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        urls = new ArrayList<>();
        names = new ArrayList<>();
        getContent();
        playGame();
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
    private void playGame(){
        generateQuestion();
        //DownloadImageTask task = new DownloadImageTask();
        try {
            Bitmap bitmap = task.execute(urls.get(questionNumber)).get();
            if (bitmap != null){
                imageViewStar.setImageBitmap(bitmap);
                for (int i = 0; i < buttons.size(); i++){
                    if (i == numberRight){
                        buttons.get(i).setText(names.get(questionNumber));
                    } else {
                        int a = genWrongAnsw();
                        buttons.get(i).setText(names.get(a));
                    }
                }
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private void generateQuestion(){
        questionNumber = (int) (Math.random() * names.size());
        numberRight = (int) (Math.random() * buttons.size());
    }
    private int genWrongAnsw(){
        return (int) (Math.random() * names.size());
    }
    private void getContent(){
//        DownloadContentTask task = new DownloadContentTask();
        //            String content = task.execute(url).get().;
        String content = "<section class=\"celeb-list\"><header><h2 class=\"section-heading section-heading--list\">The Celebrity 100</h2><h4 class=\"section-subheading\"> Click Cards for More Info</h4></header><div class=\"grid\"><div class=\"grid-item grid-item--1\"><div class=\"profile-card\"><div class=\"profile-img\" style=\"background-image: url(&quot;https://thumbor.forbes.com/thumbor/https%3A%2F%2Fspecials-images.forbesimg.com%2Fimageserve%2F5ed560600914c6000709c1bc%2F874x416.jpg%3Fbackground%3D000000%26cropX1%3D77%26cropX2%3D1920%26cropY1%3D0%26cropY2%3D879&quot;);\"></div><div class=\"profile-info\"><span class=\"profile-info__item profile-info__item--name\">Rush Limbaugh</span><span class=\"profile-info__item profile-info__item--earnings\">$85M</span></div><span class=\"profile-rank\">11</span></div></div><div class=\"grid-item grid-item--2\"><div class=\"profile-card\"><div class=\"profile-img\" style=\"background-image: url(&quot;https://thumbor.forbes.com/thumbor/https%3A%2F%2Fimages.forbes.com%2FCeleb100-Silos1%2FCeleb100-SILO-EllenDegeneres-Desktop-v2.png&quot;);\"></div><div class=\"profile-info\"><span class=\"profile-info__item profile-info__item--name\">Ellen DeGeneres</span><span class=\"profile-info__item profile-info__item--earnings\">$84M</span></div><span class=\"profile-rank\">12</span></div></div><div class=\"grid-item grid-item--3\"><div class=\"profile-card\"><div class=\"profile-img\" style=\"background-image: url(&quot;https://thumbor.forbes.com/thumbor/https%3A%2F%2Fspecials-images.forbesimg.com%2Fimageserve%2F5ed55793570be80006474219%2F874x416.jpg%3Fbackground%3D000000%26cropX1%3D0%26cropX2%3D1721%26cropY1%3D17%26cropY2%3D837&quot;);\"></div><div class=\"profile-info\"><span class=\"profile-info__item profile-info__item--name\">Bill Simmons</span><span class=\"profile-info__item profile-info__item--earnings\">$82.5M</span></div><span class=\"profile-rank\">13</span></div></div><div class=\"grid-item grid-item--4\"><div class=\"profile-card\"><div class=\"profile-img\" style=\"background-image: url(&quot;https://thumbor.forbes.com/thumbor/https%3A%2F%2Fimages.forbes.com%2FCeleb100-Silos2%2FCeleb100-SILO-EltonJohn-Desktop-v1.jpg&quot;);\"></div><div class=\"profile-info\"><span class=\"profile-info__item profile-info__item--name\">Elton John</span><span class=\"profile-info__item profile-info__item--earnings\">$81M</span></div><span class=\"profile-rank\">14</span></div></div><div class=\"grid-item grid-item--5\"><div class=\"profile-card\"><div class=\"profile-img\" style=\"background-image: url(&quot;https://thumbor.forbes.com/thumbor/https%3A%2F%2Fspecials-images.forbesimg.com%2Fimageserve%2F5ed561a119427200062f28cd%2F874x416.jpg%3Fbackground%3D000000%26cropX1%3D0%26cropX2%3D1920%26cropY1%3D192%26cropY2%3D1105&quot;);\"></div><div class=\"profile-info\"><span class=\"profile-info__item profile-info__item--name\">James Patterson</span><span class=\"profile-info__item profile-info__item--earnings\">$80M</span></div><span class=\"profile-rank\">15</span></div></div><div class=\"grid-item grid-item--6\"><div class=\"profile-card\"><div class=\"profile-img\" style=\"background-image: url(&quot;https://thumbor.forbes.com/thumbor/https%3Ahttps%3A%2F%2Fspecials-images.forbesimg.com%2Fimageserve%2F627bdaec36beab21cd23ad21%2F874x416.jpg%3Fbackground%3D000000%26cropX1%3D429%26cropX2%3D2409%26cropY1%3D94%26cropY2%3D1036&quot;);\"></div><div class=\"profile-info\"><span class=\"profile-info__item profile-info__item--name\">Stephen Curry</span><span class=\"profile-info__item profile-info__item--earnings\">$74.4M</span></div><span class=\"profile-rank\">16</span></div></div><div class=\"grid-item grid-item--7\"><div class=\"profile-card\"><div class=\"profile-img\" style=\"background-image: url(&quot;https://thumbor.forbes.com/thumbor/https%3A%2F%2Fimages.forbes.com%2FCeleb100-Silos2%2FCeleb100-SILO-ArianaGrande-Desktop-v1.jpg&quot;);\"></div><div class=\"profile-info\"><span class=\"profile-info__item profile-info__item--name\">Ariana Grande</span><span class=\"profile-info__item profile-info__item--earnings\">$72M</span></div><span class=\"profile-rank\">17</span></div></div><div class=\"grid-item grid-item--8\"><div class=\"profile-card\"><div class=\"profile-img\" style=\"background-image: url(&quot;https://thumbor.forbes.com/thumbor/https%3A%2F%2Fimages.forbes.com%2FCeleb100-Silos1%2FCeleb100-SILO-RyanReynolds-Desktop-v1.png&quot;);\"></div><div class=\"profile-info\"><span class=\"profile-info__item profile-info__item--name\">Ryan Reynolds</span><span class=\"profile-info__item profile-info__item--earnings\">$71.5M</span></div><span class=\"profile-rank\">18</span></div></div><div class=\"grid-item grid-item--9\"><div class=\"profile-card\"><div class=\"profile-img\" style=\"background-image: url(&quot;https://thumbor.forbes.com/thumbor/https%3A%2F%2Fspecials-images.forbesimg.com%2Fimageserve%2F5ed53f23da1f770006140c3c%2F874x416.jpg%3Fbackground%3D000000%26cropX1%3D861%26cropX2%3D4094%26cropY1%3D162%26cropY2%3D1702&quot;);\"></div><div class=\"profile-info\"><span class=\"profile-info__item profile-info__item--name\">Gordon Ramsay</span><span class=\"profile-info__item profile-info__item--earnings\">$70M</span></div><span class=\"profile-rank\">19</span></div></div><div class=\"grid-item grid-item--10\"><div class=\"profile-card\"><div class=\"profile-img\" style=\"background-image: url(&quot;https://thumbor.forbes.com/thumbor/https%3A%2F%2Fspecials-images.forbesimg.com%2Fimageserve%2F5ed558d217d638000724e3ff%2F874x416.jpg%3Fbackground%3D000000%26cropX1%3D0%26cropX2%3D1920%26cropY1%3D44%26cropY2%3D957&quot;);\"></div><div class=\"profile-info\"><span class=\"profile-info__item profile-info__item--name\">Jonas Brothers</span><span class=\"profile-info__item profile-info__item--earnings\">$68.5M</span></div><span class=\"profile-rank\">20</span></div></div></div><div class=\"pagination grid-pagination\"><div class=\"pagination-btn pagination-btn--previous\"><i class=\"pagination-btn__icon pagination-btn__icon--previous\"><svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 268.8 414.6\"><path fill=\"currentColor\" d=\"M137.4 0v313.2c20.4-35.4 55.2-66 129.6-85.2l1.8 4.8c-62.4 30-119.4 131.4-131.4 181.8h-6C119.4 364.2 63 262.8 0 232.8l1.8-4.8c74.4 19.2 109.2 49.8 129.6 85.2V0z\"></path></svg></i>PREVIOUS 10</div><div class=\"pagination-btn pagination-btn--next\"><i class=\"pagination-btn__icon pagination-btn__icon--next\"><svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 268.8 414.6\"><path fill=\"currentColor";
        String start = "<section class=\"celeb-list\">";
        String finish = "<path fill=\"currentColor\"";
        Pattern pattern = Pattern.compile(start + "(.*?)" + finish);
        Matcher matcher = pattern.matcher(content);
        String splitContent = "";
        while (matcher.find()){
            splitContent = matcher.group(1);
        }
        Pattern patternImg = Pattern.compile("https://(.*?)&quot;");
        Pattern patternName = Pattern.compile("profile-info__item profile-info__item--name\">(.*?)</span>");
        Matcher matcherImg = patternImg.matcher(splitContent);
        Matcher matcherName = patternName.matcher(splitContent);
        while (matcherImg.find()){
            String s = "https://" + matcherImg.group(1);
            urls.add(s);
        }
        while (matcherName.find()){
            names.add(matcherName.group(1));
        }
    }
//    private static class DownloadImageTask extends AsyncTask<String, Void, Bitmap>{
//        @Override
//        protected Bitmap doInBackground(String... strings) {
//            URL url = null;
//            HttpURLConnection httpURLConnection = null;
//            StringBuilder result = new StringBuilder();
//            try {
//                url = new URL(strings[0]);
//                InputStream inputStream = httpURLConnection.getInputStream();
//                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                return bitmap;
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
}
