package org.jeecg.modules.healthmanage.measure.service;

import org.jeecg.modules.healthmanage.measure.entity.HmMeasureInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @Description: 测量信息
 * @Author: jeecg-boot
 * @Date:   2021-08-17
 * @Version: V1.0
 */
public interface IHmMeasureInfoService extends IService<HmMeasureInfo> {
    public Long   queryCurrentAmount(String loginUser_id, String userSetId, String  device_type)  ; //按登陆用户+子用户+测量类型（血糖，血压，温度 获取当天测量总量）
    public Long  queryAllAmount(String loginUser_id, String userSetId, String  device_type,String startDate ,String endDate)  ; //按登陆用户+子用户+测量类型（血糖，血压，温度 获取总量
    //按起止日期提供折线图血压数据
    public   List<Map<String,Object>> queryBpMeasureDetail(String loginUser_id, String userSetId, String  device_type, String startDate, String endDate)  ;
    //按起止日期提供折线图血糖数据
    public List<Map<String, Object>> queryGluMeasureDetail(String loginUser_id, String userSetId, String  device_type, String startDate, String endDetail)  ;
//    体温
    public List<Map<String, Object>> queryTempMeasureDetail(String loginUser_id, String userSetId, String  device_type, String startDate, String endDetail)  ; //按起止日期提供折线图体温数据

}
