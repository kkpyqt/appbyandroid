package org.jeecg.modules.healthmanage.update.entity;

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
 * @Description: hm_version_info
 * @Author: jeecg-boot
 * @Date:   2021-08-17
 * @Version: V1.0
 */
@Data
@TableName("hm_version_info")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="hm_version_info对象", description="hm_version_info")
public class HmVersionInfo implements Serializable {
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
	/**当前版本*/
	@Excel(name = "当前版本", width = 15)
    @ApiModelProperty(value = "当前版本")
    private java.lang.Integer versioncode;
	/**版本名称*/
	@Excel(name = "版本名称", width = 15)
    @ApiModelProperty(value = "版本名称")
    private java.lang.String versionname;
	/**更新内容描述*/
	@Excel(name = "更新内容描述", width = 15)
    @ApiModelProperty(value = "更新内容描述")
    private java.lang.String content;
	/**最小支持版本*/
	@Excel(name = "最小支持版本", width = 15)
    @ApiModelProperty(value = "最小支持版本")
    private java.lang.Integer minsupport;
	/**下载地址*/
	@Excel(name = "下载地址", width = 15)
    @ApiModelProperty(value = "下载地址")
    private java.lang.String url;
}
