package org.jeecg.modules.healthmanage.measure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.healthmanage.measure.entity.HmMeasureInfo;

import java.util.List;
import java.util.Map;

/**
 * @Description: 测量信息
 * @Author: jeecg-boot
 * @Date:   2021-08-17
 * @Version: V1.0
 */
public interface HmMeasureInfoMapper extends BaseMapper<HmMeasureInfo> {
   public Long  queryCurrentAmount(@Param("loginUser_id")  String loginUser_id,@Param("userSetId")   String userSetId, @Param("device_type")   String  device_type)  ; //按登陆用户+子用户+测量类型（血糖，血压，温度 获取当天测量总量）
    public Long  queryAllAmount(@Param("loginUser_id")  String loginUser_id,
                                @Param("userSetId")   String userSetId,
                                @Param("device_type")   String  device_type,
                                @Param("startDate")  String startDate,
                                @Param("endDate")  String endDate)  ; //按登陆用户+子用户+测量类型（血糖，血压，温度 获取总量
    //按起止日期提供折线图血压数据
    public List<Map<String,Object>> queryBpMeasureDetail(@Param("loginUser_id")  String loginUser_id,@Param("userSetId")   String userSetId,
                                                         @Param("device_type")   String  device_type, @Param("startDate")  String startDate,
                                                         @Param("endDate")  String endDate)  ;
    //按起止日期提供折线图血糖数据

    public List<Map<String,Object>> queryGluMeasureDetail(@Param("loginUser_id")  String loginUser_id,@Param("userSetId")   String userSetId,
                                                         @Param("device_type")   String  device_type, @Param("startDate")  String startDate,
                                                         @Param("endDate")  String endDate)  ;
    //    体温
    public List<Map<String,Object>> queryTempMeasureDetail(@Param("loginUser_id")  String loginUser_id,@Param("userSetId")   String userSetId,
                                                          @Param("device_type")   String  device_type, @Param("startDate")  String startDate,
                                                          @Param("endDate")  String endDate)  ;
}
