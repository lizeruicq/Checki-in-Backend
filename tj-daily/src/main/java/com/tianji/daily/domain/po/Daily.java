package com.tianji.daily.domain.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.tianji.daily.enums.DateType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author rio
 * @since 2024-03-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("daily")
public class Daily implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "登记日期")
    @TableField("date")
    private LocalDate date;

    @ApiModelProperty(value = "考勤类别")
    @TableField("datetype")
    private DateType datetype;

    @ApiModelProperty(value = "上班时间")
    @TableField("ondutytime")
    private String ondutytime;

    @ApiModelProperty(value = "下班时间")
    @TableField("offdutytime")
    private String offdutytime;

    @ApiModelProperty(value = "工号")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "姓名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "需求号1")
    @TableField("rdmno1")
    private String rdmno1;

    @ApiModelProperty(value = "需求号2")
    @TableField("rdmno2")
    private String rdmno2;

    @ApiModelProperty(value = "需求号3")
    @TableField("rdmno3")
    private String rdmno3;

    @ApiModelProperty(value = "工作人天")
    @TableField("worklength")
    private Double worklength;



    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;





}
