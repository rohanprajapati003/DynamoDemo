package com.example.demo.utility;

import com.example.demo.model.UserRole;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("securityService")
public class AuthorizationCheck {


	@Autowired
	private UserRepo userRepo;

	public  Boolean verifyRole(String key,String action){

		System.out.println("In Method VerifyRole");
		UserRole manager = userRepo.getUserRoleByTenantId("481a0cbd-e503-4939-ac49-51311d437e98");

		if(manager!=null){
			UserRole.Access role = manager.getScRoles().get(key);

			if(action.equals("update")){
				if(Boolean.FALSE.equals(role.getUpdate())){
					throw new RuntimeException("Not Authorized");
				}
			}else if(action.equals("view")){
				if(Boolean.FALSE.equals(role.getView())){
					throw new RuntimeException("Not Authorized");
				}
			} else if (action.equals("add")) {
				if(Boolean.FALSE.equals(role.getAdd())){
					throw new RuntimeException("Not Authorized");
				}
			}else if (action.equals("delete")){
				if(Boolean.FALSE.equals(role.getDelete())){
					throw new RuntimeException("Not Authorized");
				}
			}
		}
		return true;
	}



}
