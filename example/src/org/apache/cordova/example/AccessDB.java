package org.apache.cordova.example;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
//import java.net.CookieHandler;
//import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

//import org.apache.http.cookie.Cookie;

import android.webkit.JavascriptInterface;

public class AccessDB implements Runnable
{
	private Thread currThread=null;
	private HttpURLConnection con=null;
	private String returnedJsonStr;
    private String url;
    //private Cookie myCookie;
    private String mySession;
    
	
@JavascriptInterface
 public String addRecipe(String name,String title,String materials,String description,String category) throws Exception
 {  
	this.url = "http://yumyum.whelastic.net/ServletController/addRecipe";	
	this.currThread=new Thread(this);			
  	this.currThread.run();	
  	
  	this.con.setRequestProperty("clientName", name);
  	this.con.setRequestProperty("RecipeTitle", title);
  	this.con.setRequestProperty("RecipeMaterials", materials);
  	this.con.setRequestProperty("RecipeDescription", description);
  	this.con.setRequestProperty("RecipeCategory", category);
  	
  	this.con.setRequestProperty("Cookie","JSESSIONID=" + mySession);
  //	this.con.setRequestProperty("myCookie", myCookie);
 //     this.con.addRequestProperty("myCookie", myCookie);
  	return this.getServerOutput();
  	
 }



@JavascriptInterface
public String getInvCategory(String category) throws Exception
{  
	this.url = "http://yumyum.whelastic.net/ServletController/getCategoryInventory";	
 	this.currThread=new Thread(this);			
 	this.currThread.run();	
 	
	this.con.setRequestProperty("category", category);
// 	System.out.println("inventory");

return this.getServerOutput();
}

@JavascriptInterface
public String getInventory() throws Exception
{  
	this.url = "http://yumyum.whelastic.net/ServletController/getInventory";	
 	this.currThread=new Thread(this);			
 	this.currThread.run();		
// 	System.out.println("inventory");

return this.getServerOutput();
}

@JavascriptInterface
public String login(String user, String password) throws Exception
{  
	this.url = "http://yumyum.whelastic.net/ServletController/logIn";	

	this.currThread=new Thread(this);			
 	this.currThread.run();		
 	


 	this.con.setRequestProperty("userName", user);
  	this.con.setRequestProperty("password", password);
  	
  	mySession=this.con.getHeaderField("JSESSIONID");
  	//con.getHeaderField(1);
 	return this.getServerOutput();
}

@JavascriptInterface
public String signUp(String user, String password) throws Exception
{  
	this.url = "http://yumyum.whelastic.net/ServletController/signUp";	
 	this.currThread=new Thread(this);			
 	this.currThread.run();
 	
 	this.con.setRequestProperty("userName", user);
  	this.con.setRequestProperty("password", password);
  	System.out.println(user+password);
  	return this.getServerOutput();
}

public String getServerOutput() throws Exception
{
	
	//getting from server
		InputStream is=con.getInputStream();
		BufferedInputStream buffer=new BufferedInputStream(is);
		StringBuilder sb=new StringBuilder();
		int curr=buffer.read();
		while (curr!=-1)
		{
			sb.append((char)curr);
			curr=buffer.read();
		}
		System.out.println(sb.toString());
		this.returnedJsonStr=sb.toString();
		
		return this.returnedJsonStr.trim();
}

@Override
public void run() 
{		
	URL obj = null;
			try 
			{
				System.out.println(this.url);
				obj = new URL(this.url);
				this.con = (HttpURLConnection) obj.openConnection();
				this.con.setRequestMethod("GET");
				//this.con.connect();
			} 
			catch (MalformedURLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			} 
			catch (ProtocolException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}					
}

@JavascriptInterface
public String updateRecipe(String name,String title,String materials,String description,String category,String id) throws Exception
{  
	this.url = "http://yumyum.whelastic.net/ServletController/updateRecipe";	
	this.currThread=new Thread(this);			
 	this.currThread.run();	
 	
 	this.con.setRequestProperty("clientName", name);
 	this.con.setRequestProperty("RecipeTitle", title);
 	this.con.setRequestProperty("RecipeMaterials", materials);
 	this.con.setRequestProperty("RecipeDescription", description);
 	this.con.setRequestProperty("RecipeCategory", category);
 	this.con.setRequestProperty("RecipeId", id);
 	
 	this.con.setRequestProperty("Cookie","JSESSIONID=" + mySession);

 	return this.getServerOutput();
 	
}

@JavascriptInterface
public String logOut() throws Exception
{  
	this.url = "http://yumyum.whelastic.net/ServletController/logOut";	
 	this.currThread=new Thread(this);			
 	this.currThread.run();		
// 	System.out.println("inventory");
 	this.con.setRequestProperty("Cookie","JSESSIONID=" + mySession);
return this.getServerOutput();
}

@JavascriptInterface
public String deleteRecipe(String cpId) throws Exception
{  
	System.out.println(cpId);
	this.url = "http://yumyum.whelastic.net/ServletController/deleteRecipe";	
 	this.currThread=new Thread(this);			
 	this.currThread.run();		
 		
this.con.setRequestProperty("RecipeId", cpId);
this.con.setRequestProperty("Cookie","JSESSIONID=" + mySession);

return this.getServerOutput();
}

@JavascriptInterface
public String personalInventory() throws Exception
{  
	this.url = "http://yumyum.whelastic.net/ServletController/personalRecipes";	
 	this.currThread=new Thread(this);			
 	this.currThread.run();		
 	
 	this.con.setRequestProperty("Cookie","JSESSIONID=" + mySession);

return this.getServerOutput();
 	
}
}
