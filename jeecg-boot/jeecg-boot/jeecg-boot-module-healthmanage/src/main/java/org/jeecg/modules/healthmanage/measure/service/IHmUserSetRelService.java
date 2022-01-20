package org.jeecg.modules.healthmanage.measure.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.healthmanage.measure.entity.HmUserSetRel;

/**
 * @Description: 设备用户关系表
 * @Author: jeecg-boot
 * @Date:   2021-08-23
 * @Version: V1.0
 */
public interface IHmUserSetRelService extends IService<HmUserSetRel> {

    public HmUserSetRel  queryBySetMacAdd(String set_macadd)  ;
    public HmUserSetRel  queryBySetImei(String set_imei)  ;
    public HmUserSetRel  queryBySetIccid(String set_imei)  ;

}
