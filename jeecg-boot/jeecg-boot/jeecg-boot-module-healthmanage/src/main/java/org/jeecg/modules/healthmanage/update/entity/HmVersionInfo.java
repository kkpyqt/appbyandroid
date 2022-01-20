package org.jeecg.modules.healthmanage.update.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
import java.util.Date;

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
	/**当前版本*/
	@Excel(name = "当前版本", width = 15)
    @ApiModelProperty(value = "当前版本")
    private Integer versioncode;
	/**版本名称*/
	@Excel(name = "发行厂家名称", width = 15)
    @ApiModelProperty(value = "发行厂家名称")
    private String versionname;
    /**版本名称*/
    @Excel(name = "app版本名称", width = 15)
    @ApiModelProperty(value = "app版本名称")
    private String appversionname;
	/**更新内容描述*/
	@Excel(name = "更新内容描述", width = 15)
    @ApiModelProperty(value = "更新内容描述")
    private String content;
	/**最小支持版本*/
	@Excel(name = "最小支持版本", width = 15)
    @ApiModelProperty(value = "最小支持版本")
    private Integer minsupport;
	/**下载地址*/
	@Excel(name = "下载地址", width = 15)
    @ApiModelProperty(value = "下载地址")
    private String url;
}
