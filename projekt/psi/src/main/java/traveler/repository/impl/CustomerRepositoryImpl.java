package traveler.repository.impl;

import org.springframework.stereotype.Repository;

import traveler.model.Customer;
import traveler.repository.CustomerRepository;

@Repository
public class CustomerRepositoryImpl extends GenericRepositoryImpl<Customer, Long> implements CustomerRepository {

	@Override
	protected Class<Customer> getEntityClass() {
		return Customer.class;
	}

}
