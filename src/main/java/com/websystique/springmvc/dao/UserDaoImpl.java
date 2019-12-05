package com.websystique.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.User;



@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {

	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	public User findById(long id) {
		User user = getByKey(id);
		if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

//	public User findBySSO2(String sso) {
//		logger.info("SSO : {}", sso);
//		Criteria crit = createEntityCriteria();
//		crit.add(Restrictions.eq("ssoId", sso));
//		User user = (User)crit.uniqueResult();
//		if(user!=null){
//			Hibernate.initialize(user.getUserProfiles());
//		}
//		return user;
//	}

	public User findBySSO(String sso) {
		return findByFieldName("ssoId", sso);
	}

//	@SuppressWarnings("unchecked")
//	public List<User> findAllUsers2() {
//		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
//		List<User> users = (List<User>) criteria.list();
//
//		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load.
//		// Uncomment below lines for eagerly fetching of userProfiles if you want.
//		/*
//		for(User user : users){
//			Hibernate.initialize(user.getUserProfiles());
//		}*/
//		return users;
//	}

	public List<User> findAllUsers() {
		return findAll();
	}

//	public void save2(User user) {
//		persist(user);
//	}

	public void save(User user) {
		update(user);
	}


//	public void deleteBySSO2(String sso) {
//		Criteria crit = createEntityCriteria();
//		crit.add(Restrictions.eq("ssoId", sso));
//		User user = (User)crit.uniqueResult();
//		delete(user);
//	}

	public void deleteBySSO(String sso) {
		delete(findBySSO(sso));
	}

}
