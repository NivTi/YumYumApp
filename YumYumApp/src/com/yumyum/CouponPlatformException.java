package com.yumyum;

public class CouponPlatformException extends Exception 
{
    
	public CouponPlatformException() 
	{
		super();
	}

	/**
     * Responsible to send a message description to the user.
     * @param msg   a string that represents the exception that has just occurred.
     */
	public CouponPlatformException(String msg) //Calling the super class-"Exception" with the appropriate message which describes the exception
	{
		super(msg);
	}
}
