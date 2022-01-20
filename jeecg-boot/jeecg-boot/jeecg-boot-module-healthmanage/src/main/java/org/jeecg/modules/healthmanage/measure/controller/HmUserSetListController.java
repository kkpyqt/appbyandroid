package org.jeecg.modules.healthmanage.measure.controller;

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
import org.jeecg.modules.healthmanage.measure.entity.HmUserSetList;
import org.jeecg.modules.healthmanage.measure.service.IHmUserSetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

 /**
 * @Description: 设备用户清单
 * @Author: jeecg-boot
 * @Date:   2021-08-23
 * @Version: V1.0
 */
@Api(tags="设备用户清单")
@RestController
@RequestMapping("/health/measure/hmUserSetList")
@Slf4j
public class HmUserSetListController extends JeecgController<HmUserSetList, IHmUserSetListService> {
	@Autowired
	private IHmUserSetListService hmUserSetListService;
	
	/**
	 * 分页列表查询
	 *
	 * @param hmUserSetList
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "设备用户清单-分页列表查询")
	@ApiOperation(value="设备用户清单-分页列表查询", notes="设备用户清单-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(HmUserSetList hmUserSetList,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<HmUserSetList> queryWrapper = QueryGenerator.initQueryWrapper(hmUserSetList, req.getParameterMap());
		Page<HmUserSetList> page = new Page<HmUserSetList>(pageNo, pageSize);
		IPage<HmUserSetList> pageList = hmUserSetListService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param hmUserSetList
	 * @return
	 */
	@AutoLog(value = "设备用户清单-添加")
	@ApiOperation(value="设备用户清单-添加", notes="设备用户清单-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody HmUserSetList hmUserSetList) {
		hmUserSetListService.save(hmUserSetList);
		return Result.OK("添加成功！");
	}


	 /**
	  *   添加
	  *
	  * @param hmUserSetList
	  * @return
	  */
	 @AutoLog(value = "设备用户清单-远程添加")
	 @ApiOperation(value="设备用户清单-添加", notes="设备用户清单-添加")
	 @PostMapping(value = "/addbyapp")
	 public Result<?> addbyapp(@RequestBody HmUserSetList hmUserSetList) {
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 //获取当前登陆用户
		 hmUserSetList.setUserId(sysUser.getId());
		 hmUserSetListService.save(hmUserSetList);
		 return Result.OK("添加成功！");
	 }
	/**
	 *  编辑
	 *
	 * @param hmUserSetList
	 * @return
	 */
	@AutoLog(value = "设备用户清单-编辑")
	@ApiOperation(value="设备用户清单-编辑", notes="设备用户清单-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody HmUserSetList hmUserSetList) {
		hmUserSetListService.updateById(hmUserSetList);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "设备用户清单-通过id删除")
	@ApiOperation(value="设备用户清单-通过id删除", notes="设备用户清单-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		hmUserSetListService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "设备用户清单-批量删除")
	@ApiOperation(value="设备用户清单-批量删除", notes="设备用户清单-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.hmUserSetListService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "设备用户清单-通过id查询")
	@ApiOperation(value="设备用户清单-通过id查询", notes="设备用户清单-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		HmUserSetList hmUserSetList = hmUserSetListService.getById(id);
		if(hmUserSetList==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(hmUserSetList);
	}

//	   toDO 根据子用户分组id 查询当前用户下的子用户信息
	@AutoLog(value = "设备用户清单-通过id查询")
	@ApiOperation(value="设备用户清单-通过id查询", notes="设备用户清单-通过id查询")
	@GetMapping(value = "/queryBySetId")
	public Result<?> queryBySetId(@RequestParam(name="setid",required=true) String setid) {
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		HmUserSetList hmUserSetList = hmUserSetListService.queryBySetId(setid,sysUser.getId());
		if(hmUserSetList==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(hmUserSetList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param hmUserSetList
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HmUserSetList hmUserSetList) {
        return super.exportXls(request, hmUserSetList, HmUserSetList.class, "设备用户清单");
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
        return super.importExcel(request, response, HmUserSetList.class);
    }

}
