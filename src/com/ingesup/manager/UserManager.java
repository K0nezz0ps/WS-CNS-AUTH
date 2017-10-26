package com.ingesup.manager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.ingesup.hibernate.HibernateUtil;
import com.ingesup.model.User;

public class UserManager {
	
	public static User get(User user) {
		
//		Session session = HibernateUtil.getSession();
//		Transaction t = null;
		
		try {
			
//			t = session.beginTransaction();
			
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
//			t.commit();

			return aliveUser;
		} catch (HibernateException e){
			e.printStackTrace();
//			if(t != null)
//				t.rollback();
			return null;
		} finally {
//			session.close();
		}
	}

}
