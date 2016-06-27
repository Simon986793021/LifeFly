package com.alpha.weather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author Simon 2016.6.24
 */
public class MainActivity extends Activity implements OnClickListener {
    private Button button;
    private Button weatherButton;
    private Button baiduButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.map);
        weatherButton = (Button) findViewById(R.id.weather);
        baiduButton = (Button) findViewById(R.id.baidu);
        button.setOnClickListener(this);
        weatherButton.setOnClickListener(this);
        baiduButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
        case R.id.map:
            Intent intent = new Intent(MainActivity.this, MapActivity.class);
            startActivity(intent);
            break;
        case R.id.weather:
            Intent intent2 = new Intent(MainActivity.this,
                    WeatherActivity.class);
            startActivity(intent2);
        case R.id.baidu:
            Intent intent3 = new Intent(MainActivity.this,
                    BrowserActivity.class);
            startActivity(intent3);
        default:
            break;
        }
    }
}