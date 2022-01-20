package org.jeecg.modules.jeecg.boot.module.healthmanage.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.jeecg.boot.module.healthmanage.entity.HmMeasureInfo;
import org.jeecg.modules.jeecg.boot.module.healthmanage.service.IHmMeasureInfoService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: hm_measure_info
 * @Author: jeecg-boot
 * @Date:   2021-10-06
 * @Version: V1.0
 */
@Api(tags="hm_measure_info")
@RestController
@RequestMapping("/jeecg.boot.module.healthmanage/hmMeasureInfo")
@Slf4j
public class HmMeasureInfoController extends JeecgController<HmMeasureInfo, IHmMeasureInfoService> {
	@Autowired
	private IHmMeasureInfoService hmMeasureInfoService;
	
	/**
	 * 分页列表查询
	 *
	 * @param hmMeasureInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "hm_measure_info-分页列表查询")
	@ApiOperation(value="hm_measure_info-分页列表查询", notes="hm_measure_info-分页列表查询")
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
	@AutoLog(value = "hm_measure_info-添加")
	@ApiOperation(value="hm_measure_info-添加", notes="hm_measure_info-添加")
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
	@AutoLog(value = "hm_measure_info-编辑")
	@ApiOperation(value="hm_measure_info-编辑", notes="hm_measure_info-编辑")
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
	@AutoLog(value = "hm_measure_info-通过id删除")
	@ApiOperation(value="hm_measure_info-通过id删除", notes="hm_measure_info-通过id删除")
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
	@AutoLog(value = "hm_measure_info-批量删除")
	@ApiOperation(value="hm_measure_info-批量删除", notes="hm_measure_info-批量删除")
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
	@AutoLog(value = "hm_measure_info-通过id查询")
	@ApiOperation(value="hm_measure_info-通过id查询", notes="hm_measure_info-通过id查询")
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
        return super.exportXls(request, hmMeasureInfo, HmMeasureInfo.class, "hm_measure_info");
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

}
