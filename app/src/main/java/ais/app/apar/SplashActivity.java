package ais.app.apar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import ais.app.apar.services.APARWebservice;


public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        UpdateMainContent updateMainContent = new UpdateMainContent();
//        updateMainContent.execute();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 3000);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    private class UpdateMainContent extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            Log.d(TAG, "sync main content");
        }

        @Override
        protected String doInBackground(String... parameters) {
            //update database when start up the app
            return APARWebservice.fetchAndUpdateMainContent(SplashActivity.this);
        }

        @Override
        protected void onPostExecute(String res) {

        }
    }


}
