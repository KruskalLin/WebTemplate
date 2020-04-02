package stg.template.template.web;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import stg.template.template.dao.UserSpecification;
import stg.template.template.entity.User;
import stg.template.template.payloads.APIResponse;
import stg.template.template.payloads.FilterRequest;
import stg.template.template.payloads.ListData;
import stg.template.template.payloads.UserProfile;
import stg.template.template.service.UserService;

import javax.validation.Valid;
import java.util.stream.Collectors;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2019/7/12
 * @Todo:
 */
@RestController
@RequestMapping("/admin")
@Api(value = "Admin API")
public class AdminController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/getUserList")
    @ResponseBody
    @ApiOperation(value = "Get all users through page filter")
    @ApiImplicitParams({@ApiImplicitParam(name = "filter", value = "filter", paramType = "FilterRequest")})
    public APIResponse getUserList(@Valid @RequestBody FilterRequest filter) {
        Specification<User> specification = new UserSpecification(filter);
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getSize(),
                new Sort("ASC".equals(filter.getSort())?Sort.Direction.ASC:Sort.Direction.DESC, filter.getProperty()));
        Page<User> users = userService.findAll(specification, pageable);
        return APIResponse.ofSuccess(new ListData<>(users.stream().map(UserProfile::new).collect(Collectors.toList()), users.getTotalPages()));
    }

}
