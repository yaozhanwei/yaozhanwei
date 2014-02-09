package com.news.www.base;

import java.util.LinkedHashMap;

public interface Dao<T> {

	/**
	 * ��ȡ��¼����
	 * @param entityClass ʵ����
	 * @return
	 */
	public long getCount();
	/**
	 * ���һ����������
	 */
	public void clear();
	/**
	 * ����ʵ��
	 * @param entity ʵ��id
	 */
	public void save(T entity);
	/**
	 * ����ʵ��
	 * @param entity ʵ��id
	 */
	public void update(T entity);
	/**
	 * ɾ��ʵ��
	 * @param entityClass ʵ����
	 * @param entityids ʵ��id����
	 */
	public void delete(long entityId);
	/**
	 * ��ȡʵ��
	 * @param <T>
	 * @param entityClass ʵ����
	 * @param entityId ʵ��id
	 * @return
	 */
	public T find(long entityId);
	/**
	 * ��ȡʵ��
	 * @param <T>
	 * @param entityClass ʵ����
	 * @param bh ʵ��bh
	 * @return
	 */
	public T findByBh(String bh);
	/**
	 * ��ȡ��ҳ���
	 * @param <T>
	 * @param entityClass ʵ����
	 * @param firstindex ��ʼ����
	 * @param maxresult ��Ҫ��ȡ�ļ�¼��
	 * @return
	 */
	public QueryResult<T> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams,LinkedHashMap<String, String> orderby);
	
	public QueryResult<T> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams);
	
	public QueryResult<T> getScrollData(int firstindex, int maxresult, LinkedHashMap<String, String> orderby);
	
	public QueryResult<T> getScrollData(int firstindex, int maxresult);
	
	public QueryResult<T> getScrollData();
	
}
