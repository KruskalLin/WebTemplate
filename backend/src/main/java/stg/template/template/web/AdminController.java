package stg.template.template.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import stg.template.template.dao.UserSpecification;
import stg.template.template.entity.User;
import stg.template.template.exception.ResourceNotFoundException;
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
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUserList")
    public ListData<UserProfile> getUserList(@Valid @RequestBody FilterRequest filter) {
        Specification<User> specification = new UserSpecification(filter);
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getSize(),
                new Sort("ASC".equals(filter.getSort())?Sort.Direction.ASC:Sort.Direction.DESC, filter.getProperties()));
        Page<User> users = userService.findAll(specification, pageable);
        return new ListData<>(users.stream().map(UserProfile::new).collect(Collectors.toList()), users.getTotalPages());
    }

}
