package com.clj.blesample;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.clj.blesample.adapter.DeviceAdapter;
import com.clj.blesample.com.wehand.demo.BluetoothUUID_SUGAR;
import com.clj.blesample.com.wehand.demo.bloodpress.BleInterface;
import com.clj.blesample.com.wehand.demo.bloodpress.ComputerFragment;
import com.clj.blesample.com.wehand.demo.bloodpress.DripBloodFragment;
import com.clj.blesample.com.wehand.demo.bloodpress.InsertTripFragment;
import com.clj.blesample.com.wehand.demo.bloodpress.ResultFragment;
import com.clj.blesample.com.wehand.demo.bloodpress.SearchFragment;
import com.clj.blesample.com.wehand.demo.bloodpress.StatusFragment;
import com.clj.blesample.com.wehand.demo.bloodpress.StopSearchFragment;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleGattCallback;
import com.clj.fastble.callback.BleMtuChangedCallback;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.callback.BleRssiCallback;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.clj.fastble.scan.BleScanRuleConfig;
import com.clj.fastble.utils.HexUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.UUID;

import static com.clj.fastble.data.BleScanState.STATE_SCANNING;
import static java.lang.Integer.parseInt;


public class BloodPressMainActivity extends AppCompatActivity implements View.OnClickListener,BleInterface {

    private static final String TAG = BloodPressMainActivity.class.getSimpleName();
    private static final int REQUEST_CODE_OPEN_GPS = 1;
    private static final int REQUEST_CODE_PERMISSION_LOCATION = 2;

    private LinearLayout layout_setting;
    private TextView txt_setting;
    private TextView btn_scan;

    private ImageView img_loading;

    private Toolbar toolbar;

    private Animation operatingAnim;
    private DeviceAdapter mDeviceAdapter;
    private ProgressDialog progressDialog;

    private List<Fragment> fragments = new ArrayList<>();
    private int currentPage = 0;
    private String[] titles = new String[7];

    public BleDevice mBleDevice;
    public BluetoothGatt mBluetoothGatt;

    public  String sugarResult;

    private int icount = 1;

    ViewPager mViewPager;

    private Button tofastble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugar_main);
        initView();
        initData();

        BleManager.getInstance().init(getApplication());
        BleManager.getInstance()
                .enableLog(true)
                .setReConnectCount(1, 5000)
                .setConnectOverTime(20000)
                .setOperateTimeout(5000);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BleManager.getInstance().disconnectAllDevice();
        BleManager.getInstance().destroy();
    }

    @Override
    public  ViewPager getViewPager(){
        return  mViewPager;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_scan_ble:
                if (btn_scan.getText().equals(getString(R.string.start_scan))) {
                    checkPermissions();
                    btn_scan.setText(getString(R.string.stop_scan));
                    btn_scan.setVisibility(View.VISIBLE);

                } else if (btn_scan.getText().equals(getString(R.string.stop_scan))) {
                    txt_setting.setText("查找或连接智能血压已停止");
                    btn_scan.setVisibility(View.VISIBLE);
                    changePage(6);
                    if(BleManager.getInstance().getScanSate() == STATE_SCANNING){
                        BleManager.getInstance().cancelScan();
                    }

                    btn_scan.setText(getString(R.string.start_scan));

                }
                break;
            case R.id.tofastble:
                startmainactivity();
                break;


        }
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_ble);
//        toolbar.setTitle(titles[0]);
        setSupportActionBar(toolbar);

        btn_scan = (TextView) findViewById(R.id.btn_scan_ble);
        btn_scan.setText(getString(R.string.start_scan));
        btn_scan.setOnClickListener(this);


        txt_setting = (TextView) findViewById(R.id.txt_setting_ble);
        txt_setting.setOnClickListener(this);

        tofastble = findViewById(R.id.tofastble);
        tofastble.setOnClickListener(this);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initPage();
        checkPermissions();

    }

    private void showConnectedDevice() {
        List<BleDevice> deviceList = BleManager.getInstance().getAllConnectedDevice();
        mDeviceAdapter.clearConnectedDevice();
        for (BleDevice bleDevice : deviceList) {
            mDeviceAdapter.addDevice(bleDevice);
        }
        mDeviceAdapter.notifyDataSetChanged();
    }

    private void setScanRule() {

        String[] names;

        names = BluetoothUUID_SUGAR.BLENAMEARRAY_BLOODTOOTH; //血糖仪广播名称

//        boolean isAutoConnect = sw_auto.isChecked();
        boolean isAutoConnect = false;

        BleScanRuleConfig scanRuleConfig = new BleScanRuleConfig.Builder()
                .setDeviceName(true, names)   // 只扫描指定广播名的设备，可选
                .setAutoConnect(isAutoConnect)      // 连接时的autoConnect参数，可选，默认false
                .setScanTimeOut(10000)              // 扫描超时时间，可选，默认10秒
                .build();
        BleManager.getInstance().initScanRule(scanRuleConfig);
    }

    private void startScan() {
        BleManager.getInstance().scan(new BleScanCallback() {
            @Override
            public void onScanStarted(boolean success) {
//                mDeviceAdapter.clearScanDevice();
//                mDeviceAdapter.notifyDataSetChanged();
                //显示搜索框
//                img_loading.startAnimation(operatingAnim);
//                img_loading.setVisibility(View.VISIBLE);
                txt_setting.setText("正在查找并连接智能血糖仪");
                changePage(0);//  [0搜索、1已连接（未插卡）、2插卡、3滴血、4计算、5结果、6停止]
                btn_scan.setText(getString(R.string.stop_scan));
                btn_scan.setVisibility(View.VISIBLE);


            }

            @Override
            public void onLeScan(BleDevice bleDevice) {
                super.onLeScan(bleDevice);
                if(bleDevice.getName()!= "" || !bleDevice.getName().isEmpty()){
                    Log.i("BLE on le",bleDevice.getName());


                }




            }

            @Override
            public void onScanning(BleDevice bleDevice) {
//                mDeviceAdapter.addDevice(bleDevice);
//                mDeviceAdapter.notifyDataSetChanged();
                Log.i("BLE on scan",bleDevice.getName());
                //                直接根据结果中判断，有找到就匹配UUID 然后连接
                if(bleDevice.getName().length() >1 ){
                    Log.i("BLE on le",bleDevice.getName());
                    byte[] cmScanRecord = bleDevice.getScanRecord();
                    String cmUUID = bytesToHexString(cmScanRecord);


                    if (cmUUID.equals("08180A18") || cmUUID.equals("F0FF0809")) {
//                    Toast.makeText(MainActivity2.this, "设备相符", Toast.LENGTH_LONG).show();先取消
                        BleManager.getInstance().cancelScan();
                        connect(bleDevice);
                    }

//                F0FF0809
                    if (cmUUID.equals("F0FF0809")) {
//                    Toast.makeText(MainActivity2.this, "设备相符", Toast.LENGTH_LONG).show();先取消
                        BleManager.getInstance().cancelScan();
                        connect(bleDevice);
                    }

                }

            }

            @Override
            public void onScanFinished(List<BleDevice> scanResultList) {

                btn_scan.setText(getString(R.string.stop_scan));
//                btn_scan.setVisibility(View.GONE);

//                connect(scanResultList.get(0));
            }
        });
    }


    public void connect(final BleDevice bleDevice) {
        BleManager.getInstance().connect(bleDevice, new BleGattCallback() {
            @Override
            public void onStartConnect() {
            }

            @Override
            public void onConnectFail(BleDevice bleDevice, BleException exception) {
                img_loading.clearAnimation();
                img_loading.setVisibility(View.INVISIBLE);
                btn_scan.setVisibility(View.INVISIBLE);
                btn_scan.setText(getString(R.string.start_scan));
                Toast.makeText(BloodPressMainActivity.this, getString(R.string.connect_fail), Toast.LENGTH_LONG).show();
            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            @Override
            public void onConnectSuccess(BleDevice bleDevice, BluetoothGatt gatt, int status) {
                txt_setting.setText("连接成功");

                changePage(1);  //[搜索、插卡、滴血、计算、结果、停止]
                mBleDevice = bleDevice;
                mBluetoothGatt = gatt ;
                listenToResult2(bleDevice,gatt);


            }

            @Override
            public void onDisConnected(boolean isActiveDisConnected, BleDevice bleDevice, BluetoothGatt gatt, int status) {

//                 btn_scan.setText(getString(R.string.start_scan)); [0搜索、1已连接（未插卡）、2插卡、3滴血、4计算、5结果、6停止]
                changePage(6);
                if (isActiveDisConnected) {
                    Toast.makeText(BloodPressMainActivity.this, getString(R.string.active_disconnected), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(BloodPressMainActivity.this, getString(R.string.disconnected), Toast.LENGTH_LONG).show();
//                    ObserverManager.getInstance().notifyObserver(bleDevice);
                }

            }
        });
    }

    private void readRssi(BleDevice bleDevice) {
        BleManager.getInstance().readRssi(bleDevice, new BleRssiCallback() {
            @Override
            public void onRssiFailure(BleException exception) {
                Log.i(TAG, "onRssiFailure" + exception.toString());
            }

            @Override
            public void onRssiSuccess(int rssi) {
                Log.i(TAG, "onRssiSuccess: " + rssi);
            }
        });
    }

    private void setMtu(BleDevice bleDevice, int mtu) {
        BleManager.getInstance().setMtu(bleDevice, mtu, new BleMtuChangedCallback() {
            @Override
            public void onSetMTUFailure(BleException exception) {
                Log.i(TAG, "onsetMTUFailure" + exception.toString());
            }

            @Override
            public void onMtuChanged(int mtu) {
                Log.i(TAG, "onMtuChanged: " + mtu);
            }
        });
    }

    @Override
    public final void onRequestPermissionsResult(int requestCode,
                                                 @NonNull String[] permissions,
                                                 @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION_LOCATION:
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                            onPermissionGranted(permissions[i]);
                        }
                    }
                }
                break;
        }
    }

    private void checkPermissions() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!bluetoothAdapter.isEnabled()) {
            Toast.makeText(this, getString(R.string.please_open_blue), Toast.LENGTH_LONG).show();
            return;
        }

        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
        List<String> permissionDeniedList = new ArrayList<>();
        for (String permission : permissions) {
            int permissionCheck = ContextCompat.checkSelfPermission(this, permission);
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                onPermissionGranted(permission);
            } else {
                permissionDeniedList.add(permission);
            }
        }
        if (!permissionDeniedList.isEmpty()) {
            String[] deniedPermissions = permissionDeniedList.toArray(new String[permissionDeniedList.size()]);
            ActivityCompat.requestPermissions(this, deniedPermissions, REQUEST_CODE_PERMISSION_LOCATION);
        }
    }

    private void onPermissionGranted(String permission) {
        switch (permission) {
            case Manifest.permission.ACCESS_FINE_LOCATION:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !checkGPSIsOpen()) {
                    new AlertDialog.Builder(this)
                            .setTitle(R.string.notifyTitle)
                            .setMessage(R.string.gpsNotifyMsg)
                            .setNegativeButton(R.string.cancel,
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                        }
                                    })
                            .setPositiveButton(R.string.setting,
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                            startActivityForResult(intent, REQUEST_CODE_OPEN_GPS);
                                        }
                                    })

                            .setCancelable(false)
                            .show();
                } else {
                    setScanRule();
                    startScan();
                    Log.i("调试模式","开始启动");
                }
                break;
        }
    }

    private boolean checkGPSIsOpen() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager == null)
            return false;
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_OPEN_GPS) {
            if (checkGPSIsOpen()) {
                setScanRule();
                startScan();
            }
        }
    }

    private void initData() {
//        bleDevice = getIntent().getParcelableExtra(KEY_DATA);
//        if (bleDevice == null)
//            finish();
        //标题栏
        titles = new String[]{
                "智能设备查找",
                "智能设备测量操作",
                "插卡",
                "滴血",
                "计算",
                "测量结果",
                "停止搜索"
        };
    }

    //刷新数据
    private void initPage() {
        prepareFragment();
        changePage(1);  //默认是1

    }

    public void changePage(int page) {
        currentPage = page;
        toolbar.setTitle(titles[page]);
        updateFragment(page);

    }

    //服务列表[0搜索、1已连接（未插卡）、2插卡、3滴血、4计算、5结果、6停止]
    private void prepareFragment() {
        fragments.add(new SearchFragment());  //连接 0
        fragments.add(new StatusFragment());   //状态  1
        fragments.add(new InsertTripFragment());//2
        fragments.add(new DripBloodFragment());   //状态2 请滴血   3
        fragments.add(new ComputerFragment());   //结果1 结果计算中  4
        fragments.add(new ResultFragment());   //结果2 结果展示  5
        fragments.add(new StopSearchFragment());   //停止   5
        for (Fragment fragment : fragments) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_sugar, fragment).hide(fragment).commit(); //这个跟界面控件绑定
        }
    }

    //更新操作选项
    private void updateFragment(int position) {
        if (position > fragments.size() - 1) {
            return;
        }
        for (int i = 0; i < fragments.size(); i++) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            Fragment fragment = fragments.get(i);
            if (i == position) {
                transaction.show(fragment);
            } else {
                transaction.hide(fragment);
            }
//            transaction.commit();
            transaction.commitAllowingStateLoss();
        }
    }

    public BleDevice getBleDevice() {
        return mBleDevice;
    }


    public void setTxt_setting(String setting){
        txt_setting.setText(setting);
        txt_setting.setVisibility(View.VISIBLE);
    }
    public void setShowBtn(){
        btn_scan.setText(getString(R.string.start_scan));
        btn_scan.setVisibility(View.VISIBLE);
    }
    //取广播字段
        public static String bytesToHexString(byte[] bArray) {
            StringBuffer sb = new StringBuffer(bArray.length);
            String sTemp;
            for (int i = 5; i < 9; i++) {
                sTemp = Integer.toHexString(0xFF & bArray[i]);
                if (sTemp.length() < 2)
                    sb.append(0);
                sb.append(sTemp.toUpperCase());
            }
            return sb.toString();
        }
    //   结果显示,一体机的输出
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void listenToResult2(final BleDevice bleDevice, final BluetoothGatt gatt) {
        BluetoothGattService service = gatt.getService(UUID.fromString(String.valueOf(BluetoothUUID_SUGAR.SERVICE_BLOODTOOTH)));//固定uui的service
        final BluetoothGattCharacteristic characteristic_2a18 = service.getCharacteristic(UUID.fromString(String.valueOf(BluetoothUUID_SUGAR.RESULT_BLOODTOOTH)));
        BleManager.getInstance().notify(
                bleDevice,
                characteristic_2a18.getService().getUuid().toString(),
                characteristic_2a18.getUuid().toString(),
                new BleNotifyCallback() {

                    @Override
                    public void onNotifySuccess() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//
                                // [0搜索、1已连接（未插卡）、2插卡、3滴血、4计算、5结果、6停止]
                                txt_setting.setText("测量结果");
                                txt_setting.setVisibility(View.VISIBLE);
                                changePage(4);


                            }
                        });
                    }

                    @Override
                    public void onNotifyFailure(BleException exception) {
                        if (BleManager.getInstance().isConnected(bleDevice) != true) {
                            changePage(1);
                            connect(bleDevice);
                        } else {
                            listenToResult2(bleDevice, gatt);
                        }

                    }


                    @Override
                    public void onCharacteristicChanged(final byte[] data) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String mearesult = HexUtil.formatHexString(characteristic_2a18.getValue(), true);
                                if (TextUtils.isEmpty(mearesult)) return;
                                if (BleManager.getInstance().isConnected(bleDevice) != true) {
                                    changePage(1);
                                    connect(bleDevice);
                                } else {
                                    String[] result2a6d = mearesult.split(" ");
//                                修改点5

                                    Log.i("数据接收1", result2a6d[0] + result2a6d[1] + result2a6d[2]);
//                                监听是否正常接收
                                    if (result2a6d[2].contains("05")) {
                                        txt_setting.setText("测量数据接收中");
//                                    展示测量结果在界面上，是个字符串数据，不能用追加的方式，可以用update
                                        String dyresulte = result2a6d[6];
                                        Integer resetreluste = Integer.parseInt(dyresulte, 16);
//                                    resetText(showtipTextview, resetreluste.toString());

//                                    todo 显示中间过程停顿1 秒
//                                        timer.schedule(new TimerTask() {
//                                            @Override
//                                            public void run() {
//                                                pb_measure_result.setText(resetreluste.toString()); //界面中间显示倒计时
//                                                //do something
//                                            }
//                                        },500);//延时0.5s执行

//                                       测量结果出来后，给个界面
//                                    showImageView(R.drawable.bloodsuger1);
                                    } else if (result2a6d[2].contains("08")) {
//                                    notifyBlood(bleDevice, gatt);
//                                    直接解析，然后停止通过，跳转到结果
                                        if (icount > 1) {
                                            //                                        取消订阅通知
                                            BleManager.getInstance().stopNotify(bleDevice, characteristic_2a18.getService().getUuid().toString(),
                                                    characteristic_2a18.getUuid().toString());
                                            BleManager.getInstance().disconnectAllDevice();
                                            BleManager.getInstance().destroy();
                                        } else {
//
                                            String mearesult1 = HexUtil.formatHexString(characteristic_2a18.getValue(), true);
                                            String[] results = mearesult.split(" ");
                                            //高压
                                            String m = results[6];
                                            Log.i("数据接收高压", m);
//                                低压
                                            String n = results[8];
                                            Log.i("数据接收低压", n);
//                                心率
                                            String i = results[9];
                                            Log.i("数据接收心率", i);
                                            Integer[] bloodsize1 = {Integer.parseInt(m, 16), Integer.parseInt(n, 16), Integer.parseInt(i, 16),};
                                            String bloodsize = bloodsize1[0].toString() + "," + bloodsize1[1].toString() + "," + bloodsize1[2].toString();
                                            Log.i("数据接收2", bloodsize);
//                                不再接收数据了
//                                addText(showtipTextview,"测量结果："+bloodsize);
//                                            startMeasureResultAct(bloodsize);
                                        }


                                    }

                                }


                            }


                        });
                    }
                });
    }






    //    处理消息结果 1取设备状态 2 取结果


//    public String getResult(){
//      return sugarResult;
//    }
//    public  void setResult(String valueResult){
//        this.sugarResult = valueResult ;
//    }

    //返回
    public void startmainactivity(){
        Intent intent2 = new Intent();
        intent2.setClass(this, MainActivity.class);
        startActivity(intent2);
        if(mBleDevice != null){
            BleManager.getInstance().disconnect(mBleDevice);
            BleManager.getInstance().clearCharacterCallback(mBleDevice);
        }

        BleManager.getInstance().disconnectAllDevice();

        finish();
    }

}
