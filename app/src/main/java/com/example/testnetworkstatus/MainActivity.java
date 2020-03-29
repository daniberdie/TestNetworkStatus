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

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null) {
            textView1.setText(networkInfo.toString());
            if (networkInfo.isConnected())
            {
                if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI)
                {
                    textView2.setText(R.string.wifi);
                }
                if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE)
                {
                    textView2.setText(R.string.mobile);
                }
            }
            else
            {
                textView2.setText(R.string.disconnected);
            }
        }
    }
}
