package com.yumyum;

import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


/**
 * this class interacts with the database in order to add/delete/update/get recipes information.
 * 
 * @param recipeInstance       RecipeHandler object that is created only once (thanks to the single tone design pattern implemented in the class).  
 * @param firstCreation        boolean flag the indicates whether it is the first attempt to create a RecipeHandler object or not.
 * @param factory              SessionFactory instance that manages the session object in order to connect the database.
 * @param session              Session object that interacts with the database; begins the transactions, save the needed information on the database and so on.
 */
public class RecipeHandler implements ICouponsDAO
{
	  private static RecipeHandler recipeInstance = null;
	  private static boolean firstCreation = true;
	  private SessionFactory factory = null;
	  private Session session = null;
	  
	  /**
	   * The RecipeHandler constructor.
	   */
	  private RecipeHandler(){}
	  
	  
	  /**
	   * Checks whether it is the first attempt to create a RecipeHandler object or not.
	   * 
	   * @return    a RecipeHandler instance. In case that it is not first attempt the returned value will be null. 
	   */
	  public static RecipeHandler createHibernateDAOManager()
	    {
		if (firstCreation)
		{
		    firstCreation = false;
		    recipeInstance = new RecipeHandler();
		}
		return recipeInstance;
	    }

    /**
     * Adds a new Recipe linked to the current user to the Database.
     * 
     * @param coupon     in this class the object that is sent to the method should be a Recipe object.
     * @return            adding result. 
     */
	@Override
	public boolean addCoupon(Object coupon) throws CouponPlatformException 
	{
		Recipe couponRecipe=(Recipe)coupon;
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
     * Gets a specific Recipe identified by its unique ID.
     * 
     * @param id          unique identifier used to distinguish between the different Recipes.
     * @return            Recipe instance that holds the given unique ID. The returned value need to be casted in order to be a Recipe instance again. 
     */
	@Override
	public Object getCoupon(int id) throws CouponPlatformException 
	{
		Recipe recipe = null;
		try
		{
		    factory = new AnnotationConfiguration().configure()
			    .buildSessionFactory();
		    session = factory.openSession();
		    session.beginTransaction();
		    recipe = (Recipe) session.get(Recipe.class, id);
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

		return recipe;
		
	}

	
	
	/**
	 * Gets all the Recipes objects found in the database.
	 * 
	 * @return    List iterator of all the Recipes.
	 */
	@Override
	public Iterator<? extends Object> getCoupons()
			throws CouponPlatformException 
	{
		List<Recipe> coupons = null;
		try
		{
		    factory = new AnnotationConfiguration().configure()
			    .buildSessionFactory();
		    session = factory.openSession();
		    session.beginTransaction();
		    coupons = session.createQuery("from Recipe").list();
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
	 * Update a specific Recipe.
	 * 
	 * @return   the update result.
	 */
	@Override
	public boolean updateCoupon(Object ob) throws CouponPlatformException 
	{
		Recipe couponRecipe=(Recipe)ob;
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
	 * Deletes a specific Recipe.
	 * 
	 * @param id      unique identifier used to distinguish between the different Recipes.
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
		    session.delete(session.get(Recipe.class, id));
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
