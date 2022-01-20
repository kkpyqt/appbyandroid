package org.jeecg.modules.healthmanage.measure.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.healthmanage.measure.entity.HmMeasureInfo;
import org.jeecg.modules.healthmanage.measure.mapper.HmMeasureInfoMapper;
import org.jeecg.modules.healthmanage.measure.service.IHmMeasureInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description: 测量信息
 * @Author: jeecg-boot
 * @Date:   2021-08-17
 * @Version: V1.0
 */
@Service
public class HmMeasureInfoServiceImpl extends ServiceImpl<HmMeasureInfoMapper, HmMeasureInfo> implements IHmMeasureInfoService {

    @Resource
    private HmMeasureInfoMapper hmMeasureInfoMapper;

    @Override
    public Long queryCurrentAmount(String loginUser_id, String userSetId, String  device_type) {
        return hmMeasureInfoMapper.queryCurrentAmount( loginUser_id,  userSetId,   device_type) ;
    }

    @Override
    public Long queryAllAmount(String loginUser_id, String userSetId, String  device_type,String startDate ,String endDate)  {
        return hmMeasureInfoMapper.queryAllAmount(loginUser_id,  userSetId,  device_type, startDate ,  endDate);
    }
    //update-end--Author:zhangweijian  Date:20190428 for：传入开始时间，结束时间参数
//    获取血压
    @Override
    public List<Map<String,Object>> queryBpMeasureDetail(String loginUser_id, String userSetId, String  device_type, String startDate, String endDate)  {
//       接口实现，调用xml 中的sql
        return hmMeasureInfoMapper.queryBpMeasureDetail( loginUser_id,  userSetId,   device_type,  startDate,  endDate);
    }
//    血糖
    @Override
    public List<Map<String, Object>> queryGluMeasureDetail(String loginUser_id, String userSetId, String  device_type, String startDate, String endDate)  {
//
        return hmMeasureInfoMapper.queryGluMeasureDetail( loginUser_id,  userSetId,   device_type,  startDate,  endDate);
    }
//体温
    @Override
    public List<Map<String, Object>> queryTempMeasureDetail(String loginUser_id, String userSetId, String  device_type, String startDate, String endDate)  {
//        String dbType = CommonUtils.getDatabaseType();
        return hmMeasureInfoMapper.queryTempMeasureDetail( loginUser_id,  userSetId,   device_type,  startDate,  endDate);
    }
}


