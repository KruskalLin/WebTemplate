package stg.template.template.payloads;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/7/10
 * @Todo:
 */
@Data
@ApiModel(value = "SignUpRequest", description = "Sign up request")
public class SignUpRequest {

    @NotBlank
    @ApiModelProperty(value = "username", required = true, dataType = "String")
    private String username;

    @NotBlank
    @ApiModelProperty(value = "password", required = true, dataType = "String")
    private String password;
}
