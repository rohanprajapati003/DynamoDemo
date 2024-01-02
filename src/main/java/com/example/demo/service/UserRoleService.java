package com.example.demo.service;

import com.example.demo.model.UserRole;
import com.example.demo.model.dto.UserRoleDTO;

import java.util.List;

public interface UserRoleService {

	UserRoleDTO addUserRole(UserRoleDTO userRoleDTO);

	UserRoleDTO getUserRole(UserRoleDTO userRoleDTO);


	UserRoleDTO updateUserRole(UserRoleDTO userRoleDTO);

	List<UserRole> userRoleList();

}
