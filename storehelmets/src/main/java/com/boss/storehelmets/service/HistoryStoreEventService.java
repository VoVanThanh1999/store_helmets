package com.boss.storehelmets.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.boss.storehelmets.model.HistoryStoreEvent;

@Service
public interface HistoryStoreEventService {
	
	public List<HistoryStoreEvent> findByDateHistory(Date date);
	
}
