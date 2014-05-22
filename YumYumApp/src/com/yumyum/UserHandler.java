package com.yumyum;

import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


/**
 * this class interacts with the database in order to add/delete/update/get users information.
 * 
 * @param userInstance         UserHandler object that is created only once (thanks to the single tone design pattern implemented in the class).  
 * @param firstCreation        boolean flag the indicates whether it is the first attempt to create a UserHandler object or not.
 * @param factory              SessionFactory instance that manages the session object in order to connect the database.
 * @param session              Session object that interacts with the database; begins the transactions, save the needed information on the database and so on.
 */
public class UserHandler implements ICouponsDAO
{
	  private static UserHandler userInstance    = null;
	  private static boolean firstCreation = true;
	  private SessionFactory factory = null;
	  private Session session = null;
	  
	  
	  /**
	   * The UserHandler constructor.
	   */
	  private UserHandler(){}
	  
	  
	  /**
	   * Checks whether it is the first attempt to create a UserHandler object or not.
	   * 
	   * @return    a UserHandler instance. In case that it is not first attempt the returned value will be null. 
	   */
	  public static UserHandler createHibernateDAOManager()
	    {
		if (firstCreation)
		{
		    firstCreation = false;
		    userInstance = new UserHandler();
		}
		return userInstance;
	    }
	  
	  
	  
	 /**
	  * Adds a new User linked to the current user to the Database.
	  * 
	  * @param coupon     in this class the object that is sent to the method should be a User object.
	  * @return           adding result. 
	  */  
	@Override
	public boolean addCoupon(Object coupon) throws CouponPlatformException 
	{
		User couponRecipe=(User)coupon;
		try
		{
		    factory = new AnnotationConfiguration().configure()
			    .buildSessionFactory();
		    session = factory.openSession();
		    session.beginTransaction();
		    session.save(couponRecipe);
		    session.getTransaction().commit();
		}
		catch (Exception e)
		{
		    session.getTransaction().rollback();
		    throw new CouponPlatformException(e.getMessage());
		}
		finally
		{
		    if (session != null)
			session.close();
		}

		return true;
	}

	
	
	/**
     * Gets a specific User identified by its unique ID.
     * 
     * @param id          unique identifier used to distinguish between the different Users.
     * @return            User instance that holds the given unique ID. The returned value need to be casted in order to be a User instance again. 
     */
	@Override
	public Object getCoupon(int id) throws CouponPlatformException 
	{
		User user = null;
		try
		{
		    factory = new AnnotationConfiguration().configure()
			    .buildSessionFactory();
		    session = factory.openSession();
		    session.beginTransaction();
		    user = (User) session.get(User.class, id);
		    session.getTransaction().commit();
		}
		catch (HibernateException e)
		{
		    session.getTransaction().rollback();
		    throw new CouponPlatformException(e.getMessage());
		}
		finally
		{
		    if (session != null)
			session.close();
		}

		return user;
	}

	
	
	/**
	 * Gets all the Users objects found in the database.
	 * 
	 * @return    List iterator of all the Users.
	 */
	@Override
	public Iterator<? extends Object> getCoupons()
			throws CouponPlatformException 
	{
		List<User> coupons = null;
		try
		{
		    factory = new AnnotationConfiguration().configure()
			    .buildSessionFactory();
		    session = factory.openSession();
		    session.beginTransaction();
		    coupons = session.createQuery("from User").list();
		    session.getTransaction().commit();
		}
		catch (HibernateException e)
		{
		    session.getTransaction().rollback();
		    throw new CouponPlatformException(e.getMessage());
		}
		finally
		{
		    if (session != null)
			session.close();
		}

		return coupons.iterator();
	}

	
	
	/**
	 * Update a specific User.
	 * 
	 * @return   the update result.
	 */
	@Override
	public boolean updateCoupon(Object ob) throws CouponPlatformException 
	{
		User couponRecipe=(User)ob;
		try
		{
		    factory = new AnnotationConfiguration().configure()
			    .buildSessionFactory();
		    session = factory.openSession();
		    session.beginTransaction();
		    session.update(couponRecipe);
		    session.getTransaction().commit();
		}
		catch (HibernateException e)
		{
		    session.getTransaction().rollback();
		    throw new CouponPlatformException(e.getMessage());
		}
		finally
		{
		    if (session != null)
			session.close();
		}

		return true;
	}

	
	/**
	 * Deletes a specific User.
	 * 
	 * @param id      unique identifier used to distinguish between the different Users.
	 */
	@Override
	public boolean deleteCoupon(int id) throws CouponPlatformException 
	{
		try
		{
		    factory = new AnnotationConfiguration().configure()
			    .buildSessionFactory();
		    session = factory.openSession();
		    session.beginTransaction();
		    session.delete(session.get(User.class, id));
		    session.getTransaction().commit();
		}
		catch (Exception e)
		{
		    session.getTransaction().rollback();
		    throw new CouponPlatformException(e.getMessage());
		}
		finally
		{
		    if (session != null)
			session.close();
		}

		return true;
	}
    	
}

