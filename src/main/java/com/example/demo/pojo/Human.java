package com.example.demo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("人类")
public class Human implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @ApiModelProperty("主键")
    private int pk;
    @ApiModelProperty("名字")
    @Length(max = 30,min = 0)
    private String name;
    @ApiModelProperty("密码")
    @Length(max = 45,min = 1)
    private String password;
    @ApiModelProperty("权限")
    private int perms;
}
