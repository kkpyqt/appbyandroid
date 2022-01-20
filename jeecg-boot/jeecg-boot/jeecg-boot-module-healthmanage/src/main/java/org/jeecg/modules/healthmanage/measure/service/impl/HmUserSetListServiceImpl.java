package org.jeecg.modules.healthmanage.measure.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.healthmanage.measure.entity.HmUserSetList;
import org.jeecg.modules.healthmanage.measure.mapper.HmUserSetListMapper;
import org.jeecg.modules.healthmanage.measure.service.IHmUserSetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 设备用户清单
 * @Author: jeecg-boot
 * @Date:   2021-08-23
 * @Version: V1.0
 */
@Service
public class HmUserSetListServiceImpl extends ServiceImpl<HmUserSetListMapper, HmUserSetList> implements IHmUserSetListService {

    @Autowired
    private  HmUserSetListMapper hmUserSetListMapper;
    @Override
    public HmUserSetList  queryBySetMacAdd(String set_macadd)
    {
        return  hmUserSetListMapper.queryBySetMacAdd(set_macadd);
    }

    @Override
    public HmUserSetList  queryBySetImei(String set_imei)  {
        return  hmUserSetListMapper.queryBySetImei(set_imei);
    }

    @Override
    public HmUserSetList  queryBySetId(String set_id,String user_id)  {
        return  hmUserSetListMapper.queryBySetId(set_id,user_id);
    }
}
