/**
 * 
 */
package com.alpha.weather;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MyLocationStyle;

/**
 * @author Simon
 */
public class MapActivity extends Activity implements LocationSource,
        AMapLocationListener {
    /*
     * 定义变量
     */
    private AMap aMap;
    private MapView mapView;
    private AMapLocationClient aMapLocationClient = null;
    private AMapLocationClientOption aMapLocationClientOption = null;// 设置定位参数
    private OnLocationChangedListener onLocationChangedListener = null;
    private boolean ismove = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mapView.getMap();
            // 设置显示定位按钮 并且可以点击
            UiSettings settings = aMap.getUiSettings();
            aMap.setLocationSource(this);// 设置了定位的监听
            // 是否显示定位按钮
            settings.setMyLocationButtonEnabled(true);
            aMap.setMyLocationEnabled(true);// 显示定位层并且可以触发定位,默认是flase
        }
        startLocation();
        setUpMap();

    }

    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {
        // 自定义系统定位小蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory
                .fromResource(R.drawable.location_marker));// 设置小蓝点的图标
        myLocationStyle.strokeColor(Color.BLACK);// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(100, 255, 255, 255));// 设置圆形的填充颜色
        // myLocationStyle.anchor(int,int)//设置小蓝点的锚点
        myLocationStyle.strokeWidth(1.0f);// 设置圆形的边框粗细
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setLocationSource(this);// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        // aMap.setMyLocationType()
    }

    /*
     * 开始定位
     */
    private void startLocation() {
        // TODO Auto-generated method stub
        aMapLocationClient = new AMapLocationClient(getApplicationContext());
        aMapLocationClient.setLocationListener(this);
        aMapLocationClientOption = new AMapLocationClientOption();
        aMapLocationClientOption
                .setLocationMode(AMapLocationMode.Hight_Accuracy);
        aMapLocationClientOption.setNeedAddress(true);
        aMapLocationClientOption.setInterval(2000);
        aMapLocationClient.setLocationOption(aMapLocationClientOption);
        // 启动定位
        aMapLocationClient.startLocation();
    }

    /*
     * 当activity销毁时，停止定位
     */
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        mapView.onDestroy();
        aMapLocationClient.stopLocation();
        aMapLocationClient.onDestroy();
    }

    @Override
    public void onLocationChanged(AMapLocation arg0) {
        // TODO Auto-generated method stub
        if (arg0 != null) {
            if (arg0.getErrorCode() == 0) {
                // 定位成功回调信息，设置相关消息
                // arg0.getLocationType();// 获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                // arg0.getLatitude();// 获取纬度
                // arg0.getLongitude();// 获取经度
                // arg0.getAccuracy();// 获取精度信息
                // SimpleDateFormat df = new SimpleDateFormat(
                // "yyyy-MM-dd HH:mm:ss");
                // Date date = new Date(arg0.getTime());
                // df.format(date);// 定位时间
                // arg0.getAddress();//
                // 地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                // arg0.getCountry();// 国家信息
                // arg0.getProvince();// 省信息
                // arg0.getCity();// 城市信息
                // arg0.getDistrict();// 城区信息
                // arg0.getStreet();// 街道信息
                // arg0.getStreetNum();// 街道门牌号信息
                // arg0.getCityCode();// 城市编码
                // arg0.getAdCode();// 地区编码
                if (ismove) {
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
                    aMap.moveCamera(CameraUpdateFactory
                            .changeLatLng(new LatLng(arg0.getLatitude(), arg0
                                    .getLongitude())));
                    onLocationChangedListener.onLocationChanged(arg0);

                    StringBuffer buffer = new StringBuffer();
                    buffer.append(arg0.getCountry() + "" + arg0.getProvince()
                            + "" + arg0.getCity() + "" + arg0.getDistrict()
                            + "" + arg0.getStreet() + "" + arg0.getStreetNum());
                    // 将地址显示出来
                    Toast.makeText(getApplicationContext(), buffer.toString(),
                            Toast.LENGTH_LONG).show();
                    ismove = false;
                }

            } else {
                // 显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError",
                        "location Error, ErrCode:" + arg0.getErrorCode()
                                + ", errInfo:" + arg0.getErrorInfo());
                Toast.makeText(getApplicationContext(), "定位失败",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    /*
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener arg0) {
        // TODO Auto-generated method stub
        onLocationChangedListener = arg0;
    }

    /*
     * (non-Javadoc) 取消定位
     * 
     * @see com.amap.api.maps2d.LocationSource#deactivate()
     */
    @Override
    public void deactivate() {
        // TODO Auto-generated method stub
        onLocationChangedListener = null;
    }
}
