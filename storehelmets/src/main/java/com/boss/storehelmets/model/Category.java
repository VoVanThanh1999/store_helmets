package com.boss.storehelmets.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.sun.istack.NotNull;

@Entity
@Table(name = "category")
public class Category {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name="id_category")
	private String idCategory;
	
	@Column(name="name_category")
	private String nameCategory;
		
	@OneToOne()
	@JoinColumn(name = "id_user")
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_category")
	private Set<CategoryDetails> detailsCategories;
	
	public String getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(String idCategory) {
		this.idCategory = idCategory;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNameCategory() {
		return nameCategory;
	}

	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}

	public Set<CategoryDetails> getDetailsCategories() {
		return detailsCategories;
	}

	public void setDetailsCategories(Set<CategoryDetails> detailsCategories) {
		this.detailsCategories = detailsCategories;
	}
	
	
}
