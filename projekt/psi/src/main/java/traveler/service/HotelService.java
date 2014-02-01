package traveler.service;

import java.util.List;

import traveler.controller.command.HotelCommand;
import traveler.model.Hotel;

public interface HotelService {

	List<Hotel> listHotels();

	void addHotel(HotelCommand hotelCommand);

}
