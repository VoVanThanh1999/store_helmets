package com.boss.storehelmets.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="code")
public class CodePromotion {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name="id_code")
	private String idCode;
	
	@Column(name="code")
	private String code;
	
	@Column(name="status")
	private boolean status;

	/**
	 * @return the idCode
	 */
	public String getIdCode() {
		return idCode;
	}

	/**
	 * @param idCode the idCode to set
	 */
	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	

}
