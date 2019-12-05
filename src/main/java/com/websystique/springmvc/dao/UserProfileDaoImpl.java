package com.websystique.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.UserProfile;



@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractDao<Long, UserProfile>implements UserProfileDao{

	public UserProfile findById(long id) {
		return getByKey(id);
	}

//	public UserProfile findByType2(String type) {
//		Criteria crit = createEntityCriteria();
//		crit.add(Restrictions.eq("type", type));
//		return (UserProfile) crit.uniqueResult();
//	}

	public UserProfile findByType(String type) {
		return findByFieldName("type", type);
	}

//	@SuppressWarnings("unchecked")
//	public List<UserProfile> findAll2(){
//		Criteria crit = createEntityCriteria();
//		crit.addOrder(Order.asc("type"));
//		return (List<UserProfile>)crit.list();
//	}

//	public List<UserProfile> findAll(){
//		return (List<UserProfile>) getEntityManager().createQuery("from " + getPersistentClass().getName()).getResultList();;
//	}

}
