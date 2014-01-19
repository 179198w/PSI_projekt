package traveler.repository.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.transaction.annotation.Transactional;

import traveler.repository.GenericRepository;

@Transactional
public abstract class GenericRepositoryImpl<E, I extends Serializable> implements GenericRepository<E, I> {

	@Inject
	private final SessionFactory sessionFactory = null;

	abstract protected Class<E> getEntityClass();

	protected Session session() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public I save(E entity) {
		return (I) session().save(entity);
	}
	
	public void update(E entity) {
		session().update(entity);
	
	}
	
	@SuppressWarnings("unchecked")
	public E get(I id) {
		return (E) session().get(getEntityClass(), id);
	}

	@SuppressWarnings("unchecked")
	public List<E> getAll() {
		return (List<E>) session().createCriteria(getEntityClass()).list();
	}


	@SuppressWarnings("unchecked")
	public E getBy(String propertyName, Object value) {
		return (E) session().createCriteria(getEntityClass())
				.add(Restrictions.eq(propertyName, value))
				.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<E> getAllBy(String propertyName, Object value) {
		return (List<E>) session().createCriteria(getEntityClass())
				.add(Restrictions.eq(propertyName, value))
				.list();
	}
	
	public void delete(E entity) {
		session().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<E> getAllWithOuterJoin(String... properties) {
		Criteria criteria = session().createCriteria(getEntityClass());
		for (String property : properties) {
			criteria.createAlias(property, property, JoinType.LEFT_OUTER_JOIN);
		}
		return (List<E>) criteria.list();
	}
	
}
