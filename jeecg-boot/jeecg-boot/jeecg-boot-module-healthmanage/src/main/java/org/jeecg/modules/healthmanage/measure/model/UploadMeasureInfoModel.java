package org.jeecg.modules.healthmanage.measure.model;

import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * 登录表单
 *
 * @Author scott
 * @since  2019-01-18
 */
@ApiModel(value="上传测量信息对象", description="上传测量信息对象")

public class UploadMeasureInfoModel {

    /**
     * data : [{"deviceType":"BPM","unit":"m","adalert":" ","value":"120","createDate":"2018-3-26 22:07:00","measureType":"Svalue"},{"deviceType":"BPM","unit":"m","adalert":"","value":"80","createDate":"2018-3-26 22:07:00","measureType":"Dvalue"},{"deviceType":"BPM","unit":"m","adalert":"0","value":"79","createDate":"2018-3-26 22:07:00","measureType":"Pvalue"}]
     * Iccid : 86889754971095283811
     * set_Imei : 075527388751801
     * set_Macadd :
     * username : 1
     */
    private List<DataEntity> data;
    private String Iccid;
    private String set_Imei;
    private String set_Macadd;
    private String username;
    private String deviceType;

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public void setIccid(String Iccid) {
        this.Iccid = Iccid;
    }
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
    public String getDeviceType() {
        return deviceType;
    }

    public void setSet_Imei(String set_Imei) {
        this.set_Imei = set_Imei;
    }

    public void setSet_Macadd(String set_Macadd) {
        this.set_Macadd = set_Macadd;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public String getIccid() {
        return Iccid;
    }

    public String getSet_Imei() {
        return set_Imei;
    }

    public String getSet_Macadd() {
        return set_Macadd;
    }

    public String getUsername() {
        return username;
    }

    public static class DataEntity {
        /**

         * unit : m
         * adalert :
         * value : 120
         * createDate : 2018-3-26 22:07:00
         * measureType : Svalue
         */
        public DataEntity(){}

        private String unit;
        private String adalert;
        private String value;
        private String createDate;
        private String measureType;

        public String getMeasureType() {
            return measureType;
        }


        public void setUnit(String unit) {
            this.unit = unit;
        }

        public void setAdalert(String adalert) {
            this.adalert = adalert;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public void setMeasureType(String measureType) {
            this.measureType = measureType;
        }



        public String getUnit() {
            return unit;
        }

        public String getAdalert() {
            return adalert;
        }

        public String getValue() {
            return value;
        }

        public String getCreateDate() {
            return createDate;
        }


    }
}
