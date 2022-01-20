package org.jeecg.modules.healthmanage.measure.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.healthmanage.measure.entity.HmMeasureInfo;
import org.jeecg.modules.healthmanage.measure.entity.HmUserSetRel;
import org.jeecg.modules.healthmanage.measure.model.UploadMeasureInfoModel;
import org.jeecg.modules.healthmanage.measure.model.UploadMeasureInfoModel.DataEntity;
import org.jeecg.modules.healthmanage.measure.service.IHmMeasureInfoService;
import org.jeecg.modules.healthmanage.measure.service.IHmUserSetListService;
import org.jeecg.modules.healthmanage.measure.service.IHmUserSetRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: 测量信息
 * @Author: jeecg-boot
 * @Date:   2021-08-17
 * @Version: V1.0
 */
@Api(tags="测量信息")
@RestController
@RequestMapping("/health/measure/hmMeasureInfo")
@Slf4j
public class HmMeasureInfoController extends JeecgController<HmMeasureInfo, IHmMeasureInfoService> {
	@Autowired
	private IHmMeasureInfoService hmMeasureInfoService;

	@Autowired
	private IHmUserSetRelService iHmUserSetRelService;

	@Autowired
	private IHmUserSetListService iHmUserSetListService;
	
	/**
	 * 分页列表查询
	 *
	 * @param hmMeasureInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "测量信息-分页列表查询")
	@ApiOperation(value="测量信息-分页列表查询", notes="测量信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(HmMeasureInfo hmMeasureInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<HmMeasureInfo> queryWrapper = QueryGenerator.initQueryWrapper(hmMeasureInfo, req.getParameterMap());
		Page<HmMeasureInfo> page = new Page<HmMeasureInfo>(pageNo, pageSize);
		IPage<HmMeasureInfo> pageList = hmMeasureInfoService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param hmMeasureInfo
	 * @return
	 */
	@AutoLog(value = "测量信息-添加")
	@ApiOperation(value="测量信息-添加", notes="测量信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody HmMeasureInfo hmMeasureInfo) {
		hmMeasureInfoService.save(hmMeasureInfo);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param hmMeasureInfo
	 * @return
	 */
	@AutoLog(value = "测量信息-编辑")
	@ApiOperation(value="测量信息-编辑", notes="测量信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody HmMeasureInfo hmMeasureInfo) {
		hmMeasureInfoService.updateById(hmMeasureInfo);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "测量信息-通过id删除")
	@ApiOperation(value="测量信息-通过id删除", notes="测量信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		hmMeasureInfoService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "测量信息-批量删除")
	@ApiOperation(value="测量信息-批量删除", notes="测量信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.hmMeasureInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "测量信息-通过id查询")
	@ApiOperation(value="测量信息-通过id查询", notes="测量信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		HmMeasureInfo hmMeasureInfo = hmMeasureInfoService.getById(id);
		if(hmMeasureInfo==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(hmMeasureInfo);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param hmMeasureInfo
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HmMeasureInfo hmMeasureInfo) {
        return super.exportXls(request, hmMeasureInfo, HmMeasureInfo.class, "测量信息");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, HmMeasureInfo.class);
    }

	 /**
	  *   添加
	  *
	  * @return
	  */
	 @AutoLog(value = "测量信息-添加")
	 @ApiOperation(value="测量信息-设备推送添加", notes="测量信息-添加")
	 @PostMapping(value = "/addBySet")
	 public Result<JSONObject> addBySet(@RequestBody UploadMeasureInfoModel uploadMeasureInfoModel) {

		 Result<JSONObject> result = new Result<JSONObject>();
         //	 增加设备是否有macadd imei 在库信息检查
//		 iHmUserSetRelService.queryBySetImei(model.imei)
//		 iHmUserSetRelService.queryBySetImei(model.macadd)
		 /**
		  * data : [{"deviceType":"BPM","unit":"m","adalert":" ","value":"120","createDate":"2018-3-26 22:07:00","measureType":"Svalue"},{"deviceType":"BPM","unit":"m","adalert":"","value":"80","createDate":"2018-3-26 22:07:00","measureType":"Dvalue"},{"deviceType":"BPM","unit":"m","adalert":"0","value":"79","createDate":"2018-3-26 22:07:00","measureType":"Pvalue"}]
		  * Iccid : 86889754971095283811
		  * set_Imei : 075527388751801
		  * set_Macadd :
		  * username : 1
		  */


//		 String set_Imei = jsonObject.get("set_Imei").toString();
//		 //手机号模式 登录模式: "2"  注册模式: "1"
//		 String set_Macadd=jsonObject.get("set_Macadd").toString();
////         测量的list
//		 JSONArray jsonArray = jsonObject.getJSONArray("data");

		 String set_Imei =uploadMeasureInfoModel.getSet_Imei();
		 String set_Macadd =uploadMeasureInfoModel.getSet_Macadd();
		 String user_name =uploadMeasureInfoModel.getUsername();
		 String iccid =uploadMeasureInfoModel.getIccid();
		 String deviceType=uploadMeasureInfoModel.getDeviceType();
		 List<DataEntity> datalist = uploadMeasureInfoModel.getData();




		 log.info(set_Imei+','+set_Macadd);
		 if(oConvertUtils.isEmpty(set_Imei) && oConvertUtils.isEmpty(set_Macadd)&& oConvertUtils.isEmpty(iccid)){

			 result.error500("ICCID 或设备imie或mac地址不能都为空 ！");

			 return result;
		 }
		 HmUserSetRel hmUserSetRel0 = new HmUserSetRel();
		 HmUserSetRel hmUserSetRel1 = new HmUserSetRel();
		 HmUserSetRel hmUserSetRel2 = new HmUserSetRel();
//		 todo 检查设备是否跟用户绑定
		 hmUserSetRel0 = iHmUserSetRelService.queryBySetIccid(iccid);
		 hmUserSetRel1 = iHmUserSetRelService.queryBySetImei(set_Imei);
		 hmUserSetRel2 = iHmUserSetRelService.queryBySetMacAdd(set_Macadd);
//
		 HmUserSetRel  hmUserSetRel =new HmUserSetRel();
//        优先取 IMEI ，MACadd, ICCID，
		 if(!oConvertUtils.isEmpty(hmUserSetRel1)) {
			 hmUserSetRel = hmUserSetRel1;
		 }
		 else if (!oConvertUtils.isEmpty(hmUserSetRel2)) {
				 hmUserSetRel = hmUserSetRel2;

			 }else if(!oConvertUtils.isEmpty(hmUserSetRel0)) {
			 hmUserSetRel = hmUserSetRel0;

		 }else
		 {
			 result.error500("当前设备没注册，请联系厂家客服中心 ！");
		 }
//		 未注册用户但已注册设备场景

		 if (!oConvertUtils.isEmpty(hmUserSetRel.getUserId())) {
//			 HmUserSetRel finalHmUserSetRel = hmUserSetRel;

		 }else {
			 hmUserSetRel.setUserId("SNULL");

		 }


		 HmUserSetRel finalHmUserSetRel = hmUserSetRel;

//        写入测量数据

			 datalist.forEach(data->{

				 //需要增加对上传数据为空的检查
				 HmMeasureInfo hmMeasureInfo =new HmMeasureInfo();
//


                 hmMeasureInfo.setUserId(finalHmUserSetRel.getUserId());

				 hmMeasureInfo.setUserRelId(user_name);

				 hmMeasureInfo.setDeviceType(deviceType);

				 hmMeasureInfo.setSetImei(set_Imei);
//                todo: 这里要做个单位的装换关系
				 hmMeasureInfo.setMUnit(data.getUnit());
				 hmMeasureInfo.setMeasureType(data.getMeasureType());

                 hmMeasureInfo.setAdalert( data.getAdalert());
                 hmMeasureInfo.setMValue( data.getValue());
					 try {
						 Date data1 =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(data.getCreateDate());
						 hmMeasureInfo.setCreateTime(data1);
					 } catch (ParseException e) {
//						 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
						 hmMeasureInfo.setCreateTime(new Date());
//						 e.printStackTrace();
					 }





				 hmMeasureInfoService.save(hmMeasureInfo);
					 result.setCode(200);

			 });


			 return result;
		 }



	@AutoLog(value = "测量信息-通过setid查询当日总测量次数")
	@ApiOperation(value="测量统计信息-通过setid查询当日总测量次数", notes="测量统计信息-通过setid查询当日总测量次数")
	@GetMapping(value = "/queryCurrentAmountBySetId")
	public Result<JSONObject> queryCurrentAmountBySetId(@RequestParam(name="userSetId",required=true) String userSetId,
														@RequestParam(name="device_type",required=false) String device_type,
														@RequestParam(name="startDate",required=false) String startDate,
														@RequestParam(name="endDate",required=false) String endDate			) {
		Result<JSONObject> result = new Result<JSONObject>();
		JSONObject obj = new JSONObject();
	 	LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

	 	Long currentAmount = hmMeasureInfoService.queryCurrentAmount(sysUser.getId(), userSetId,device_type);

        if(oConvertUtils.isEmpty(currentAmount)){
            obj.put("todayAmount", 0);
        }
        else{
            obj.put("todayAmount", currentAmount);
        }

		// 获取7天的开始和结束时间
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date dayStart = calendar.getTime(); //当天
		calendar.add(Calendar.DATE, -7);
		Date dayEnd = calendar.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String datef = sdf.format(date);
//		if(oConvertUtils.isEmpty(startDate) || oConvertUtils.isEmpty(endDate)){
//		    String startDate1 =sdf.format(dayEnd);
//            String endDate1 =sdf.format(dayStart);

			Long allAmount = hmMeasureInfoService.queryAllAmount(sysUser.getId(), userSetId,device_type,startDate,endDate);
			if(oConvertUtils.isEmpty(allAmount)){
                obj.put("allAmount", 0);
            }
			else{
                obj.put("allAmount", allAmount);
            }

//
//		}else {
////            String startDate1 =sdf.format(startDate);
////            String endDate1 =sdf.format(endDate);
//			Long allAmount = hmMeasureInfoService.queryAllAmount(sysUser.getId(), userSetId,device_type,startDate,endDate);
//            if(oConvertUtils.isEmpty(allAmount)){
//                obj.put("allAmount", 0);
//            }
//            else{
//                obj.put("allAmount", allAmount);
//            }
//		}
		result.setResult(obj);
		result.success("计算成功");
		return result;
	}

    @AutoLog(value = "测量信息-通过setid查询总测量次数")
    @ApiOperation(value="测量统计信息-通过setid查询总测量次数", notes="测量统计信息-通过setid查询总测量次数")
    @GetMapping(value = "/queryAllAmountBySetId")
    public Result<JSONObject> queryAllAmountBySetId(@RequestParam(name="userSetId",required=true) String userSetId,
                                                        @RequestParam(name="device_type",required=false) String device_type,
                                                        @RequestParam(name="startDate",required=false) String startDate,
                                                        @RequestParam(name="endDate",required=false) String endDate			) {
        Result<JSONObject> result = new Result<JSONObject>();
        JSONObject obj = new JSONObject();
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();



        // 获取7天的开始和结束时间
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date dayStart = calendar.getTime(); //当天
        calendar.add(Calendar.DATE, -7);
        Date dayEnd = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String datef = sdf.format(date);
//		if(oConvertUtils.isEmpty(startDate) || oConvertUtils.isEmpty(endDate)){
//		    String startDate1 =sdf.format(dayEnd);
//            String endDate1 =sdf.format(dayStart);

        Long allAmount = hmMeasureInfoService.queryAllAmount(sysUser.getId(), userSetId,device_type,startDate,endDate);
        if(oConvertUtils.isEmpty(allAmount)){
            obj.put("allAmount", 0);
        }
        else{
            obj.put("allAmount", allAmount);
        }


        result.setResult(obj);
        result.success("计算成功");
        return result;
    }


	/**
	 * 获取测量明细
	 * @return
	 */
	@AutoLog(value = "测量信息-通过setid获取测量明细")
    @ApiOperation(value="测量明细信息-通过setid查询默认7天内的明细", notes="测量明细信息-通过setid查询默认7天内的明细")
	@GetMapping("/queryDetailBySetId")
	public Result<List<Map<String,Object>>> queryDetailBySetId(@RequestParam(name="userSetId",required=true) String userSetId,@RequestParam(name="device_type",required=true) String device_type,
													  @RequestParam(name="startDate",required=false) String startDate,@RequestParam(name="endDate",required=false) String endDate) {
		Result<List<Map<String,Object>>> result = new Result<List<Map<String,Object>>>();

		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        log.info("获取当前用户"+','+sysUser.getRealname());
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date dayEnd = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, -7);
		Date dayStart = calendar.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        都为空默认取7天
		switch(device_type){
			case "BPM":
				if(oConvertUtils.isEmpty(startDate) || oConvertUtils.isEmpty(endDate)){
					List<Map<String,Object>> list = hmMeasureInfoService.queryBpMeasureDetail(sysUser.getId(), userSetId,device_type,sdf.format(dayEnd),sdf.format(dayStart));

					result.setResult(oConvertUtils.toLowerCasePageList(list));

				}else {

					List<Map<String,Object>> list = hmMeasureInfoService.queryBpMeasureDetail(sysUser.getId(), userSetId,device_type,startDate,endDate);

					result.setResult(oConvertUtils.toLowerCasePageList(list));
				}
				break;
			case "GLU":
				if(oConvertUtils.isEmpty(startDate) || oConvertUtils.isEmpty(endDate)){
					List<Map<String,Object>> list = hmMeasureInfoService.queryGluMeasureDetail(sysUser.getId(), userSetId,device_type,sdf.format(dayEnd),sdf.format(dayStart));

					result.setResult(oConvertUtils.toLowerCasePageList(list));

				}else {

					List<Map<String,Object>> list = hmMeasureInfoService.queryGluMeasureDetail(sysUser.getId(), userSetId,device_type,startDate,endDate);

					result.setResult(oConvertUtils.toLowerCasePageList(list));
				}
				break;
			case "TEMP":
				if(oConvertUtils.isEmpty(startDate) || oConvertUtils.isEmpty(endDate)){
					List<Map<String,Object>> list = hmMeasureInfoService.queryTempMeasureDetail(sysUser.getId(), userSetId,device_type,sdf.format(dayEnd),sdf.format(dayStart));

					result.setResult(oConvertUtils.toLowerCasePageList(list));

				}else {

					List<Map<String,Object>> list = hmMeasureInfoService.queryTempMeasureDetail(sysUser.getId(), userSetId,device_type,startDate,endDate);

					result.setResult(oConvertUtils.toLowerCasePageList(list));
				}
				break;
//				默认血压
			default:
				if(oConvertUtils.isEmpty(startDate) || oConvertUtils.isEmpty(endDate)){
					List<Map<String,Object>> list = hmMeasureInfoService.queryBpMeasureDetail(sysUser.getId(), userSetId,device_type,sdf.format(dayEnd),sdf.format(dayStart));

					result.setResult(oConvertUtils.toLowerCasePageList(list));

				}else {

					List<Map<String,Object>> list = hmMeasureInfoService.queryBpMeasureDetail(sysUser.getId(), userSetId,device_type,startDate,endDate);

					result.setResult(oConvertUtils.toLowerCasePageList(list));
				}
				break;
		}





		return result;
	}


}
