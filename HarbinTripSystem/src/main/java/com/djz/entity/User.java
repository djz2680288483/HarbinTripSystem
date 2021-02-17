package com.djz.entity;


import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author sr
 * @since 2019-01-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("用户实体类")
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户名")
    @TableId("name")
    private String name;

    @ApiModelProperty("密码")
    @TableId("password")
    private String password;

    @ApiModelProperty("QQ")
    @TableId("qq")
    private String qq;

    @ApiModelProperty("电话号")
    @TableId("telephone")
    private String telephone;

    @ApiModelProperty("地址")
    @TableId("address")
    private String address;

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
