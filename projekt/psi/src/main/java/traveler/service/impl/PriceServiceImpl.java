package traveler.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import traveler.command.PriceCommand;
import traveler.model.Period;
import traveler.model.Price;
import traveler.model.TouristEventComponent;
import traveler.repository.PriceRepository;
import traveler.service.PeriodService;
import traveler.service.PriceService;
import traveler.service.TouristEventComponentService;
import traveler.utils.MapperFacadeFactoryBean;

@Service
public class PriceServiceImpl implements PriceService {

	@Inject
	private PriceRepository priceRepository;

	@Inject
	private MapperFacadeFactoryBean mapperFacade;

	@Inject
	private PeriodService periodService;

	@Inject
	private TouristEventComponentService touristEventComponentService;

	@Override
	public List<Price> listPrices() {
		return priceRepository.getAll();
	}

	@Override
	public List<Price> listPricesWithRelatedData() {
		return priceRepository.getAllWithOuterJoin("period", "touristEventComponent");
	}

	@Override
	public void addPrice(PriceCommand priceCommand) {
		priceCommand.setAdultValue(100 * priceCommand.getAdultValue());
		priceCommand.setChildValue(100 * priceCommand.getChildValue());

		for (Long periodId : priceCommand.getPeriodIds()) {
			Price price = mapperFacade.getObject().map(priceCommand, Price.class);

			Period period = periodService.getPeriod(periodId);
			price.setPeriod(period);

			if (priceCommand.getTouristEventComponentId() != null) {
				TouristEventComponent touristEventComponent = touristEventComponentService.getTouristEventComponent(priceCommand.getTouristEventComponentId());
				price.setTouristEventComponent(touristEventComponent);
			}

			priceRepository.save(price);
		}
	}

}
