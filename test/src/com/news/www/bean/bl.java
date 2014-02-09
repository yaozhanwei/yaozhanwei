package com.news.www.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class bl {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	private String bh;
	//类型编号
	@Column(length=32)
	private String lxhb;
	//病人编号
	@Column(length=32)
	private String brbh;
	//门诊号
	@Column(length=12)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String mzh;
	@Column(length=12)
	//床号
	private String ch;
	//病人姓名
	@Column(length=32)
	private String bhxm;
	//病人性别
	@Column(length=32)
	private String xb;
	//出生日期
	@Column(length=32)
	private Date csrq;
	//年龄
	@Column(length=32)
	private int nl;
	//职业
	@Column(length=32)
	private String zy;
	//民族
	@Column(length=32)
	private String mz;
	//籍贯
	@Column(length=32)
	private String jg;
	//电话
	@Column(length=32)
	private String dh;
	//地址
	@Column(length=32)
	private String dz;
	//检查部位
	@Column(length=32)
	private String jcbw;
	//检查时间
	@Column(length=32)
	private Date jcsj = new Date();
	//检查医生
	@Column(length=32)
	private String jcys;
	//检查原因
	@Column(length=32)
	private String jcyy;
	//检查结果
	@Column(length=32)
	private String jcjg;
	//检查材料
	@Column(length=32)
	private String jccl;
	
	public String getBh() {
		return bh;
	}
	public void setBh(String bh) {
		this.bh = bh;
	}
	public String getLxhb() {
		return lxhb;
	}
	public void setLxhb(String lxhb) {
		this.lxhb = lxhb;
	}
	public String getBrbh() {
		return brbh;
	}
	public void setBrbh(String brbh) {
		this.brbh = brbh;
	}
	public String getMzh() {
		return mzh;
	}
	public void setMzh(String mzh) {
		this.mzh = mzh;
	}
	public String getCh() {
		return ch;
	}
	public void setCh(String ch) {
		this.ch = ch;
	}
	public String getBhxm() {
		return bhxm;
	}
	public void setBhxm(String bhxm) {
		this.bhxm = bhxm;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public Date getCsrq() {
		return csrq;
	}
	public void setCsrq(Date csrq) {
		this.csrq = csrq;
	}
	public int getNl() {
		return nl;
	}
	public void setNl(int nl) {
		this.nl = nl;
	}
	public String getZy() {
		return zy;
	}
	public void setZy(String zy) {
		this.zy = zy;
	}
	public String getMz() {
		return mz;
	}
	public void setMz(String mz) {
		this.mz = mz;
	}
	public String getJg() {
		return jg;
	}
	public void setJg(String jg) {
		this.jg = jg;
	}
	public String getDh() {
		return dh;
	}
	public void setDh(String dh) {
		this.dh = dh;
	}
	public String getDz() {
		return dz;
	}
	public void setDz(String dz) {
		this.dz = dz;
	}
	public String getJcbw() {
		return jcbw;
	}
	public void setJcbw(String jcbw) {
		this.jcbw = jcbw;
	}
	public Date getJcsj() {
		return jcsj;
	}
	public void setJcsj(Date jcsj) {
		this.jcsj = jcsj;
	}
	public String getJcys() {
		return jcys;
	}
	public void setJcys(String jcys) {
		this.jcys = jcys;
	}
	public String getJcyy() {
		return jcyy;
	}
	public void setJcyy(String jcyy) {
		this.jcyy = jcyy;
	}
	public String getJcjg() {
		return jcjg;
	}
	public void setJcjg(String jcjg) {
		this.jcjg = jcjg;
	}
	public String getJccl() {
		return jccl;
	}
	public void setJccl(String jccl) {
		this.jccl = jccl;
	}
	
}
