package org.jeecg.modules.contract.entity;

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
 * @Description: 员工表
 * @Author: jeecg-boot
 * @Date:   2021-08-15
 * @Version: V1.0
 */
@Data
@TableName("con_human")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="con_human对象", description="员工表")
public class ConHuman implements Serializable {
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
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
	/**编码*/
	@Excel(name = "编码", width = 15)
    @ApiModelProperty(value = "编码")
    private String humanCode;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
    private String name;
	/**微信id*/
	@Excel(name = "微信id", width = 15)
    @ApiModelProperty(value = "微信id")
    private String weichatOpenid;
	/**性别*/
	@Excel(name = "性别", width = 15, dicCode = "sex")
	@Dict(dicCode = "sex")
    @ApiModelProperty(value = "性别")
    private String sex;
	/**年龄*/
	@Excel(name = "年龄", width = 15)
    @ApiModelProperty(value = "年龄")
    private Integer age;
	/**出生日期*/
	@Excel(name = "出生日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "出生日期")
    private Date birthday;
	/**电话号码*/
	@Excel(name = "电话号码", width = 15)
    @ApiModelProperty(value = "电话号码")
    private String mobilePhone1;
	/**电话号码2*/
	@Excel(name = "电话号码2", width = 15)
    @ApiModelProperty(value = "电话号码2")
    private String mobilePhone2;
	/**座机电话*/
	@Excel(name = "座机电话", width = 15)
    @ApiModelProperty(value = "座机电话")
    private String tel;
	/**身份证号码*/
	@Excel(name = "身份证号码", width = 15)
    @ApiModelProperty(value = "身份证号码")
    private String idNumber;
	/**来源*/
	@Excel(name = "来源", width = 15)
    @ApiModelProperty(value = "来源")
    private Integer soureType;
	/**区域*/
	@Excel(name = "区域", width = 15)
    @ApiModelProperty(value = "区域")
    private String area;
	/**身份证地址*/
	@Excel(name = "身份证地址", width = 15)
    @ApiModelProperty(value = "身份证地址")
    private String address;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String remarks;
	/**入职日期*/
	@Excel(name = "入职日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "入职日期")
    private Date employDate;
	/**离职日期*/
	@Excel(name = "离职日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "离职日期")
    private Date resignDate;
	/**工作类别*/
	@Excel(name = "工作类别", width = 15, dicCode = "job_status")
	@Dict(dicCode = "job_status")
    @ApiModelProperty(value = "工作类别")
    private Integer jobStatus;
	/**常用地址*/
	@Excel(name = "常用地址", width = 15)
    @ApiModelProperty(value = "常用地址")
    private String commonAddr;
	/**转正日期*/
	@Excel(name = "转正日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "转正日期")
    private Date formalDate;
	/**民族*/
	@Excel(name = "民族", width = 15)
    @ApiModelProperty(value = "民族")
    private String nation;
	/**政治面貌*/
	@Excel(name = "政治面貌", width = 15, dicCode = "affiliation")
	@Dict(dicCode = "affiliation")
    @ApiModelProperty(value = "政治面貌")
    private String political;
	/**籍贯*/
	@Excel(name = "籍贯", width = 15)
    @ApiModelProperty(value = "籍贯")
    private String nativePlace;
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
	/**学历*/
	@Excel(name = "学历", width = 15)
    @ApiModelProperty(value = "学历")
    private String education;
	/**毕业学校*/
	@Excel(name = "毕业学校", width = 15)
    @ApiModelProperty(value = "毕业学校")
    private String school;
	/**专业*/
	@Excel(name = "专业", width = 15)
    @ApiModelProperty(value = "专业")
    private String major;
	/**联系地址*/
	@Excel(name = "联系地址", width = 15)
    @ApiModelProperty(value = "联系地址")
    private String contactAddress;
	/**邮编*/
	@Excel(name = "邮编", width = 15)
    @ApiModelProperty(value = "邮编")
    private String zipCode;
	/**Email*/
	@Excel(name = "Email", width = 15)
    @ApiModelProperty(value = "Email")
    private String email;
	/**外语语种*/
	@Excel(name = "外语语种", width = 15)
    @ApiModelProperty(value = "外语语种")
    private String foreignLanguage;
	/**外语水平*/
	@Excel(name = "外语水平", width = 15)
    @ApiModelProperty(value = "外语水平")
    private String foreignLanguageLevel;
	/**计算机水平*/
	@Excel(name = "计算机水平", width = 15)
    @ApiModelProperty(value = "计算机水平")
    private String computerLevel;
	/**毕业时间*/
	@Excel(name = "毕业时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "毕业时间")
    private Date graduationTime;
	/**到职时间*/
	@Excel(name = "到职时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到职时间")
    private Date arrivalTime;
	/**教育经历*/
	@Excel(name = "教育经历", width = 15)
    @ApiModelProperty(value = "教育经历")
    private String educationExperience;
	/**工作经历*/
	@Excel(name = "工作经历", width = 15)
    @ApiModelProperty(value = "工作经历")
    private String workExperience;
	/**删除标识*/
	@Excel(name = "删除标识", width = 15)
    @ApiModelProperty(value = "删除标识")
    private Integer delFlag;
	/**人员状态 */
	@Excel(name = "人员状态 ", width = 15, dicCode = "ployee_status")
	@Dict(dicCode = "ployee_status")
    @ApiModelProperty(value = "人员状态 ")
    private Integer humanState;
	/**岗位*/
	@Excel(name = "岗位", width = 15)
    @ApiModelProperty(value = "岗位")
    private Integer postId;
}
