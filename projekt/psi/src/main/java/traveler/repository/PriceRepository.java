package traveler.repository;

import java.util.List;

import traveler.command.PriceFilterCommand;
import traveler.model.Price;

public interface PriceRepository extends GenericRepository<Price, Long> {

	List<Price> getFiltered(PriceFilterCommand FilterCommand);

}
