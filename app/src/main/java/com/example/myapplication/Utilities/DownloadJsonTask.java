package com.example.myapplication.Utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Environment;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DownloadJsonTask  extends AsyncTask<Void, Void, String> implements Callable<String>{
    private String urlString;
    private static final String JSON_URL = "https://example.com/data.json";
    private Handler handler = new Handler(Looper.getMainLooper());

    public DownloadJsonTask(String urlString) {
        this.urlString = urlString;
    }

    @Override
    public String call() throws Exception {
        String jsonString = null;
        URL url = new URL(urlString);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            bufferedReader.close();
            jsonString = stringBuilder.toString();
        } finally {
            urlConnection.disconnect();
        }
        return jsonString;
    }



    @Override
    protected String doInBackground(Void... voids) {
        String jsonString = downloadJson();
        if (jsonString != null) {
            storeJsonToFile(jsonString);
        }
        return jsonString;
    }

    @Override
    protected void onPostExecute(String jsonString) {
        // Schedule the next download
        handler.postDelayed(() -> execute(), 5000);
    }

    private String downloadJson() {
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(JSON_URL);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            Scanner scanner = new Scanner(urlConnection.getInputStream());
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }

    private void storeJsonToFile(String jsonString) {
        File file = new File(Environment.getExternalStorageDirectory(), "data.json");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
