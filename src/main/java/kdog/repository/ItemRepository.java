package kdog.repository;

import kdog.entity.Item;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ItemRepository extends AbstractRepository<Item> {

	@PersistenceContext(unitName = "entity")
	private EntityManager entityManager;

	EntityManager setEntityManager() {
		return entityManager;
	}

	Class<Item> setEntityClass() {
		return Item.class;
	}
}
