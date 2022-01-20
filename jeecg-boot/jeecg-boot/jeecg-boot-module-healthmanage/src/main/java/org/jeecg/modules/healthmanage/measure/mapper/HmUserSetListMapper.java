package org.jeecg.modules.healthmanage.measure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.healthmanage.measure.entity.HmUserSetList;

/**
 * @Description: 设备用户清单
 * @Author: jeecg-boot
 * @Date:   2021-08-23
 * @Version: V1.0
 */
public interface HmUserSetListMapper extends BaseMapper<HmUserSetList> {

    public HmUserSetList queryBySetMacAdd(String set_macadd)  ;
    public HmUserSetList  queryBySetImei(String set_imei)  ;

    public HmUserSetList  queryBySetId(String set_id,String user_id)  ;

}
