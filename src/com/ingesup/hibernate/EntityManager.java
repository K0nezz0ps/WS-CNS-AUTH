package com.ingesup.hibernate;

import java.util.List;
import org.hibernate.HibernateException;

public abstract class EntityManager {
	
	public static <T> T create(T object) {
		
		try{

			HibernateUtilAuth.getSession().save(object);
			HibernateUtilAuth.getSession().refresh(object);

			return null;
			
		} catch (HibernateException e){
			e.printStackTrace();

			return null;
		} finally{
			HibernateUtilAuth.cleanHibernateExchange();
		}
	}
	
	public static <T> void create(List<T> listObject){

		try {

			for(T obj : listObject)
				HibernateUtilAuth.getSession().save(obj);

			return;
			
		} catch(HibernateException e){
			e.printStackTrace();
			return;
		} finally{
			HibernateUtilAuth.cleanHibernateExchange();
		}
		
	}
	
	public static <T> void update(T object){
		
		try {
			HibernateUtilAuth.getSession().update(object);

		} catch (HibernateException e){
			e.printStackTrace();
			return;
		} finally{
			HibernateUtilAuth.cleanHibernateExchange();
		}
	}

	public static <T> void update(List<T> listObject){
		
		try {
			
			for(T obj : listObject)
				HibernateUtilAuth.getSession().update(obj);

		} catch (HibernateException e){
			e.printStackTrace();
			return;
		}  finally{
			HibernateUtilAuth.cleanHibernateExchange();
		}
	}
	
	public static <T> void delete(T object) {

		try {
			HibernateUtilAuth.getSession().delete(object);
		} catch (HibernateException e){
			e.printStackTrace();
			return;
		} finally{
			HibernateUtilAuth.cleanHibernateExchange();
		}
	}

}
