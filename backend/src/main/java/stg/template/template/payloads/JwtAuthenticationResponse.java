package stg.template.template.payloads;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/7/9
 * @Todo:
 */
@Data
@ApiModel(value = "JwtAuthenticationResponse", description = "Token response")
public class JwtAuthenticationResponse {
    @ApiModelProperty(value = "token", required = true, dataType = "String")
    private String token;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }
}