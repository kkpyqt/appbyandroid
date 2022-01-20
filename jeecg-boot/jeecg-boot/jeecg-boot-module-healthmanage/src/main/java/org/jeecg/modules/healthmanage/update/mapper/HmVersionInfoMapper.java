package org.jeecg.modules.healthmanage.update.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.healthmanage.update.entity.HmVersionInfo;
/**
 * @Description: hm_version_info
 * @Author: jeecg-boot
 * @Date:   2021-08-17
 * @Version: V1.0
 */
public interface HmVersionInfoMapper extends BaseMapper<HmVersionInfo> {

//     @select("select * from hm_version_info order by versionCode desc limit 1")
    public HmVersionInfo maxHmVersionInfo( String versionName);

}
