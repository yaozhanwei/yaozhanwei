package com.news.www.base;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class BaseDao<T> implements Dao<T>{
	
	protected Class<T> entityClass = this.getSuperClass(getClass());
//	protected static EntityManager em = Persistence.createEntityManagerFactory("qs").createEntityManager();
	@PersistenceContext protected EntityManager em;
	protected Log log = LogFactory.getLog(entityClass);
	//	EntityManagerFactory factory = Persistence.createEntityManagerFactory("qs");
//	protected EntityManager em = factory.createEntityManager();
	public BaseDao() {
	}
	protected Class getSuperClass(Class myclass){
		Type type = myclass.getGenericSuperclass();
		Type[] params = null;
		if(type instanceof ParameterizedType){
			params = ((ParameterizedType) type).getActualTypeArguments();
		}
		return (Class)params[0];//��ȡ���ุ�෺�Ͳ���ĵ�һ����������xx hello extends BaseDao<xx>
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public long getCount(){
		return (Long)em.createQuery("select count(o) from "+ getEntityName(this.entityClass)+ " o").getSingleResult();
	}
	public void clear(){
		em.clear();
	}
	public void save(T entity){
		em.persist(entity);
	}
	public void update(T entity){
		em.merge(entity);
	}
	public void delete(long entityId){
		em.remove(em.getReference(entityClass, entityId));
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)//����Ҫִ�У�������transaction����
	public T find(long entityId){
		return em.find(entityClass, entityId);
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)//����Ҫִ�У�������transaction����
	public T findByBh(String bh){
		return em.find(entityClass, bh);
	}
	protected static void setQueryParams(Query query, Object[] queryParams){
		if(queryParams!=null && queryParams.length>0){
			for(int i=0; i<queryParams.length; i++){
				query.setParameter(i+1, queryParams[i]);
			}
		}
	}
	protected static String buildOrderby(LinkedHashMap<String, String> orderby){
		StringBuffer orderbyql = new StringBuffer("");
		if(orderby!=null && orderby.size()>0){
			orderbyql.append(" order by ");
			for(String key : orderby.keySet()){
				orderbyql.append("o.").append(key).append(" ").append(orderby.get(key)).append(",");
			}
			orderbyql.deleteCharAt(orderbyql.length()-1);
		}
		return orderbyql.toString();
	}
	protected static <E> String getCountField(Class<E> clazz){
		String out = "o";
		try {
			PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
			for(PropertyDescriptor propertydesc : propertyDescriptors){
				Method method = propertydesc.getReadMethod();
				if(method!=null && method.isAnnotationPresent(EmbeddedId.class)){					
					PropertyDescriptor[] ps = Introspector.getBeanInfo(propertydesc.getPropertyType()).getPropertyDescriptors();
					out = "o."+ propertydesc.getName()+ "." + (!ps[1].getName().equals("class")? ps[1].getName(): ps[0].getName());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return out;
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public QueryResult<T> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams,LinkedHashMap<String, String> orderby){
		QueryResult qr = new QueryResult<T>();
		String entityname = getEntityName(this.entityClass);
		Query query = em.createQuery("select o from "+ entityname+ " o "+(wherejpql==null || "".equals(wherejpql.trim())? "": "where "+ wherejpql)+ buildOrderby(orderby));
		setQueryParams(query, queryParams);
		if(firstindex!=-1 && maxresult!=-1) query.setFirstResult(firstindex).setMaxResults(maxresult);
		qr.setResultlist(query.getResultList());
		query = em.createQuery("select count("+ getCountField(this.entityClass)+ ") from "+ entityname+ " o "+(wherejpql==null || "".equals(wherejpql.trim())? "": "where "+ wherejpql));
		setQueryParams(query, queryParams);
		qr.setTotalrecord((Long)query.getSingleResult());
		return qr;
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public QueryResult<T> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams){
		return getScrollData(firstindex,maxresult,wherejpql,queryParams,null);
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public QueryResult<T> getScrollData(int firstindex, int maxresult, LinkedHashMap<String, String> orderby){
		return getScrollData(firstindex,maxresult,null,null,orderby);
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public QueryResult<T> getScrollData(int firstindex, int maxresult){
		return getScrollData(firstindex,maxresult,null,null,null);
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public QueryResult<T> getScrollData(){
		return getScrollData(-1,-1);
	}
	
	protected static <E> String getEntityName(Class<E> clazz){
		String entityname = clazz.getSimpleName();
		Entity entity = clazz.getAnnotation(Entity.class);
		if(entity.name()!=null && !"".equals(entity.name())){
			entityname = entity.name();
		}
		return entityname;
	}
}
