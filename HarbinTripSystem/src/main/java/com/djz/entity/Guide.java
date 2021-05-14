package com.djz.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author djz
 * @date 2021/5/13 -11:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("导航数据实体")
@TableName("guide")
public class Guide {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户名")
    @TableId("username")
    private String username;
    @ApiModelProperty("导航方式 0驾车 1公交 2徒步")
    @TableId("guide_type")
    private String guideType;

    @ApiModelProperty("起始地址")
    @TableId("start_place")
    private String startPlace;
    @ApiModelProperty("起始经度")
    @TableId("start_longitude")
    private BigDecimal startLongitude;

    @ApiModelProperty("起始纬度")
    @TableId("start_latitude")
    private BigDecimal startLatitude;

    @ApiModelProperty("目的地")
    @TableId("end_place")
    private String endPlace;

    @ApiModelProperty("目的地经度")
    @TableId("end_longitude")
    private BigDecimal endLongitude;

    @ApiModelProperty("目的地纬度")
    @TableId("end_latitude")
    private BigDecimal endLatitude;

    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableId("create_time")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableId("update_time")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty("删除时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableId("delete_time")
    private Date deleteTime;

    @ApiModelProperty("是否删除")
    @TableId("deleted")
    @TableLogic
    private Integer deleted;
}
