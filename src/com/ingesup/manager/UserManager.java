package com.ingesup.manager;

import javax.persistence.Query;
import org.hibernate.HibernateException;
import com.ingesup.hibernate.HibernateUtil;
import com.ingesup.model.User;

public class UserManager {
	
	public static User get(User user) {
		
		try {
			
			Query query = HibernateUtil.getSession().createQuery("from User where mail=:mail and password=:password");
			query.setParameter("mail", user.getMail().toLowerCase());
			query.setParameter("password", user.getPassword());
			
			User aliveUser = null;
			
			try {
				aliveUser = (User) query.getSingleResult();
			} catch (Exception e){
				// Nothing to display, this exception only occure if no result has been found
				// TODO: use the deprecated uniqueResult() that does not throw exception and just return null if no result
			}

			return aliveUser;
		} catch (HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

}
