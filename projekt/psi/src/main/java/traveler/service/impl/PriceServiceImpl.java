package traveler.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import traveler.command.PriceCommand;
import traveler.command.PriceEditCommand;
import traveler.command.PriceFilterCommand;
import traveler.model.City;
import traveler.model.Period;
import traveler.model.Price;
import traveler.model.PriceType;
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
	
	public void removePrice(Long priceId){
		Price price= priceRepository.get(priceId);
		priceRepository.delete(price);
	}
	
	@Override
	public void addPrice(PriceCommand priceCommand) {
		priceCommand.setAdultValue(priceCommand.getAdultValue());
		priceCommand.setChildValue(priceCommand.getChildValue());

		for (Long periodId : priceCommand.getPeriodIds()) {
			Price price = mapperFacade.getObject().map(priceCommand, Price.class);

			Period period = periodService.getPeriod(periodId);
			price.setPeriod(period);

			if (PriceType.COMPONENT.equals(priceCommand.getType())) {
				TouristEventComponent touristEventComponent = touristEventComponentService.getTouristEventComponent(priceCommand.getTouristEventComponentId());
				price.setTouristEventComponent(touristEventComponent);
			}

			priceRepository.save(price);
		}
	}

	@Override
	@Transactional
	public PriceEditCommand getPriceEditCommand(Long priceId) {
		Price price = priceRepository.get(priceId);
		PriceEditCommand priceEditCommand = mapperFacade.getObject().map(price, PriceEditCommand.class);
		priceEditCommand.setAdultValue(priceEditCommand.getAdultValue() / 100);
		priceEditCommand.setChildValue(priceEditCommand.getChildValue() / 100);
		
		priceEditCommand.setPeriodId(price.getPeriod().getId());
		priceEditCommand.setTouristEventId(price.getPeriod().getTouristEvent().getId());
		if (PriceType.COMPONENT.equals(price.getType())) {
			priceEditCommand.setTouristEventComponentId(price.getTouristEventComponent().getId());
		}
		
		return priceEditCommand;
	}

	@Override
	@Transactional
	public void updatePrice(PriceEditCommand priceEditCommand) {
		priceEditCommand.setAdultValue(100 * priceEditCommand.getAdultValue());
		priceEditCommand.setChildValue(100 * priceEditCommand.getChildValue());
		
		Price price = mapperFacade.getObject().map(priceEditCommand, Price.class);

		Period period = periodService.getPeriod(priceEditCommand.getPeriodId());
		price.setPeriod(period);

		if (PriceType.COMPONENT.equals(priceEditCommand.getType())) {
			TouristEventComponent touristEventComponent = touristEventComponentService.getTouristEventComponent(priceEditCommand.getTouristEventComponentId());
			price.setTouristEventComponent(touristEventComponent);
		}

		priceRepository.update(price);
	}

	@Override
	public List<Price> listPricesWithRelatedData(PriceFilterCommand filterCommand) {
		return priceRepository.getFiltered(filterCommand);
	}

}
