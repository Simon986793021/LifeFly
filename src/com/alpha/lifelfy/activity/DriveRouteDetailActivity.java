/**
 * 
 */
package com.alpha.lifelfy.activity;

import adapter.DriveSegmentListAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alpha.lifelfy.utils.Utils;
import com.alpha.weather.R;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;

/**
 * @author Simon
 * @category 驾车路线详情
 */
public class DriveRouteDetailActivity extends Activity {
    private TextView timeTextView;
    private TextView priceText;
    private DrivePath drivePath;
    private DriveRouteResult driveRouteResult;
    private ListView listView;
    private DriveSegmentListAdapter driveSegmentListAdapter;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driveroutedetail);
        timeTextView = (TextView) findViewById(R.id.tv_time);
        imageView = (ImageView) findViewById(R.id.iv_back);
        priceText = (TextView) findViewById(R.id.tv_price);
        getIntentData();
        int dis = (int) drivePath.getDistance();
        int dur = (int) drivePath.getDuration();
        String time = Utils.getFriendlyTime(dur) + "("
                + Utils.getFriendlyLength(dis) + ")";
        timeTextView.setText(time);
        String price = Math.round(driveRouteResult.getTaxiCost()) + "";
        priceText.setText("打车约" + price + "元");

        /*
         * listview
         */
        listView = (ListView) findViewById(R.id.lv_drivedetail);
        driveSegmentListAdapter = new DriveSegmentListAdapter(
                getApplicationContext(), drivePath.getSteps());
        listView.setAdapter(driveSegmentListAdapter);

        /*
         * 点击返回事件
         */
        imageView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }

    /**
     * 
     */
    private void getIntentData() {
        // TODO Auto-generated method stub
        Intent intent = getIntent();
        drivePath = intent.getParcelableExtra("drivepath");
        driveRouteResult = intent.getParcelableExtra("driveresult");

    }
}
