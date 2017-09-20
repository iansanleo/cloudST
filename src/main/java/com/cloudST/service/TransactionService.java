package com.cloudST.service;

import com.cloudST.model.Transaction;

public interface TransactionService {
	
	Transaction createUpload(Integer idFile, Integer idUser);
	
	Transaction createDelete(Integer idFile, Integer idUser);

}
