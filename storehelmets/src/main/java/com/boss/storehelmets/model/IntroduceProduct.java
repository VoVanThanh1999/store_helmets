package com.boss.storehelmets.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Id;
@Entity
@Table(name = "introduce_product")
public class IntroduceProduct {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "id_introduce")
	private String idIntroduce;
	
	@Column(name="name_intro")
	private String nameIntro;

	/**
	 * @return the idIntroduce
	 */
	public String getIdIntroduce() {
		return idIntroduce;
	}

	/**
	 * @param idIntroduce the idIntroduce to set
	 */
	public void setIdIntroduce(String idIntroduce) {
		this.idIntroduce = idIntroduce;
	}

	/**
	 * @return the nameIntro
	 */
	public String getNameIntro() {
		return nameIntro;
	}

	/**
	 * @param nameIntro the nameIntro to set
	 */
	public void setNameIntro(String nameIntro) {
		this.nameIntro = nameIntro;
	}
	
	
	
	

}
