package org.jeecg.modules.healthmanage.update.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.healthmanage.update.entity.HmVersionInfo;
import org.jeecg.modules.healthmanage.update.service.IHmVersionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

 /**
 * @Description: hm_version_info
 * @Author: jeecg-boot
 * @Date:   2021-08-17
 * @Version: V1.0
 */
@Api(tags="hm_version_info")
@RestController
@RequestMapping("/health/update/hmVersionInfo")
@Slf4j
public class HmVersionInfoController extends JeecgController<HmVersionInfo, IHmVersionInfoService> {
	@Autowired
	private IHmVersionInfoService hmVersionInfoService;
	
	/**
	 * 分页列表查询
	 *
	 * @param hmVersionInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "hm_version_info-分页列表查询")
	@ApiOperation(value="hm_version_info-分页列表查询", notes="hm_version_info-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(HmVersionInfo hmVersionInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<HmVersionInfo> queryWrapper = QueryGenerator.initQueryWrapper(hmVersionInfo, req.getParameterMap());
		Page<HmVersionInfo> page = new Page<HmVersionInfo>(pageNo, pageSize);
		IPage<HmVersionInfo> pageList = hmVersionInfoService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param hmVersionInfo
	 * @return
	 */
	@AutoLog(value = "hm_version_info-添加")
	@ApiOperation(value="hm_version_info-添加", notes="hm_version_info-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody HmVersionInfo hmVersionInfo) {
		hmVersionInfoService.save(hmVersionInfo);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param hmVersionInfo
	 * @return
	 */
	@AutoLog(value = "hm_version_info-编辑")
	@ApiOperation(value="hm_version_info-编辑", notes="hm_version_info-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody HmVersionInfo hmVersionInfo) {
		hmVersionInfoService.updateById(hmVersionInfo);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "hm_version_info-通过id删除")
	@ApiOperation(value="hm_version_info-通过id删除", notes="hm_version_info-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		hmVersionInfoService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "hm_version_info-批量删除")
	@ApiOperation(value="hm_version_info-批量删除", notes="hm_version_info-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.hmVersionInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "hm_version_info-通过id查询")
	@ApiOperation(value="hm_version_info-通过id查询", notes="hm_version_info-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		HmVersionInfo hmVersionInfo = hmVersionInfoService.getById(id);
		if(hmVersionInfo==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(hmVersionInfo);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param hmVersionInfo
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HmVersionInfo hmVersionInfo) {
        return super.exportXls(request, hmVersionInfo, HmVersionInfo.class, "hm_version_info");
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
        return super.importExcel(request, response, HmVersionInfo.class);
    }

}
