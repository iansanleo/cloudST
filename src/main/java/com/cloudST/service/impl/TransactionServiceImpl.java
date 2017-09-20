package com.cloudST.service.impl;

import com.cloudST.model.Transaction;
import com.cloudST.repository.TransacionRespository;
import com.cloudST.service.TransactionService;
import com.cloudST.utiles.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransacionRespository transactionRepository;
	
	@Override
	public Transaction createUpload(Integer idFile, Integer idUser) {
		Transaction transaction = new Transaction(DateUtils.actualDate(),"upload",idFile,idUser);
		return transactionRepository.save(transaction);
	}

	@Override
	public Transaction createDelete(Integer idFile, Integer idUser) {
		Transaction transaction = new Transaction(DateUtils.actualDate(),"delete",idFile,idUser);
		return transactionRepository.save(transaction);
	}
}
