package com.project.Ecommerce.service.basicService;

import com.project.Ecommerce.exception.UserException;
import com.project.Ecommerce.model.User;

public interface UserService {

    public User findUserById(Long id) throws UserException;
    public User findUserProfileByJwt(String jwt) throws UserException;

}
