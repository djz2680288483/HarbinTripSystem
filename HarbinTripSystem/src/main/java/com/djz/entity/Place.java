package com.djz.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author djz
 * @date 2021/2/18 -14:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("place")
public class Place implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("地点名称")
    @TableId("place_name")
    private String placeName;


    @ApiModelProperty("经度")
    @TableId("longitude")
    private BigDecimal longitude;

    @ApiModelProperty("纬度")
    @TableId("latitude")
    private BigDecimal latitude;

    @ApiModelProperty("地点所属省份")
    @TableId("province")
    private String province;

    @ApiModelProperty("地点所属省份邮编")
    @TableId("postcode")
    private String postcode;

    @ApiModelProperty("创建时间")
    @TableId("create_time")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


    @ApiModelProperty("更新时间")
    @TableId("update_time")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty("删除时间")
    @TableId("delete_time")
    private Date deleteTime;

    @ApiModelProperty("是否删除")
    @TableId("deleted")
    @TableLogic
    private Integer deleted;




}
