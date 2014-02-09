package com.news.www.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Clzp {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	private String bh;
	@Column(length=32)
	private String clmc;//材料名称
	@Column(length=32)
	private String clcfdz;//材料存放地址
	@Column(length=32)
	private String blbh;//病历编号
	public String getBh() {
		return bh;
	}
	public void setBh(String bh) {
		this.bh = bh;
	}
	public String getClmc() {
		return clmc;
	}
	public void setClmc(String clmc) {
		this.clmc = clmc;
	}
	public String getClcfdz() {
		return clcfdz;
	}
	public void setClcfdz(String clcfdz) {
		this.clcfdz = clcfdz;
	}
	public String getBlbh() {
		return blbh;
	}
	public void setBlbh(String blbh) {
		this.blbh = blbh;
	}
	
}
