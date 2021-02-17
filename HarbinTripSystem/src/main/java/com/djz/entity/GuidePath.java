package com.djz.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author djz
 * @date 2021/2/17 -20:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("导航数据实体")
public class GuidePath implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户名id")
    @TableId("user_id")
    private Long userId;

    @ApiModelProperty("起始地址")
    @TableId("start_place")
    private String startPlace;

    @ApiModelProperty("起始经度")
    @TableId("start_longitude")
    private BigDecimal startLongitude;

    @ApiModelProperty("起始纬度")
    @TableId("start_latitude")
    private BigDecimal startLatitude;

    @ApiModelProperty("导航开始时间")
    @TableId("start_time")
    private Date startTime;


    @ApiModelProperty("目的地")
    @TableId("end_place")
    private String endPlace;

    @ApiModelProperty("目的地经度")
    @TableId("end_longitude")
    private BigDecimal endLongitude;

    @ApiModelProperty("目的地纬度")
    @TableId("end_latitude")
    private BigDecimal endLatitude;

    @ApiModelProperty("导航结束时间")
    @TableId("end_time")
    private Date endTime;

    @ApiModelProperty("导航费用（驾车或公交")
    @TableId("guide_price")
    private BigDecimal guidePrice;

    @ApiModelProperty("导航所用时间（单位分钟）")
    @TableId("guide_time")
    private Integer guideTime;

    @ApiModelProperty("导航方式 0驾车 1公交 2徒步")
    @TableId("guide_type")
    private Integer guideType;

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
