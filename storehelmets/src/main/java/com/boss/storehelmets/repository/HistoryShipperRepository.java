package com.boss.storehelmets.repository;
import java.sql.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.boss.storehelmets.model.HistoryShipper;

@Repository
public interface HistoryShipperRepository extends JpaRepository<HistoryShipper, String>{
	HistoryShipper findByDate(Date date);
}
