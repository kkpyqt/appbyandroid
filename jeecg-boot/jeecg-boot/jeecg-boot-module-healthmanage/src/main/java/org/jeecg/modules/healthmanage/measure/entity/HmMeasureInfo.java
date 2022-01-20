package org.jeecg.modules.healthmanage.measure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @Description: 测量信息
 * @Author: jeecg-boot
 * @Date:   2021-08-17
 * @Version: V1.0
 */
@Data
@TableName("hm_measure_info")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="hm_measure_info对象", description="测量信息")

public class HmMeasureInfo implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss" )
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
	@Excel(name = "用户ID", width = 15)
    @ApiModelProperty(value = "用户ID")
    private java.lang.String userId;
    /**用户ID*/
    @Excel(name = "设备注册用户ID", width = 15)
    @ApiModelProperty(value = "设备注册用户ID")
    private java.lang.String userRelId;
	/**设备类型*/
	@Excel(name = "设备类型", width = 15)
    @ApiModelProperty(value = "设备类型")
    private java.lang.String deviceType;
	/**测量类型*/
	@Excel(name = "测量类型", width = 15)
    @ApiModelProperty(value = "测量类型")
    private java.lang.String measureType;
	/**测量值*/
	@Excel(name = "测量值", width = 15)
    @ApiModelProperty(value = "测量值")
    private java.lang.String mValue;
	/**测量单位*/
	@Excel(name = "测量单位", width = 15)
    @ApiModelProperty(value = "测量单位")
    private java.lang.String mUnit;
	/**设备mac*/
	@Excel(name = "设备mac", width = 15)
    @ApiModelProperty(value = "设备mac")
    private java.lang.String setMacadd;
	/**设备IMEI*/
	@Excel(name = "设备IMEI", width = 15)
    @ApiModelProperty(value = "设备IMEI")
    private java.lang.String setImei;
	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
    @TableLogic
    private java.lang.Integer delFlag;
	/**版本号*/
	@Excel(name = "版本号", width = 15)
    @ApiModelProperty(value = "版本号")
    private java.lang.Integer version;
	/**结果类型*/
	@Excel(name = "结果类型", width = 15)
    @ApiModelProperty(value = "结果类型")
    private java.lang.Integer levelType;
//	adalert
    /**结果类型*/
    @Excel(name = "结果类型", width = 15)
    @ApiModelProperty(value = "警告")
    private java.lang.String adalert;
}
