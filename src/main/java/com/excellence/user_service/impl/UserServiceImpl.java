package com.excellence.user_service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.excellence.user_service.dto.DepartmentDto;
import com.excellence.user_service.dto.ResponseDto;
import com.excellence.user_service.dto.UserDto;
import com.excellence.user_service.entity.User;
import com.excellence.user_service.repository.UserRepository;
import com.excellence.user_service.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private WebClient webClient;

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public ResponseDto getUser(Long userId) {

		ResponseDto responseDto = new ResponseDto();
		User user = userRepository.findById(userId).get();
		UserDto userDto = mapToUser(user);

		DepartmentDto departmentDto = webClient.get()
				.uri("http://localhost:8182/api/departments/" + user.getDepartmentId()).retrieve()
				.bodyToMono(DepartmentDto.class).block();
		responseDto.setUser(userDto);
		responseDto.setDepartment(departmentDto);

		return responseDto;
	}

	private UserDto mapToUser(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setEmail(user.getEmail());
		return userDto;
	}
}
