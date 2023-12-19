package com.example.demo.controller;

import com.example.demo.model.dto.BankDTO;
import com.example.demo.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class BankController {

	@Autowired
	BankService bankService;


	@GetMapping()
	public ResponseEntity<?> getBank(){

		return ResponseEntity.status(HttpStatus.OK).body(bankService.getBank("abc"));

	}

	@PostMapping("/add")
	public ResponseEntity<?> createBank(@RequestBody BankDTO bankDTO){

		return ResponseEntity.status(HttpStatus.OK).body(bankService.addBank(bankDTO));

	}

	@PostMapping("/addBankList")
	public ResponseEntity<?> createBankList(@RequestBody BankDTO bankDTO){

		return ResponseEntity.status(HttpStatus.OK).body(bankService.addBankList(bankDTO));

	}

}
