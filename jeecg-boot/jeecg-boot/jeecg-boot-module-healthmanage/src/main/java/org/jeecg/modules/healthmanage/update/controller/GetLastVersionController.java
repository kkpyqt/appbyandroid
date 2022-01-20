package org.jeecg.modules.healthmanage.update;
// 这个就是来源版本对象的数据
//import com.ysyl.service.Updatejson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.modules.healthmanage.update.entity.HmVersionInfo;
import org.jeecg.modules.healthmanage.update.service.IHmVersionInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
@Api(tags="返回当前厂家下最高版本信息")
@RestController
@RequestMapping(value = "/getlastversion")
public class GetLastVersionController {
//    下载的版本code

	 @Resource
     private  IHmVersionInfoService iHmVersionInfoService;

	@ApiOperation(value = "检查版本", notes = "返回最新版本信息")
    //如果当前版本

	@RequestMapping(value = "/updatejson/", method = RequestMethod.GET)
	public HmVersionInfo getUpdatejson(  @RequestParam(name = "name") String name) {
        HmVersionInfo updatejson = iHmVersionInfoService.maxHmVersionInfo(name);  //获取指定用户的版本
//         todo:要提前在表中初始化

		return updatejson;
	}

}
