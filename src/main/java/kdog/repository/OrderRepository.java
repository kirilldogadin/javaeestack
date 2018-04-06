package kdog.repository;

import kdog.entity.Order;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class OrderRepository extends AbstractRepository<Order> {

	@PersistenceContext(unitName = "entity")
	private EntityManager entityManager;

	EntityManager setEntityManager() {
		return entityManager;
	}

	Class<Order> setEntityClass() {
		return Order.class;
	}

	@Override
	public Order save(Order entity) {
		em.persist(entity);
		return entity;
	}

	public Order saveAndFlush(Order entity){ // делаем явный сброс, но не теряем транзакционность
		save(entity);
		em.flush();
		return entity;
	}
}
