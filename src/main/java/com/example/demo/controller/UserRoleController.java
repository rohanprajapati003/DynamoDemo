package com.example.demo.controller;

import com.example.demo.model.dto.BankDTO;
import com.example.demo.model.dto.UserRoleDTO;
import com.example.demo.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userRole")
public class UserRoleController {


	@Autowired
	private UserRoleService userRoleService;


	@PostMapping("/add")
 	public ResponseEntity<?> createUserRole(@RequestBody UserRoleDTO userRoleDTO){

		return ResponseEntity.status(HttpStatus.OK).body(userRoleService.addUserRole(userRoleDTO));
	}

	@PostMapping("/get")
	public ResponseEntity<?> getUserRole(@RequestBody UserRoleDTO userRoleDTO){

		return ResponseEntity.status(HttpStatus.OK).body(userRoleService.getUserRole(userRoleDTO));
	}

	@GetMapping()
	public ResponseEntity<?> getUserRole(){

		return ResponseEntity.status(HttpStatus.OK).body(userRoleService.userRoleList());

	}

}
