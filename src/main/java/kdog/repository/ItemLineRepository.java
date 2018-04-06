package kdog.repository;

import kdog.entity.ItemLine;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ItemLineRepository extends AbstractRepository<ItemLine> {

	@PersistenceContext
	private EntityManager em;

	@Override
	EntityManager setEntityManager() {
		return em;
	}

	@Override
	Class<ItemLine> setEntityClass() {
		return ItemLine.class;
	}
}
