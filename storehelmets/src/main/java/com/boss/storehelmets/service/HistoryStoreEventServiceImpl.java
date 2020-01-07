package com.boss.storehelmets.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.boss.storehelmets.model.HistoryStoreEvent;
import com.boss.storehelmets.repository.HistoryStoreEventRepository;

public class HistoryStoreEventServiceImpl implements HistoryStoreEventService{
	@Autowired
	HistoryStoreEventRepository historyStoreEventRepository;
	
	@Override
	public List<HistoryStoreEvent> findByDateHistory(Date date) {
		// TODO Auto-generated method stub
		try {
			List<HistoryStoreEvent> historyStoreEvents = historyStoreEventRepository.findAll();
			return historyStoreEvents;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return null;
	}

}
