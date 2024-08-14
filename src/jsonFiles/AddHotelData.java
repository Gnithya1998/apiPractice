package jsonFiles;

import pojo.HotelBookingCreation;
import pojo.BookingDates;

public class AddHotelData {
	
	public HotelBookingCreation bookingData(String firstName, String lastName, String checkIn, String checkOut, String addition) {
		
		HotelBookingCreation hotel = new HotelBookingCreation();
		hotel.setFirstname(firstName);
		hotel.setLastname(lastName);
		hotel.setTotalprice(1500);
		hotel.setDepositpaid(true);
		
		BookingDates bookingDates = new BookingDates();
		bookingDates.setCheckin(checkIn);
		bookingDates.setCheckout(checkOut);
		
		hotel.setBookingdates(bookingDates);
		hotel.setAdditionalneeds(addition);
		
		return hotel;	
	}

}
