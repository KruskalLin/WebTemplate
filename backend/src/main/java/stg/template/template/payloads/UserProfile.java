package stg.template.template.payloads;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import stg.template.template.entity.Role;
import stg.template.template.entity.User;

import java.util.Set;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/7/10
 * @Todo:
 */
@Data
@AllArgsConstructor
@ApiModel(value = "UserProfile", description = "UserProfile")
public class UserProfile {
    @ApiModelProperty(value = "user id", required = true, dataType = "Long")
    private Long id;

    @ApiModelProperty(value = "username", required = true, dataType = "String")
    private String username;

    @ApiModelProperty(value = "roles", required = true, dataType = "Set<Role>")
    private Set<Role> roles;

    public UserProfile(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.roles = user.getRoles();
    }

}
