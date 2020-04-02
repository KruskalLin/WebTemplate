package stg.template.template.web;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import stg.template.template.dao.RoleDao;
import stg.template.template.dao.UserDao;
import stg.template.template.entity.Role;
import stg.template.template.entity.RoleName;
import stg.template.template.entity.User;
import stg.template.template.exception.BadRequestException;
import stg.template.template.exception.InternalException;
import stg.template.template.exception.ResourceNotFoundException;
import stg.template.template.payloads.*;
import stg.template.template.security.JwtTokenProvider;
import stg.template.template.service.UserService;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/7/9
 * @Todo: auth controller
 */

@RestController
@RequestMapping("/")
@Api(value = "Auth API")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleDao roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping(value = "/signin")
    @ResponseBody
    @ApiOperation(value = "Log in")
    @ApiImplicitParams({@ApiImplicitParam(name = "loginRequest", value = "loginRequest", paramType = "LoginRequest")})
    public APIResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        User user = userService.findByUsername(loginRequest.getUsername());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return APIResponse.ofSuccess(new JwtAuthenticationResponse(jwt));
    }


    @PostMapping(value = "/signup")
    @ResponseBody
    @ApiOperation(value = "Log out")
    @ApiImplicitParams({@ApiImplicitParam(name = "signUpRequest", value = "signUpRequest", paramType = "SignUpRequest")})
    public APIResponse registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userService.existsByUsername(signUpRequest.getUsername())) {
            throw new BadRequestException("Username is already taken!");
        }
        User user = new User(signUpRequest.getUsername(), signUpRequest.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new InternalException("User Role not set"));
        user.setRoles(Collections.singleton(userRole));
        User result = userService.save(user);
        return APIResponse.ofMessage("User registered successfully");
    }


    @GetMapping("/user/{username}")
    @ResponseBody
    @ApiOperation(value = "Get user profile by username")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "username", paramType = "String")})
    public APIResponse getUserProfile(@PathVariable(value = "username") String username) {
        User user = userService.findByUsername(username);
        return APIResponse.ofSuccess(new UserProfile(user.getId(), user.getUsername(), user.getRoles()));
    }



    @GetMapping(value = "/updateUser")
    @ResponseBody
    @ApiOperation(value = "Update user's password by username")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "username", paramType = "String"), @ApiImplicitParam(name = "password", value = "password", paramType = "String")})
    public APIResponse updateUserPassword(@RequestParam String username, @RequestParam String password) {
        User user = userService.findByUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userService.save(user);
        return APIResponse.ofMessage("User update successfully");
    }

    @GetMapping(value = "/deleteUser")
    @ResponseBody
    @ApiOperation(value = "Delelte user by username")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "username", paramType = "String")})
    public APIResponse deleteUser(@RequestParam String username) {
        User user = userService.findByUsername(username);
        userService.delete(user);
        return APIResponse.ofMessage("User delete successfully");
    }


}
