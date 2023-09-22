package com.excellence.user_service.service;

import com.excellence.user_service.dto.ResponseDto;
import com.excellence.user_service.entity.User;

public interface UserService {
	
	User saveUser(User user);

    ResponseDto getUser(Long userId);

}
