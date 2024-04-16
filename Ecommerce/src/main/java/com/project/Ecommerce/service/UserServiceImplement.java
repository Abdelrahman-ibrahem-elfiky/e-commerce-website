package com.project.Ecommerce.service;

import com.project.Ecommerce.config.JwtProvider;
import com.project.Ecommerce.exception.UserException;
import com.project.Ecommerce.model.User;
import com.project.Ecommerce.repository.UserRepository;
import com.project.Ecommerce.service.basicService.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplement implements UserService {

    private UserRepository userRepository;
    private JwtProvider jwtProvider;

    public UserServiceImplement(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public User findUserById(Long id) throws UserException {

        Optional<User>userOptional=userRepository.findById(id);
        if (userOptional.isPresent())
        {
            return userOptional.get();
        }
        throw new UserException("User Not Found With Id.."+id);
    }


    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {

        String email=jwtProvider.getEmailFromToken(jwt);

        User user=userRepository.findByEmail(email);
        if(user==null)
        {
            throw new UserException("User Not Found With Email.."+email);

        }
        return user;
    }
}
