package org.jeecg.modules.healthmanage.measure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

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
    private String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
	/**用户ID*/
	@Excel(name = "用户ID", width = 15, dictTable = "sys_user", dicText = "username", dicCode = "id")
	@Dict(dictTable = "sys_user", dicText = "username", dicCode = "id")
    @ApiModelProperty(value = "用户ID")
    private String userId;

    /**用户组ID*/
    @Excel(name = "用户组", width = 15)
    @ApiModelProperty(value = "用户组")
    private String setId;
	/**设备用户名称*/
	@Excel(name = "设备用户名称", width = 15)
    @ApiModelProperty(value = "设备用户名称")
    private String setUserName;
	/**身高*/
	@Excel(name = "身高", width = 15)
    @ApiModelProperty(value = "身高")
    private String height;
	/**体重*/
	@Excel(name = "体重", width = 15)
    @ApiModelProperty(value = "体重")
    private String weight;
	/**健康状况*/
	@Excel(name = "健康状况", width = 15)
    @ApiModelProperty(value = "健康状况")
    private String health;
	/**删除标志*/
	@Excel(name = "删除标志", width = 15, dicCode = "delete_flag")
	@Dict(dicCode = "delete_flag")
    @ApiModelProperty(value = "删除标志")
    private Integer delFlag;
	/**出生日期*/
	@Excel(name = "出生日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "出生日期")
    private Date birthday;
	/**性别*/
	@Excel(name = "性别", width = 15, dicCode = "sex")
	@Dict(dicCode = "sex")
    @ApiModelProperty(value = "性别")
    private String sex;
}
