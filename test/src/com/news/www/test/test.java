package com.news.www.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;

public class test extends TestCase {

	public void testBaseDao(){
//		em.clear();
	}
	public void test() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("qs");
		EntityManager em = factory.createEntityManager();
//		en.getTransaction().begin();
//		en.persist(new hello("yao"));
	}
	
}
