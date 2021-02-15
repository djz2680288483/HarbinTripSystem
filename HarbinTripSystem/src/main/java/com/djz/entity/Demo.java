package com.djz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author djz
 * @date 2021/2/5 -16:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel("示例实体类")
public class Demo implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("自增ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("姓名")
    @TableField("name")
    private String name;
    @ApiModelProperty("年龄")
    @TableField("age")
    private int age;
}
