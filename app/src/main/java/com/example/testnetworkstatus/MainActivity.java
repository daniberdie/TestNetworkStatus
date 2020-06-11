package com.example.testnetworkstatus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    TextView textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

        new AsyncConnectionCheck().execute();

    }

    public class AsyncConnectionCheck extends AsyncTask<String, String, String>
    {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        @Override
        protected String doInBackground(String... strings) {

            if (networkInfo != null) {
                //textView1.setText(networkInfo.toString());
                if (networkInfo.isConnected()) {
                    if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                        return "Wifi connected!"; //textView2.setText(R.string.wifi);
                    }
                    if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                        return "Mobile connected!";//textView2.setText(R.string.mobile);
                    }
                }
            }
            return "disconnected";
        }

        @Override
        protected void onPostExecute(String result)
        {
            if(result.equals("disconnected")) textView1.setText("No network operating!");
            else textView1.setText(networkInfo.toString());
            textView2.setText(result);
        }
    }
}
