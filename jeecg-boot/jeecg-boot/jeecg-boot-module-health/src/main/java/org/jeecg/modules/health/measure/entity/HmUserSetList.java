package org.jeecg.modules.health.measure.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 设备用户清单
 * @Author: jeecg-boot
 * @Date:   2021-08-23
 * @Version: V1.0
 */
@Data
@TableName("hm_user_set_list")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="hm_user_set_list对象", description="设备用户清单")
public class HmUserSetList implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**用户ID*/
	@Excel(name = "用户ID", width = 15, dictTable = "sys_user", dicText = "username", dicCode = "id")
	@Dict(dictTable = "sys_user", dicText = "username", dicCode = "id")
    @ApiModelProperty(value = "用户ID")
    private java.lang.String userId;
	/**设备用户名称*/
	@Excel(name = "设备用户名称", width = 15)
    @ApiModelProperty(value = "设备用户名称")
    private java.lang.String setUserName;
	/**身高*/
	@Excel(name = "身高", width = 15)
    @ApiModelProperty(value = "身高")
    private java.lang.String height;
	/**体重*/
	@Excel(name = "体重", width = 15)
    @ApiModelProperty(value = "体重")
    private java.lang.String weight;
	/**健康状况*/
	@Excel(name = "健康状况", width = 15)
    @ApiModelProperty(value = "健康状况")
    private java.lang.String health;
	/**删除标志*/
	@Excel(name = "删除标志", width = 15, dicCode = "delete_flag")
	@Dict(dicCode = "delete_flag")
    @ApiModelProperty(value = "删除标志")
    private java.lang.Integer delFlag;
	/**出生日期*/
	@Excel(name = "出生日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "出生日期")
    private java.util.Date birthday;
	/**性别*/
	@Excel(name = "性别", width = 15, dicCode = "sex")
	@Dict(dicCode = "sex")
    @ApiModelProperty(value = "性别")
    private java.lang.String sex;
}
