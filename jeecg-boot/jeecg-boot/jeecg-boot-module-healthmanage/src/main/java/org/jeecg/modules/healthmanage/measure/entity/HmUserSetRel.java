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
 * @Description: 设备用户关系表
 * @Author: jeecg-boot
 * @Date:   2021-08-23
 * @Version: V1.0
 */
@Data
@TableName("hm_user_set_rel")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="hm_user_set_rel对象", description="设备用户关系表")
public class HmUserSetRel implements Serializable {
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
	/**厂家名称*/
	@Excel(name = "厂家名称", width = 15)
    @ApiModelProperty(value = "厂家名称")
    private String bandName;
	/**设备类型*/
	@Excel(name = "设备类型", width = 15)
    @ApiModelProperty(value = "设备类型")
    private String deviceName;
	/**设备mac*/
	@Excel(name = "设备mac", width = 15)
    @ApiModelProperty(value = "设备mac")
    private String setMacadd;
	/**设备IMEI*/
	@Excel(name = "设备IMEI", width = 15)
    @ApiModelProperty(value = "设备IMEI")
    private String setImei;

    /**设备icced*/
    @Excel(name = "设备iccid", width = 15)
    @ApiModelProperty(value = "设备Iccid")
    private String iccId;
	/**删除标志*/
	@Excel(name = "删除标志", width = 15, dicCode = "delete_flag")
	@Dict(dicCode = "delete_flag")
    @ApiModelProperty(value = "删除标志")
    private Integer delFlag;
}
