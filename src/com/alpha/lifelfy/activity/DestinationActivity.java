/**
 * 
 */
package com.alpha.lifelfy.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.alpha.weather.R;

/**
 * @author Simon
 * @category 目的地输入页面
 */
public class DestinationActivity extends Activity implements OnClickListener,
        OnItemClickListener, TextWatcher {
    private RecomandAdapter recomandAdapter;
    private ListView listView;
    private Button button;
    private ImageView imageView;
    private EditText editText;
    private RouteTask routeTask;
    private PositionEntity positionEntity;
    private String city;// 搜索的城市

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detination);
        listView = (ListView) findViewById(R.id.recommend_list);
        imageView = (ImageView) findViewById(R.id.destination_back);
        imageView.setOnClickListener(this);

        button = (Button) findViewById(R.id.destination_search);
        button.setOnClickListener(this);

        editText = (EditText) findViewById(R.id.destination_edittext);
        editText.addTextChangedListener(this);
        recomandAdapter = new RecomandAdapter(getApplicationContext());
        listView.setAdapter(recomandAdapter);
        listView.setOnItemClickListener(this);
        routeTask = RouteTask.getInstance(getApplicationContext());
        positionEntity = routeTask.getStartPoint();
        city = positionEntity.city;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.text.TextWatcher#beforeTextChanged(java.lang.CharSequence,
     * int, int, int)
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
            int after) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see android.text.TextWatcher#onTextChanged(java.lang.CharSequence, int,
     * int, int)
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // TODO Auto-generated method stub
        InputTipTask.getInstance(getApplicationContext(), recomandAdapter)
                .searchTips(s.toString(), city);
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.text.TextWatcher#afterTextChanged(android.text.Editable)
     */
    @Override
    public void afterTextChanged(Editable s) {
        // TODO Auto-generated method stub

    }

    /*
     * ListView点击事件
     * 
     * @see
     * android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget
     * .AdapterView, android.view.View, int, long)
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
            long id) {
        // TODO Auto-generated method stub
        PositionEntity entity = (PositionEntity) recomandAdapter
                .getItem(position);
        if (entity.latitue == 0 && entity.longitude == 0) {

            PoiSearchTask poiSearchTask = new PoiSearchTask(
                    getApplicationContext(), recomandAdapter);
            poiSearchTask.search(entity.address, entity.city);
        } else {
            routeTask = RouteTask.getInstance(getApplicationContext());
            routeTask.setEndPoint(entity);
            Toast.makeText(getApplicationContext(), entity.address,
                    Toast.LENGTH_SHORT).show();
            editText.setText(entity.address);

        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
        case R.id.destination_back:
            Intent intent = new Intent(DestinationActivity.this,
                    NavigationActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            finish();
            break;
        case R.id.destination_search:
            PoiSearchTask poiSearchTask = new PoiSearchTask(
                    getApplicationContext(), recomandAdapter);
            poiSearchTask.search(editText.getText().toString(), city);
            Intent intent2 = new Intent(DestinationActivity.this,
                    NavigationActivity.class);
            startActivity(intent2);
            finish();
            break;
        }
    }
}
