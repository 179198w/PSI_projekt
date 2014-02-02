package traveler.service;

import java.util.List;

import traveler.command.PriceCommand;
import traveler.command.PriceEditCommand;
import traveler.command.PriceFilterCommand;
import traveler.model.Price;

public interface PriceService {

	List<Price> listPrices();

	List<Price> listPricesWithRelatedData();

	void addPrice(PriceCommand priceCommand);

	PriceEditCommand getPriceEditCommand(Long priceId);

	void updatePrice(PriceEditCommand priceEditCommand);

	List<Price> listPricesWithRelatedData(PriceFilterCommand priceFilterCommand);

}
