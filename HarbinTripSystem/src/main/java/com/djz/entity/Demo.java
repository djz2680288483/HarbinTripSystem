package com.djz.entity;

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
    private Long id;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("年龄")
    private int age;
}
