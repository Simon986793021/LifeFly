package com.alpha.lifelfy.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.alpha.weather.R;

/**
 * @author Simon 2016.6.24
 */
public class MainActivity extends Activity implements OnClickListener {
    private Button button;
    private Button weatherButton;
    private Button baiduButton;
    private Button navigationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.map);
        weatherButton = (Button) findViewById(R.id.weather);
        baiduButton = (Button) findViewById(R.id.baidu);
        navigationButton = (Button) findViewById(R.id.navigation);
        button.setOnClickListener(this);
        weatherButton.setOnClickListener(this);
        baiduButton.setOnClickListener(this);
        navigationButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
        case R.id.map:
            Intent intent = new Intent(MainActivity.this, MapActivity.class);
            startActivity(intent);
            break;
        // case R.id.weather:
        // Intent intent2 = new Intent(MainActivity.this,
        // WeatherActivity.class);
        // startActivity(intent2);
        case R.id.baidu:
            Intent intent3 = new Intent(MainActivity.this,
                    BrowserActivity.class);
            startActivity(intent3);
        case R.id.navigation:
            Intent intent4 = new Intent(MainActivity.this,
                    NavigationActivity.class);
            startActivity(intent4);
        default:
            break;
        }
    }
}