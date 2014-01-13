package traveler.repository.impl;

import org.springframework.stereotype.Repository;

import traveler.model.Price;
import traveler.repository.PriceRepository;

@Repository
public class PriceRepositoryImpl extends GenericRepositoryImpl<Price, Long> implements PriceRepository {

	@Override
	protected Class<Price> getEntityClass() {
		return Price.class;
	}

}
