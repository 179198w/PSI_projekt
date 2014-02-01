package traveler.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import traveler.model.Price;
import traveler.repository.PriceRepository;
import traveler.service.PriceService;

@Service
public class PriceServiceImpl implements PriceService {

	@Inject
	private PriceRepository priceRepository;
	
	@Override
	public List<Price> listPrices() {
		return priceRepository.getAll();
	}

	@Override
	public List<Price> listPricesWithRelatedData() {
		return priceRepository.getAllWithOuterJoin("period", "touristEventComponent");
	}

}
