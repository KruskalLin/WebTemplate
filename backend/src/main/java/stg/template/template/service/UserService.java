package stg.template.template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import stg.template.template.dao.UserDao;
import stg.template.template.entity.User;
import stg.template.template.exception.ResourceNotFoundException;


/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2019/7/12
 * @Todo:
 */
@Service
public class UserService {
    @Autowired
    private UserDao userRepository;

    public Page<User> findAll(Specification<User> specification, Pageable pageable){
        return userRepository.findAll(specification, pageable);
    }

    @CachePut(value = "cache", key = "#user.id")
    public User save(User user) {
        userRepository.save(user);
        return user;
    }

    // beforeInvocation means executing before invoking this method
    @CacheEvict(value = "cache", key = "#user.id", beforeInvocation = true)
    public User delete(User user) {
        return user;
    }

    @Cacheable(value="cache", key = "#username")
    public User findByUsername(String username){
        User user =  userRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("User", "username", username)
        );
        return user;
    }


    @CacheEvict(value={"cache"}, allEntries = true)
    public void removeAllCache(){
    }


    public Boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }
}
