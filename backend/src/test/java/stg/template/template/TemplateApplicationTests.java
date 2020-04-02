package stg.template.template;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import stg.template.template.dao.RoleDao;
import stg.template.template.entity.Role;
import stg.template.template.entity.RoleName;
import stg.template.template.entity.User;
import stg.template.template.exception.InternalException;
import stg.template.template.service.UserService;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplateApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private RoleDao roleRepository;

	@Test
	public void contextLoads() {
		Assert.assertEquals(userService.existsByUsername("admin"), true);
		userService.removeAllCache();
		User user = userService.findByUsername("admin");
		Assert.assertEquals(passwordEncoder.matches("123456", user.getPassword()), true);
		userService.removeAllCache();
		userService.delete(user);
		Assert.assertEquals(userService.existsByUsername("admin"), false);
	}

}
