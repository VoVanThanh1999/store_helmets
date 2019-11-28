package com.boss.storehelmets.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.boss.storehelmets.model.News;

@Repository
public interface NewsRepository extends JpaRepository<News, String>{
	public List<News> findByTitle(String title);
	
	@Query("Select n FROM News as n order by n.newsDetails.dateCreated desc")
	public List<News> findByOrderByDateCreatedDesc();
}
