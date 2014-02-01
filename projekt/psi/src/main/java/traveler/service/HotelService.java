package traveler.service;

import java.util.List;

import traveler.controller.command.HotelCommand;
import traveler.model.Hotel;

public interface HotelService {

	List<Hotel> listHotels();
	
	List<Hotel> listHotels(String condition);

	void addHotel(HotelCommand hotelCommand);
	
	void removeHotel(Long hotelId);

}
