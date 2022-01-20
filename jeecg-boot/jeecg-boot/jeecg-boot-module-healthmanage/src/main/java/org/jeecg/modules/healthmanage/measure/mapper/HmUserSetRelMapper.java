package org.jeecg.modules.healthmanage.measure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.healthmanage.measure.entity.HmUserSetRel;

/**
 * @Description: 设备用户关系表
 * @Author: jeecg-boot
 * @Date:   2021-08-23
 * @Version: V1.0
 */
public interface HmUserSetRelMapper extends BaseMapper<HmUserSetRel> {

    public HmUserSetRel  queryBySetMacAdd(String set_macadd)  ;
    public HmUserSetRel  queryBySetImei(String set_imei)  ;
//    queryBySetIccid
public HmUserSetRel  queryBySetIccid(String set_imei)  ;
}
