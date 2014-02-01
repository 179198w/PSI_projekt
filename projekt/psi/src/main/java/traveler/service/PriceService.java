package traveler.service;

import java.util.List;

import traveler.model.Price;

public interface PriceService {

	List<Price> listPrices();

	List<Price> listPricesWithRelatedData();

}
