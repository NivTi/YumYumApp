package com.yumyum;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 *  This class is an entity for a relational database and can be seen as a table. 
 *  Every row in the table presents a user and its details.
 * 
 *@param userId     unique identifier used to distinguish between the different Users.
 *@param userName   the nickname the user has chosen when he signed up. 
 *@param password   the private password the user has chosen when he signed up.  
 *@param recipes    a list of all the Recipes that the current user has uploaded so far.
 */
@Entity
@Table(name="User")
public class User 
{	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_Id",nullable=false)
	private int userId;
	
	@Column(name="user_name",nullable=false)
	private String userName;
	
	@Column(name="user_password",nullable=false)
	private String password;
	
	@OneToMany(mappedBy="user")
	private  Set<Recipe> recipes = new HashSet<Recipe>(0);
	
    /**
     * User Primary constructor.
     */
	public User(){}
	
	
	/**
	 * User alternative constructor.
	 * 
	 *@param userName   the nickname the user has chosen when he signed up. 
     *@param password   the private password the user has chosen when he signed up.  
	 */
	public User(String userName, String password) 
	{
		setUserName(userName);
		setPassword(password);
	}
	
	
	/**
	 * @return  the list of all the Recipes that the current user has uploaded so far.
	 */
	public Set<Recipe> getList() {
		return recipes;
	}

	/**
	 * 
	 * @param list   all the Recipes that the current user has uploaded so far.
	 */
	public void setList(Set<Recipe> list) {
		this.recipes = list;
	}
	
	/**
	 * 
	 * @return   the unique identifier used to distinguish between the different Users.
	 */
	public int getUserId() 
	{
		return userId;
	}

	/**
	 * 
	 * @param userId     unique identifier used to distinguish between the different Users.
	 */
	public void setUserId(int userId) 
	{
		this.userId = userId;
	}
	
	/**
	 * 
	 * @return    the nickname the user has chosen when he signed up. 
	 */
	public String getUserName() 
	{
		return userName;
	}
	
	/**
	 * 
	 * @param userName     nickname for the user to signed up with.    
	 */
	public void setUserName(String userName) 
	{
		this.userName = userName;
	}
	
	/**
	 * 
	 * @return     the private password the user has chosen when he signed up.  
	 */
	public String getPassword() 
	{
		return password;
	}
	
	/**
	 * 
	 * @param password    the private password for the user to signed up with.  
	 */
	public void setPassword(String password) 
	{
		this.password = password;
	}
}
