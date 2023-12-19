package com.example.demo.service.impls;

import com.example.demo.model.Bank;
import com.example.demo.model.BankList;
import com.example.demo.model.dto.BankDTO;
import com.example.demo.repo.BankRepo;
import com.example.demo.service.BankService;
import com.example.demo.utility.converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BankServiceImpl implements BankService {

	@Autowired
	BankRepo bankRepo;

	@Autowired
	private com.example.demo.utility.converter converter;

	@Override
	public BankDTO addBank(BankDTO bankDTO) {

		Bank bank = converter.convertDtoToModel(bankDTO, Bank.class);
		bankRepo.saveBank(bank);
		return  bankDTO;
	}

	@Override
	public BankDTO getBank(String tenantId) {
		Bank bank = bankRepo.getBankByTenantId(tenantId);
		if (Objects.isNull(bank)) {
			return null;
		}
		return converter.convertDtoToModel(bank, BankDTO.class);
	}

	@Override
	public BankList addBankList(BankDTO bankDTO) {

		BankList bankList = new BankList();
		bankList.setName(bankDTO.getName());
		bankList.setTenantId(bankDTO.getTenantId());

		BankList bankList1 = bankRepo.saveBankList(bankList);
		List<BankList> bankLists = new ArrayList<>();
		addBank(bankDTO);
		return bankList1;

	}

}
