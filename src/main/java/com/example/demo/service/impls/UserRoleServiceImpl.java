package com.example.demo.service.impls;

import com.example.demo.model.UserRole;
import com.example.demo.model.dto.UserRoleDTO;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.UserRoleService;
import com.example.demo.utility.converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Component
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private converter converterd;

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserRoleDTO addUserRole(UserRoleDTO userRoleDTO) {
		UserRole userRole = converterd.convertDtoToModel(userRoleDTO, UserRole.class);
		userRole.setCreatedAndModified();
		UserRole add = userRepo.add(userRole);
		return converterd.convertModelToDto(add,UserRoleDTO.class);
	}

	@Override
	public UserRoleDTO getUserRole(UserRoleDTO userRoleDTO) {

		UserRole userById = userRepo.getUserRoleByTenantId(userRoleDTO.getTenantId());

		System.out.println(userById.getRoles());

		UserRole manager = userRepo.getUserRoleByName("Manager");
		System.out.println(manager.getScRoles());

		return converterd.convertModelToDto(userById,UserRoleDTO.class);
	}

	@Override
	public UserRoleDTO updateUserRole(UserRoleDTO userRoleDTO) {
		UserRole userRole = userRepo.getUserRoleByTenantId(userRoleDTO.getTenantId());

		if(Objects.isNull(userRole)){
			throw new RuntimeException("Not Exist");
		}
		return null;
	}

	@Override
	public List<UserRole> userRoleList() {
		return userRepo.fetchUserRoleList();
	}
}
