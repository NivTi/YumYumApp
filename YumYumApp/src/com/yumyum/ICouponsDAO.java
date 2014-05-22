package com.yumyum;

import java.util.Iterator;

/**
 * The ICouponsDAO interface represents basic actions undertaken by 
 * different coupon handlers. 
 */
public interface ICouponsDAO 
{
	/**
	 * Adds a new coupon to the Database.
	 * 
	 * @param coupon                       the new coupon to be added.
	 * @throws CouponPlatformException     throws instance of CouponPlatformException class to indicate illegal use of the CouponPlatformException implementation.
	 * @return                             adding result.
	 */
	 public boolean addCoupon(Object coupon) throws CouponPlatformException;
	 
	 /**
		 * Gets a specific coupon by its unique ID from the Database.
		 * 
		 * @param id                           coupon unique id.
		 * @throws CouponPlatformException     throws instance of CouponPlatformException class to indicate illegal use of the CouponPlatformException implementation.
		 * @return                             coupon with the specific id the was given.
		 */
	 public Object getCoupon(int id) throws CouponPlatformException;
	 
	 /**
		 * Gets an iterator of all the coupons existing in the Database.
		 * 
		 * @throws CouponPlatformException     throws instance of CouponPlatformException class to indicate illegal use of the CouponPlatformException implementation.
		 * @return                             iterator.
		 */
	 public Iterator<? extends Object> getCoupons() throws CouponPlatformException;
	 
	 /**
		 * Updates the specific given coupon in the Database.
		 * 
		 * @param ob                           coupon to be updated.
		 * @throws CouponPlatformException     throws instance of CouponPlatformException class to indicate illegal use of the CouponPlatformException implementation.
		 * @return                             update result.
		 */
	 public boolean updateCoupon(Object ob) throws CouponPlatformException;
	 
	 /**
		 * Deletes a specific coupon by its unique ID from the Database
		 * 
		 * @param id                           coupon unique id.
		 * @throws CouponPlatformException     throws instance of CouponPlatformException class to indicate illegal use of the CouponPlatformException implementation.
		 * @return                             delete result.
		 */
	 public boolean deleteCoupon(int id) throws CouponPlatformException;
}
