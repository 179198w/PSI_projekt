package traveler.repository;

import java.io.Serializable;
import java.util.List;

public interface GenericRepository<E, I extends Serializable> {

	I save(E entity);

	void update(E entity);

	E get(I id);

	void delete(E entity);

	List<E> getAll();

	E getBy(String propertyName, Object value);

	List<E> getAllBy(String propertyName, Object value);
	
	List<E> getAllByPartString(String propertyName, String value);
	
	List<E> getAllWithOuterJoin(String... properties);

}
