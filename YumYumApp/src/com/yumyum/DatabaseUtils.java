package com.yumyum;

import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * this class interacts with the database in order to add/delete/update/get information.
 * 
 * @param mySession       HttpSession object that is created for each user in order to keep important information regarding the specific user.  
 * @param recipeHandler   ICouponsDAO instance that holds a specific implementation related to recipes in the database.
 * @param userHandler     ICouponsDAO instance that holds a specific implementation related to users in the database.
 * @param logger          static variable, will be used in this class "DatabaseUtils" to generate log messages.
 */
public class DatabaseUtils 
{
	 private HttpSession mySession=null;
	 private ICouponsDAO recipeHandler=null;
	 private ICouponsDAO userHandler=null;
	 private static Logger logger=null;

	/**
	 *  The DatabaseUtils primary constructor
	 */
	public DatabaseUtils()
	{
	  recipeHandler=RecipeHandler.createHibernateDAOManager();
	  userHandler=UserHandler.createHibernateDAOManager();
	}
	 
	 
	/**
	 * DatabaseUtils constructor.
	 * 
	 * @param logger    static variable, will be used in this class "DatabaseUtils" to generate log messages.
	 */
	public DatabaseUtils(Logger logger)
	{
		this();
		this.logger=logger;
	}
	
	
	/**
	 * Adds a new Recipe linked to the current user to the Database.
	 * 
	 *@param request              an HttpServletRequest object that contains the request the client has made of the servlet.
     *@param response             an HttpServletResponse object that contains the response the servlet sends to the client.
	 */
	public void addRecipe(HttpServletRequest request,HttpServletResponse response) 
	{
		logger.info("addRecipe-DatabaseUtils");
		try 
		{			
			Recipe recipe=new Recipe();
			recipe.setName(request.getHeader("clientName"));
			recipe.setTitle(request.getHeader("RecipeTitle"));
			recipe.setMaterials(request.getHeader("RecipeMaterials"));
			recipe.setDescription(request.getHeader("RecipeDescription"));
			recipe.setCategory(Category.valueOf(request.getHeader("RecipeCategory")));
	    	User user=(User)userHandler.getCoupon((int)mySession.getAttribute("activeUser"));
	    	recipe.setUser(user);
	    	recipeHandler.addCoupon(recipe);	
    		logger.warning("Recipe was adeed succesfuly at 'addRecipe' Method in DatabaseUtils");
    		
		}
		catch (CouponPlatformException e)
		{
    		logger.warning("CouponPlatformException at getInventory Method in DatabaseUtils: Exception thrown because of a problem to 'getCoupon' by a specific coupon ID that is currently in our session OR because of a problem to 'addCoupon'");
    		
    		//System.out.println("problem to 'getCoupon' by a specific coupon ID that is currently in our session OR because of a problem to 'addCoupon'");
    		
    		request.setAttribute("fault","exception");
		
		} 
		/*catch (IOException e) 
		{
    		logger.warning("IOException at getInventory Method in DatabaseUtils: Problem to getWriter from the HttpServletResponse object");
			e.printStackTrace();
		}*/
	}
	
	
	/**
	 * Adds a new User to the Database.
	 * 
	 *@param request              an HttpServletRequest object that contains the request the client has made of the servlet.
     *@param response             an HttpServletResponse object that contains the response the servlet sends to the client.
	 */
	public void signUp(HttpServletRequest request,HttpServletResponse response)
	{	
		logger.info("signUp-DatabaseUtils");
		try 
		{
			User user=new User(request.getHeader("userName"),request.getHeader("password"));
			userHandler.addCoupon(user);			
		}
		
		catch(CouponPlatformException e)
		{
    		logger.warning("CouponPlatformException at signUp Method in DatabaseUtils: Problem to 'addCoupon' by the userHandler instance");
			try 
			{
				response.getWriter().println("we are currently experiencing difficulties to add new users, please try again later. thanks!");
			} 
			catch (IOException e1) 
			{
	    		logger.warning("IOException at signUp Method in DatabaseUtils inside CouponPlatformException catch clause: problem to 'getWriter'");
				e1.printStackTrace();
			}
		}
		/*catch (IOException e) 
		{
    		logger.warning("IOException at signUp Method in DatabaseUtils: problem to 'getWriter'");
			e.printStackTrace();
		}*/
	}
	
	
	/**
	 * Checks if the username and/or password match the information saved in the database.
	 * 
	 *@param request              an HttpServletRequest object that contains the request the client has made of the servlet.
     *@param response             an HttpServletResponse object that contains the response the servlet sends to the client.
	 */
	public void logIn(HttpServletRequest request,HttpServletResponse response)
	{
		logger.info("logIn-DatabaseUtils");
		String userName=request.getHeader("userName");
		String password=request.getHeader("password");
		try 
		{
			int userID=findUserID(userName,password);
			if (userID!=-1)
			{
				mySession=request.getSession();
				mySession.setAttribute("activeUser", userID);
	    		logger.warning("User has succesfuly logged in");
	    		request.setAttribute("isLoggedIn", "true");
			}
			else
			{
				logger.warning("fail attempt to login by user ID which isn't in our Database");
				request.setAttribute("isLoggedIn", "false");
			}
		} 
		/*catch (IOException e) 
		{
    		logger.warning("IOException at logIn Method in DatabaseUtils: problem to 'getWriter'");
			e.printStackTrace();
		} */
		catch (CouponPlatformException e) 
		{
    		logger.warning("CouponPlatformException at logIn Method in DatabaseUtils: Problem to get an iterator by the method 'getCoupons' in DAO which is accessed by the method 'findUserID'");
			e.printStackTrace();
		}
	}

	
	/**
	 * Gets user's recipes.
	 * 
	 *@param request              an HttpServletRequest object that contains the request the client has made of the servlet.
     *@param response             an HttpServletResponse object that contains the response the servlet sends to the client.
	 */
	public void personalRecipes(HttpServletRequest request,HttpServletResponse response)
	{
		logger.info("personalRecipes-DatabaseUtils");
    	try 
    	{
    		int userID=(int)mySession.getAttribute("activeUser");
    		Iterator<Recipe> recipes=(Iterator<Recipe>)recipeHandler.getCoupons();
    		request.setAttribute("data",recipes);
    		response.addHeader("userIDD", String.valueOf(userID));
		}
    	catch (CouponPlatformException e) 
    	{
    		
    		logger.warning("CouponPlatformException at personalRecipes Method in DatabaseUtils: Problem to get an iterator by the method 'getCoupons' in DAO");
			e.printStackTrace();
    	} 
    	/*catch (ServletException e) 
    	{
    		logger.warning("Problem to call froward method on the RequestDispatcher object");
			e.printStackTrace();
		} 
    	catch (IOException e) 
		{
    		logger.warning("IOException at personalRecipes Method in DatabaseUtils: problem to 'getWriter'");
			e.printStackTrace();
		}*/

	}
	
	/**
	 * Updates a recipe details for the current user.
	 * 
	 *@param request              an HttpServletRequest object that contains the request the client has made of the servlet.
     *@param response             an HttpServletResponse object that contains the response the servlet sends to the client.
	 */
	public void updateRecipe(HttpServletRequest request,HttpServletResponse response)
	{
		logger.info("updateRecipe-DatabaseUtils");
		try
		{
			Recipe recipe=new Recipe();
			recipe.setId(Integer.parseInt(request.getHeader("RecipeId")));
			recipe.setName(request.getHeader("clientName"));
			recipe.setTitle(request.getHeader("RecipeTitle"));
			recipe.setMaterials(request.getHeader("RecipeMaterials"));
			recipe.setDescription(request.getHeader("RecipeDescription"));
			recipe.setCategory(Category.valueOf(request.getHeader("RecipeCategory")));
	    	User user=(User)userHandler.getCoupon((int)mySession.getAttribute("activeUser"));   	
	    	recipe.setUser(user);
	    	recipeHandler.updateCoupon(recipe);
		}
		catch(CouponPlatformException e)
		{
    		logger.warning("CouponPlatformException at updateRecipe Method in DatabaseUtils: Exception thrown because of a problem to 'getCoupon' by a specific coupon ID that is currently in our session OR because of a problem to 'updateCoupon'");
			e.printStackTrace();
		} 
		/*catch (IOException e) 
		{
    		logger.warning("IOException at updateRecipe Method in DatabaseUtils: problem to 'getWriter'");
			e.printStackTrace();
		}*/
	}
	
	
	/**
	 * Deletes a recipe from the database.
	 * 
	 *@param request              an HttpServletRequest object that contains the request the client has made of the servlet.
     *@param response             an HttpServletResponse object that contains the response the servlet sends to the client.
	 */
	public void deleteRecipe(HttpServletRequest request,HttpServletResponse response)
	{
		logger.info("deleteRecipe-DatabaseUtils");
		try 
		{
			int recipeId=Integer.parseInt(request.getHeader("RecipeId"));
			recipeHandler.deleteCoupon(recipeId);
			//response.getWriter().print("Recipe deleted");
		} 
		catch (CouponPlatformException e)
		{
    		logger.warning("CouponPlatformException at deleteRecipe Method in DatabaseUtils: Exception thrown because of a problem to 'deleteCoupon' with a specific coupon ID");
			e.printStackTrace();
		}
		/*catch (IOException e)
		{
    		logger.warning("IOException at deleteRecipe Method in DatabaseUtils: problem to 'getWriter'");
			e.printStackTrace();
		}*/
	}
	
	
	/**
	 * Gets all the recipes details filtered by a specific category.
	 * 
	 *@param request              an HttpServletRequest object that contains the request the client has made of the servlet.
     *@param response             an HttpServletResponse object that contains the response the servlet sends to the client.
	 */
	public void getCategoryInventory(HttpServletRequest request,HttpServletResponse response)
	{
		logger.info("getCategoryInventory-DatabaseUtils");

		try 
		{
			Iterator<Recipe> recipes=(Iterator<Recipe>)recipeHandler.getCoupons();
			request.setAttribute("data", recipes);
		}

    	catch (CouponPlatformException e) 
    	{
    		logger.warning("CouponPlatformException at getCategoryInventory Method in DatabaseUtils: Problem to get an iterator by the method 'getCoupons' in DAO");
			e.printStackTrace();
		}
		
		/*catch (JSONException e) 
    	{
    		logger.warning("JSONException at getCategoryInventory Method in DatabaseUtils");
			e.printStackTrace();
		}
    	catch (IOException e) 
 		{
    		logger.warning("IOException at getCategoryInventory Method in DatabaseUtils: problem to 'getWriter'");
			e.printStackTrace();
		} */
	}
	
	
	/**
	 * Removes the current user from the session object.
	 * 
	 *@param request              an HttpServletRequest object that contains the request the client has made of the servlet.
     *@param response             an HttpServletResponse object that contains the response the servlet sends to the client.
	 */
	public void logOut(HttpServletRequest request,HttpServletResponse response)
	{
		logger.info("logOut-DatabaseUtils");
		mySession.removeAttribute("activeUser");
		/*try 
		{
			response.getWriter().print("Thank you! see you next time");
		} 
		catch (IOException e) 
		{	
    		logger.warning("IOException at logOut Method in DatabaseUtils: problem to 'getWriter'");
			e.printStackTrace();
		}*/
	}
	
	
    /**
     * Checks whether a specific user is exist in the database.
     * @param username                       the username to be checked. 
     * @param password                       the password to be checked. 
     * @throws CouponPlatformException       throws instance of CouponPlatformException class to indicate illegal use of the CouponPlatformException implementation.
     * @return                               -1 if the user does not exist, else returns the user's unique id.
     */
	private int findUserID(String username,String password) throws CouponPlatformException
	 {
		 logger.info("findUserID-DatabaseUtils");
		 Iterator<User> users=null;
		 users =(Iterator<User>)userHandler.getCoupons();
	     int id=-1;
	     User currUser;
	     while(users.hasNext())
	     {
	    	 currUser=users.next();
	    	 if (currUser.getUserName().equals(username))
	    	 {
	    	    if (currUser.getPassword().equals(password))
	    	    {
	    	    	id=currUser.getUserId();
	    	    	break;
	    	    }
	    	  }
	     } 		 
	    	
	    	return id;	
	 }
}
