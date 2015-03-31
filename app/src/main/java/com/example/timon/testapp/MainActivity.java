package com.example.timon.testapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class MainActivity extends Activity {

    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message = (TextView) findViewById(R.id.action_settings);

        message.setText("111");
        //new getinternetData .execute("");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();
        new getinternetData().execute("");
        //message.setText("OnStart");
    }

    private class getinternetData extends AsyncTask<String, Void, String> {

        private static final String TAG = "MainActivity";

        @Override
        protected String doInBackground(String... params) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet("http://www.google.com");
            try {
                //message.setText("333");
                Log.d(TAG, "Before httpResponse");
                HttpResponse response = httpclient.execute(httpget);
                Log.d(TAG, "After httpResponse");
                StatusLine statusLine = response.getStatusLine();
                int statusCode = statusLine.getStatusCode();
                Log.d(TAG, "after statusCode, code= " + statusCode);
                if (statusCode == 200) {
                    Log.d(TAG, "Could connect");
                } else {
                    Log.d(TAG, "Couldn't connect");
                }
            } catch (Exception e) {
                Log.d(TAG, e.toString());
            }
            Log.d(TAG, "Exit do in background");
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            //message.setText("Postsequence");
        }
    }
}