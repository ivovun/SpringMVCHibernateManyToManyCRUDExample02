package com.websystique.springmvc.dao;

import java.io.Serializable;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractDao<PK extends Serializable, T> {
	
	private final Class<T> persistentClass;

	@PersistenceContext
	EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public AbstractDao(){
		this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
//	@Autowired
//	private SessionFactory sessionFactory;
//
//	protected Session getSession(){
//		return sessionFactory.getCurrentSession();
//	}

	@SuppressWarnings("unchecked")
//	public T getByKey2(PK key) {
//		return (T) getSession().get(persistentClass, key);
//	}

	public T getByKey(PK key) {
		return entityManager.find(persistentClass, key);
	}

//	public void persist2(T entity) {
//		getSession().persist(entity);
//	}

	public void persist(T entity) {
		entityManager.persist(entity);
	}

//	public void update2(T entity) {
//		getSession().update(entity);
//	}

	public void update(T entity) {
		entityManager.merge(entity);
	}

//	public void delete2(T entity) {
//		getSession().delete(entity);
//	}
	public void delete(T entity) {
		entityManager.remove(entity);
	}

	public List<T> findAll() {
		return entityManager.createQuery("from " + persistentClass.getName()).getResultList();
	}

	public T findByFieldName(String name, String value) {
		return (T) entityManager.createQuery("SELECT e FROM " + persistentClass.getName()
				+" e WHERE e." + name + " = :name").setParameter("name", value).getSingleResult();
	}


//	public T findOne(final long id) {
//		return entityManager.find(persistentClass, id);
//	}


//	protected Criteria createEntityCriteria(){
//		return getSession().createCriteria(persistentClass);
//	}


}
