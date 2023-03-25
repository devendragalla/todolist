package com.acc.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.acc.domain.Task;
import com.acc.service.SearchingService;

@Service
public class SearchingServiceImpl implements SearchingService {
	
	KMPAlgorithm kmpAlgorithm= new KMPAlgorithm();
	
	@Override
	public List<Task> search(List<Task> tasks, String searchString){
		return kmpAlgorithm.searchTaskByTitle(tasks,searchString);
		 
	}

}
