package com.mingchencodelab.polymart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Demo31MainActivity extends AppCompatActivity {
    EditText demo31txt1;
    EditText demo31txt2;
    Button demo31btn;
    TextView demo31tv;

    EditText demo32txt;
    Button demo32btn;
    TextView demo32tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo31_main);
        mappingView();
        demo31btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GETAsyncTask().execute();
            }
        });

        demo32btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PostAsyncTask().execute();
            }
        });
    }

    private void mappingView() {
        demo31txt1 = findViewById(R.id.demo31txt1);
        demo31txt2 = findViewById(R.id.demo31txt2);
        demo31btn = findViewById(R.id.demo31btn1);
        demo31tv = findViewById(R.id.demo31tv);

        demo32txt = findViewById(R.id.demo32txt);
        demo32btn = findViewById(R.id.demo32btn);
        demo32tv = findViewById(R.id.demo32tv);
    }
    class PostAsyncTask extends AsyncTask<Void, Void, Void> {
        String result = "";
        String path = "https://batdongsanabc.000webhostapp.com/mob403/demo2_api_post.php";
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL(path);
                String params = "canh=" + URLEncoder.encode(demo31txt1.getText().toString(), "utf-8");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setFixedLengthStreamingMode(params.getBytes().length);
                httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
                printWriter.print(params);
                printWriter.close();

                String line = "";
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                        httpURLConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                result = stringBuilder.toString();
                httpURLConnection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            demo32tv.setText(result);
            super.onPostExecute(unused);
        }
    }
    class GETAsyncTask extends AsyncTask<Void, Void, Void> {
        String path = "https://batdongsanabc.000webhostapp.com/mob403lab3/bai3-get.php";
        String result;
        @Override
        protected Void doInBackground(Void... voids) {
            path += "?toan=" + demo31txt1.getText().toString() + "&van=" + demo31txt2.getText().toString();
            try {
                URL url = new URL(path);
                BufferedReader br = new BufferedReader(new InputStreamReader(url
                        .openConnection()
                        .getInputStream()));
                String line = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    stringBuilder.append(line);
                }
                result = stringBuilder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            demo31tv.setText(result);
        }
    }
}