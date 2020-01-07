package com.boss.storehelmets.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boss.storehelmets.model.HistoryStoreEvent;

@Repository
public interface HistoryStoreEventRepository extends JpaRepository<HistoryStoreEvent, String>{
	List<HistoryStoreEvent> findByDate(Date date);
}
