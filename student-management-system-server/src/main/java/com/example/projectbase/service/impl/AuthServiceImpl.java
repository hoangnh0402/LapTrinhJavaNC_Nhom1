package com.example.projectbase.service.impl;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.constant.SuccessMessage;
import com.example.projectbase.domain.dto.common.DataMailDto;
import com.example.projectbase.domain.dto.request.ChangePasswordRequestDto;
import com.example.projectbase.domain.dto.request.ForgetPasswordRequestDto;
import com.example.projectbase.domain.dto.request.LoginRequestDto;
import com.example.projectbase.domain.dto.request.TokenRefreshRequestDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.LoginResponseDto;
import com.example.projectbase.domain.dto.response.TokenRefreshResponseDto;
import com.example.projectbase.domain.entity.User;
import com.example.projectbase.exception.InvalidException;
import com.example.projectbase.exception.UnauthorizedException;
import com.example.projectbase.repository.UserRepository;
import com.example.projectbase.security.UserPrincipal;
import com.example.projectbase.security.jwt.JwtTokenProvider;
import com.example.projectbase.service.AuthService;
import com.example.projectbase.util.RandomPasswordUtil;
import com.example.projectbase.util.SendMailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  private final SendMailUtil sendMailUtil;

  private final AuthenticationManager authenticationManager;

  private final JwtTokenProvider jwtTokenProvider;

  @Override
  public LoginResponseDto login(LoginRequestDto request) {
    try {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getEmailOrPhone(), request.getPassword()));
      SecurityContextHolder.getContext().setAuthentication(authentication);
      UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
      String accessToken = jwtTokenProvider.generateToken(userPrincipal, Boolean.FALSE);
      String refreshToken = jwtTokenProvider.generateToken(userPrincipal, Boolean.TRUE);
      return new LoginResponseDto(accessToken, refreshToken, userPrincipal.getId(), authentication.getAuthorities());
    } catch (InternalAuthenticationServiceException e) {
      throw new UnauthorizedException(ErrorMessage.Auth.ERR_INCORRECT_USERNAME);
    } catch (BadCredentialsException e) {
      throw new UnauthorizedException(ErrorMessage.Auth.ERR_INCORRECT_PASSWORD);
    }
  }

  @Override
  public TokenRefreshResponseDto refresh(TokenRefreshRequestDto request) {
    return null;
  }

  @Override
  public CommonResponseDto logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
    SecurityContextLogoutHandler logout = new SecurityContextLogoutHandler();
    logout.logout(request, response, authentication);
    return new CommonResponseDto(true, SuccessMessage.LOGOUT);
  }

  @Override
  public CommonResponseDto forgetPassword(ForgetPasswordRequestDto request) {
    Optional<User> user = userRepository.findByUsername(request.getUsername());

    if(user.isEmpty()) {
      throw new UnauthorizedException(ErrorMessage.Auth.ERR_INCORRECT_USERNAME);
    }

    if(!user.get().getEmail().equals(request.getEmail())) {
      throw new UnauthorizedException(ErrorMessage.Auth.ERR_INCORRECT_EMAIL);
    }

    String newPassword = RandomPasswordUtil.random();

    user.get().setPassword(passwordEncoder.encode(newPassword));
    userRepository.save(user.get());

    DataMailDto mailDto = new DataMailDto();
    mailDto.setTo(request.getEmail());
    mailDto.setSubject("Mật khẩu mới của bạn là: " + newPassword);

    Map<String, Object> properties = new HashMap<>();
    properties.put("username", request.getUsername());
    properties.put("newPassword", newPassword);

    mailDto.setProperties(properties);

    try {
      sendMailUtil.sendEmailWithHTML(mailDto, "sendmail.html");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new CommonResponseDto(true, SuccessMessage.SEND_PASSWORD);
  }

  @Override
  public CommonResponseDto changePassword(ChangePasswordRequestDto request, String username) {
    Optional<User> user = userRepository.findByUsername(username);

    if(user.isEmpty()) {
      throw new UnauthorizedException(ErrorMessage.Auth.ERR_INCORRECT_USERNAME);
    }

    boolean isTruePassword = passwordEncoder.matches(request.getOldPassword(), user.get().getPassword());

    if(!isTruePassword) {
      throw new UnauthorizedException(ErrorMessage.Auth.ERR_INCORRECT_PASSWORD);
    }

    if(!request.getPassword().equals(request.getRepeatPassword())) {
      throw new InvalidException(ErrorMessage.Auth.INVALID_REPEAT_PASSWORD);
    }

    user.get().setPassword(passwordEncoder.encode(request.getPassword()));
    userRepository.save(user.get());

    return new CommonResponseDto(true, SuccessMessage.CHANGE_PASSWORD);
  }

}
