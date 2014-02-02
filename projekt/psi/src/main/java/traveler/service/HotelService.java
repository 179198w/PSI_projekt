package traveler.service;

import java.util.List;

import traveler.command.HotelCommand;
import traveler.model.Hotel;

public interface HotelService {

	List<Hotel> listHotels();
	
	List<Hotel> listHotels(String condition);

	void addHotel(HotelCommand hotelCommand);
	
	void removeHotel(Long hotelId);

	List<Hotel> listHotels(Long cityId);

	HotelCommand getHotelCommand(Long hotelId);

	void updateHotel(HotelCommand hotelCommand);

	Hotel getHotel(Long hotelId);

}
