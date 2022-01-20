package org.jeecg.modules.healthmanage.measure.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.healthmanage.measure.entity.HmUserSetList;

/**
 * @Description: 设备用户清单
 * @Author: jeecg-boot
 * @Date:   2021-08-23
 * @Version: V1.0
 */
public interface IHmUserSetListService extends IService<HmUserSetList> {
    public HmUserSetList queryBySetMacAdd(String set_macadd)  ;
    public HmUserSetList  queryBySetImei(String set_imei)  ;
    public HmUserSetList  queryBySetId(String set_id,String user_id)  ;

}
