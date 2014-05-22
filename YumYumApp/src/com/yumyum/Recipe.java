package com.yumyum;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



/**
 * This class is an entity for a relational database and can be seen as a table. 
 * Every row in the table presents a recipe and its details.
 * 
 * @param id               unique identifier used to distinguish between the different Recipes.
 * @param name             the name of the Recipe chef/owner.
 * @param title            Recipe's title.
 * @param materials        shopping list for making the dish.
 * @param description      step by step guidance.
 * @param category         Recipe's category.
 * @param user             the user that the Recipe was uploaded from his account.
 */
@Table(name = "Recipe")
@Entity
public class Recipe
{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="name")
    private String name;
    
    @Column(name="title")
    private String title;
    
    @Column(name="materials")
    private String materials;
    
    @Column(name="description")
    private String description;
    
    @Column(name="category")
    private Category category;
    
    @ManyToOne
    @JoinColumn(name="user_Id")
    private User user;

    
   
    /**
     * Recipe primary constructor.
     */
	public Recipe()
    {
	super();
    }

	/**
	 * Recipe alternative constructor.
	 * 
	 * @param id               unique identifier used to distinguish between the different Recipes.
	 * @param name             the name of the Recipe chef/owner.
     * @param title            Recipe's title.
     * @param materials        shopping list for making the dish.
     * @param description      step by step guidance.
     * @param category         Recipe's category.
	 */
	public Recipe(int id, String name, String title, String materials,
			String description, String categoryStr) 
	{
		setId(id);
		setName(name);
		setTitle(title);
		setMaterials(materials);
		setDescription(description);
		setCategory(category);
	}
	
     /**
      * @return  the user that the Recipe was uploaded from his account.
      */
	 public User getUser() {
			return user;
		}

	 /**
	  * @param user  the user that the Recipe was uploaded from his account. 
	  */
	public void setUser(User user) 
	{
		this.user = user;
	}
	
	/**
     * @return  Recipe's category.
     */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category    Recipe's category.
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
     * @return  the unique identifier of the Recipe.
     */
	public int getId() {
		return id;
	}

	/**
	 * @param id   the unique identifier of the Recipe.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
     * @return   the name of the Recipe chef/owner.
     */
	public String getName() {
		return name;
	}

	
	/**
	 * @param name    the name of the Recipe chef/owner.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
     * @return   Recipe's title.
     */
	public String getTitle() {
		return title;
	}

	
	/**
	 * @param title   Recipe's title.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
     * @return   shopping list for making the dish.
     */
	public String getMaterials() {
		return materials;
	}

	
	/**
	 * @param materials    shopping list for making the dish.
	 */
	public void setMaterials(String materials) {
		this.materials = materials;
	}
	
	/**
     * @return   step by step guidance.
     */
	public String getDescription() {
		return description;
	}

	
	/**
	 * @param description   step by step guidance.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}