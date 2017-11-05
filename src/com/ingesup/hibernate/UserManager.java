package com.ingesup.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import com.ingesup.model.User;

public class UserManager {
	
	/**
	 * Return the id of a user with a given User mail, as a User
	 * @return
	 */
	@SuppressWarnings({"deprecation","rawtypes"})
	public static User getUser(String mail){
		
		try {

			Query query = HibernateUtilAuth.getSession().createQuery("from User where mail=:uMail");
			query.setParameter("uMail", mail);

			User idUser = (User) query.uniqueResult();

			return idUser;
			
		} catch (HibernateException e){
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings({"rawtypes"})
	public static User get(User user) {

		try {

			Query query = HibernateUtilAuth.getSession().createQuery("from User where mail=:mail and password=:password");
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
