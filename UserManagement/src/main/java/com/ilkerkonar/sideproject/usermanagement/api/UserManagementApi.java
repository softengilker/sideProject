package com.ilkerkonar.sideproject.usermanagement.api;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ilkerkonar.sideproject.usermanagement.data.UserRepository;
import com.ilkerkonar.sideproject.usermanagement.model.User;

@RestController
public class UserManagementApi implements IUserManagementApi {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping( "/user" )
	@Override
	public List< User > getUserByFirstNameAndLastName( @RequestParam( value = "firstName" ) final String firstName,
			@RequestParam( value = "lastName" ) final String lastName ) {

		if ( !StringUtils.isEmpty( firstName ) && !StringUtils.isEmpty( lastName ) ) {
			return userRepository.findByFirstNameAndLastName( firstName, lastName );
		} else if ( !StringUtils.isEmpty( firstName ) && StringUtils.isEmpty( lastName ) ) {
			return userRepository.findByFirstName( firstName );
		} else if ( StringUtils.isEmpty( firstName ) && !StringUtils.isEmpty( lastName ) ) {
			return userRepository.findByLastName( lastName );
		}

		return userRepository.findAll();
	}
}