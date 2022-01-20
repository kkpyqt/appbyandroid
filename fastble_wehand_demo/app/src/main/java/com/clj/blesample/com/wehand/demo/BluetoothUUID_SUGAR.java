package com.clj.blesample.com.wehand.demo;

import java.util.UUID;

/**
 * 使用时替换下列为你对应的uuid
 * Created by qin on 2017/1/31.
 */

public interface BluetoothUUID_SUGAR {

    String[] BLENAMEARRAY_SUGAR = {"Glucose","glucose","JN-163A",};
    String[] BLENAMEARRAY_BLOODTOOTH = {"BP826","JN-163D","GLM76","Yasee_GLM76","Yasee_GLM79","BP826","JN-163A","JN-163B","JN-163C","JN-163D"};
    //BLOODTOOTH
    UUID SERVICE_BLOODTOOTH = UUID.fromString("0000fff0-0000-1000-8000-00805f9b34fb"); //服务

    UUID RESULT_BLOODTOOTH = UUID.fromString("0000fff4-0000-1000-8000-00805f9b34fb"); //特性结果
    //SUGAR
    UUID SERVICE_SUGAR = UUID.fromString("00001808-0000-1000-8000-00805f9b34fb"); //服务
    UUID READ_SUGAR = UUID.fromString("00002a6d-0000-1000-8000-00805f9b34fb");   //特性状态
    UUID RESULT_SUGAR = UUID.fromString("00002a18-0000-1000-8000-00805f9b34fb"); //特性结果

    //电池电量
    UUID BATTERY_SERVICE = UUID.fromString("0000180f-0000-1000-8000-00805f9b34fb");
    UUID BATTERY_NOTIFICATION = UUID.fromString("00002a19-0000-1000-8000-00805f9b34fb");
}
