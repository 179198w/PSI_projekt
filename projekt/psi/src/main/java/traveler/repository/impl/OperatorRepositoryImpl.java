package traveler.repository.impl;

import org.springframework.stereotype.Repository;

import traveler.model.Operator;
import traveler.repository.OperatorRepository;

@Repository
public class OperatorRepositoryImpl extends GenericRepositoryImpl<Operator, Long> implements OperatorRepository {

	@Override
	protected Class<Operator> getEntityClass() {
		return Operator.class;
	}

}
