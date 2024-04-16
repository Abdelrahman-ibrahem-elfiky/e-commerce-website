package com.project.Ecommerce.service;

import com.project.Ecommerce.config.JwtProvider;
import com.project.Ecommerce.exception.UserException;
import com.project.Ecommerce.model.Cart;
import com.project.Ecommerce.model.User;
import com.project.Ecommerce.repository.UserRepository;
import com.project.Ecommerce.request.LoginRequest;
import com.project.Ecommerce.response.AuthResponse;
import com.project.Ecommerce.service.basicService.CartItemService;
import com.project.Ecommerce.service.basicService.CartSrevice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class CustomerServiceImplement implements UserDetailsService {
    private UserRepository userRepository;
    private JwtProvider jwtProvider;
    private  PasswordEncoder passwordEncoder;

    private CartSrevice cartSrevice;

    public CustomerServiceImplement(UserRepository userRepository, JwtProvider jwtProvider, PasswordEncoder passwordEncoder, CartSrevice cartSrevice) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
        this.cartSrevice = cartSrevice;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=userRepository.findByEmail(username);
        if (user==null)
        {
            throw new UsernameNotFoundException("this user is not found with email - "+username);
        }

        List<GrantedAuthority>authorities=new ArrayList<>();

        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);

    }

    public ResponseEntity<AuthResponse> createdUserHandler(User user) throws UserException
    {
        String email=user.getEmail();
        String password=user.getPassword();
        String firstName=user.getFirstName();
        String lastName=user.getLastName();

        User isUserExist=userRepository.findByEmail(email);
        if (isUserExist!=null)
        {
            throw new UserException(" Email is Already Used With Another Account ");
        }

        User createdUser=new User();
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setLastName(lastName);
        createdUser.setFirstName(firstName);

        User savedUser=userRepository.save(createdUser);
        Cart cart=cartSrevice.createCart(savedUser);
        Authentication authentication=new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=jwtProvider.generateToken(authentication);

        AuthResponse authResponse=new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("signup success");

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
    }

    public ResponseEntity<AuthResponse>loginUser(LoginRequest loginRequest){

        String username=loginRequest.getEmail();
        String password=loginRequest.getPassword();
        Authentication authentication=authenticate(username,password);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=jwtProvider.generateToken(authentication);
        AuthResponse authResponse=new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("signin success");

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
    }

    private Authentication authenticate(String username, String password) {

        UserDetails userDetails=loadUserByUsername(username);
        if (userDetails==null)
        {
            throw new BadCredentialsException("Invalid Username");
        }

        if (!passwordEncoder.matches(password,userDetails.getPassword())){

            throw new BadCredentialsException("Invalid Password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }


}
