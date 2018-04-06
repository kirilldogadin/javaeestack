package kdog.repository;

import kdog.entity.BaseEntity;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * для операций с бд лучше использовать типизированные модули/либы,
 * например QueryDsl, spring-data тоже решает "типичные" задачи
 * @param <T> entity потомок BaseEntity
 */
public abstract class AbstractRepository<T extends BaseEntity> {

	protected EntityManager em;
	private Class<T> type;
	private String findAllSelect;
	private String findIdInSelect;
	private String softDeleteByIdInQuery;
	private String whereActive1;
	private String whereActive0;

	@PostConstruct
	public void init() {
		em = setEntityManager();
		type = setEntityClass();
		whereActive0 = " AND t.active = false";
		whereActive1 = " AND t.active = true";
		findAllSelect = "SELECT t FROM "
				+ type.getSimpleName() + " t"
				+ " WHERE t.active=true";
		findIdInSelect = "SELECT t FROM "
				+ type.getSimpleName() + " t"
				+ " WHERE t.id IN(:ids)"
				+ whereActive1;
		softDeleteByIdInQuery = "UPDATE "
				+ type.getSimpleName() + " t"
				+ " SET t.active = false"
				+ " WHERE t.id IN(:ids)"
				+ whereActive1;

	}

	abstract EntityManager setEntityManager();

	abstract Class<T> setEntityClass();

	public List<T> findAll() {
		TypedQuery<T> resultList = em.createQuery(
				findAllSelect, type);
		return resultList.getResultList();
	}

	public List<T> findByIdIn(List<Long> list) {
		TypedQuery<T> findIdInQuery = em.createQuery(
				findIdInSelect, type);
		findIdInQuery.setParameter("ids",list);
		return findIdInQuery.getResultList();
	}

	public T find(long id) {
		return em.find(type, id);
	}

	public T save(T entity) {
		em.persist(entity);
		return entity;
	}

	public void delete(T entity) {
		em.remove(entity);

	}

	public void deleteById(Long id) {
		Query deleteInQuery = em.createQuery(
				softDeleteByIdInQuery);
		deleteInQuery.setParameter("ids",id);
		deleteInQuery.executeUpdate();
	}

	public void deleteByIdIn(List<Long> list) {
		TypedQuery<T> deleteInQuery = em.createNamedQuery(
				softDeleteByIdInQuery, type);
		deleteInQuery.setParameter("ids",list);
		deleteInQuery.executeUpdate();
	}

	public T update(T entity) {
		em.merge(entity);
		return entity;
	}
}
