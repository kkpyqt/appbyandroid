package org.jeecg.modules.healthmanage.measure.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.healthmanage.measure.entity.HmUserSetRel;
import org.jeecg.modules.healthmanage.measure.mapper.HmUserSetRelMapper;
import org.jeecg.modules.healthmanage.measure.service.IHmUserSetRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 设备用户关系表
 * @Author: jeecg-boot
 * @Date:   2021-08-23
 * @Version: V1.0
 */
@Service
public class HmUserSetRelServiceImpl extends ServiceImpl<HmUserSetRelMapper, HmUserSetRel> implements IHmUserSetRelService {
    @Autowired
    private  HmUserSetRelMapper hmUserSetRelMapper;
    @Override
    public HmUserSetRel  queryBySetMacAdd(String set_macadd)
    {
        return  hmUserSetRelMapper.queryBySetMacAdd(set_macadd);
    }

    @Override
    public HmUserSetRel  queryBySetImei(String set_imei)  {
        return  hmUserSetRelMapper.queryBySetImei(set_imei);
    }
//    public HmUserSetRel  queryBySetIccid(String set_imei)  ;
    @Override
    public HmUserSetRel  queryBySetIccid(String set_imei)  {
        return  hmUserSetRelMapper.queryBySetIccid(set_imei);
    }

}
