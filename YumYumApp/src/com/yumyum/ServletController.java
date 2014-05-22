package com.yumyum;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  ServletController defines a mechanism for sending content 
 *  to the client as defined by the Client/Server model. It generates dynamic 
 *  content for the user application.
 *  
 *  @author Yaar Stendel
 *  @author Niv Tikolski
 *  @param serialVersionUID           The serialVersionUID is a universal version identifier for a Serializable class. 
 *                                    Deserialization uses this number to ensure that a loaded class corresponds exactly to a serialized object. 
 *                                    If no match is found, then an InvalidClassException is thrown.
 *  @param utils                      instance of the DatabaseUtils class which is used to perform database related processes.
 *  @param logger                     static variable, will be used in this class "ServletController" to generate log messages.
 */
@WebServlet("/ServletController/*")
public class ServletController extends HttpServlet
{
	  private static final long serialVersionUID = 1L;
	  private DatabaseUtils utils;
	  private static Logger logger=Logger.getLogger(ServletController.class.getName());
	  
	  /**
	    * The ServletController constructor.
	    */
    public ServletController()
    {
		super();
		utils = new DatabaseUtils(logger);
    }	    
    
    /**
     * Interprets the path sent by the user request and directs to the appropriate method needed.
     * After the wanted method is done the suitable JSP document is being dispatched in order to return 
     * to the view the information collected. 
     * 
     *  @param request              an HttpServletRequest object that contains the request the client has made of the servlet.
     *  @param response             an HttpServletResponse object that contains the response the servlet sends to the client.
     *  
     *  @throws  ServletException   if the request for the GET could not be handled.
     *  @throws  IOException        if an input or output error is detected when the servlet handles the GET request.      
     */
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException
    {
    	logger.info("Method 'doGet' in servlet");
    	RequestedOperation requestPath=RequestedOperation.valueOf(request.getPathInfo().substring(1)); 
    	
    	switch(requestPath)
    	{
    	case addRecipe:
    		logger.info("addRecipe case");
    		utils.addRecipe(request,response); 
    		request.getRequestDispatcher("/addRecipe.jsp").forward(request, response);
    		break;
    	
    	case signUp:
    		logger.info("signUp case");
    		utils.signUp(request,response); 
    		request.getRequestDispatcher("/signUp.jsp").forward(request, response);
    		break;
    	
    	case logIn:
    		logger.info("logIn case");
    		utils.logIn(request,response);
    		request.getRequestDispatcher("/logIn.jsp").forward(request, response);
    		break;
    		
    	case updateRecipe:
    		logger.info("updateRecipe case");
    		utils.updateRecipe(request, response);
    		request.getRequestDispatcher("/updateRecipe.jsp").forward(request, response);
    		break;
    		
    	case personalRecipes:
    		logger.info("personalRecipes case");
    		utils.personalRecipes(request, response); 
    		request.getRequestDispatcher("/personal.jsp").forward(request, response);
    		break;
    		
    	case logOut:
    		logger.info("logOut case");
    		utils.logOut(request, response);
    		request.getRequestDispatcher("/logOut.jsp").forward(request, response);
    		break;
    		
    	case deleteRecipe:
    		logger.info("deleteRecipe case");
    		utils.deleteRecipe(request, response);
    		request.getRequestDispatcher("/deleteRecipe.jsp").forward(request, response);
    		break;
    		
    	case getCategoryInventory:
    		logger.info("getCategoryInventory case");
    		utils.getCategoryInventory(request, response);    		
    		request.getRequestDispatcher("/getCategoryInventory.jsp").forward(request, response);
    		break;
    	}  
    }
	
    
    /**
     * Calls the method 'doGet()'.
     * 
     *  @param request              an HttpServletRequest object that contains the request the client has made of the servlet.
     *  @param response             an HttpServletResponse object that contains the response the servlet sends to the client.
     *  
     *  @throws  ServletException   if the request for the GET could not be handled.
     *  @throws  IOException        if an input or output error is detected when the servlet handles the GET request.      
     */
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException
    {
    	logger.info("Method 'doPost' in servlet, moving to Method 'doGet'");
    	doGet(request, response);
    }
}
