package com.example.demo.service;

import com.example.demo.model.BankList;
import com.example.demo.model.dto.BankDTO;

public interface BankService {

	BankDTO addBank(BankDTO bankDTO);

	BankDTO getBank(String tenantId);

	BankList addBankList(BankDTO bankDTO);
}
