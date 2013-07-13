package br.com.votenofilme.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import br.com.votenofilme.dao.connect.JPAUtil;

abstract class GenericDAO<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private EntityManager em;

	private Class<T> entityClass;
	
	public void beginTransaction() {
		em = JPAUtil.getEntityManager();

		em.getTransaction().begin();
	}

	public void commit() {
		em.getTransaction().commit();
	}
	
	public void commitTransaction() {
		try {		
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
		} finally {
			em.close();
		}
		
	}

	public void rollback() {
		em.getTransaction().rollback();
	}

	public void closeTransaction() {
		em.close();
	}

	public void commitAndCloseTransaction() {
		commit();
		closeTransaction();
	}

	public void flush() {
		em.flush();
	}

	public void joinTransaction() {
		em = JPAUtil.getEntityManager();
		em.joinTransaction();
	}

	public GenericDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public void save(T entity) {
		beginTransaction();
		em.persist(entity);
		commitAndCloseTransaction();
	}

	public void delete(T entity) {
		beginTransaction();
		T entityToBeRemoved = em.merge(entity);

		em.remove(entityToBeRemoved);
		commitAndCloseTransaction();
	}

	public T update(T entity) {
		beginTransaction(); 
		T entityMerged = em.merge(entity);
		commitAndCloseTransaction();
		
		return entityMerged;
	}

	public T find(int entityID) {
		beginTransaction();
		T entity = em.find(entityClass, entityID);
		closeTransaction();
		
		return entity;
	}

	public T findReferenceOnly(int entityID) {
		beginTransaction();
		T entity = em.getReference(entityClass, entityID);
		closeTransaction();
		
		return entity;
	}

	// Using the unchecked because JPA does not have a
	// em.getCriteriaBuilder().createQuery()<T> method
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findAll() {
		beginTransaction();
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		List<T> list = em.createQuery(cq).getResultList();
		closeTransaction();
		
		return list;
	}

	// Using the unchecked because JPA does not have a
	// query.getSingleResult()<T> method
	@SuppressWarnings("unchecked")
	protected T findOneResult(String namedQuery, Map<String, Object> parameters) {
		beginTransaction();
		T result = null;

		try {
			Query query = em.createNamedQuery(namedQuery);

			// Method that will populate parameters if they are passed not null
			// and empty
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}

			result = (T) query.getSingleResult();

		} catch (NoResultException e) {
			System.out
					.println("No result found for named query: " + namedQuery);
		} catch (Exception e) {
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
		}

		closeTransaction();
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> findManyResult(String namedQuery, Map<String, Object> parameters) {
		beginTransaction();
		List<T> result = null;

		try {
			Query query = em.createNamedQuery(namedQuery);

			// Method that will populate parameters if they are passed not null
			// and empty
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}

			result = (List<T>) query.getResultList();

		} catch (NoResultException e) {
			System.out
					.println("No result found for named query: " + namedQuery);
		} catch (Exception e) {
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
		}

		closeTransaction();
		
		return result;
	}
	
	protected void executeQuery(String namedQuery, Map<String, Object> parameters) {	
		try {
			Query query = em.createNamedQuery(namedQuery);

			// Method that will populate parameters if they are passed not null
			// and empty
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}					

		} catch (NoResultException e) {
			System.out
					.println("No result found for named query: " + namedQuery);
		} catch (Exception e) {
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> executeQueryDynamicManyResult(String queryDynamic, Map<String, Object> parameters) {
		List<T> result = null;
		
		try {
			Query query = em.createQuery(queryDynamic, entityClass);

			// Method that will populate parameters if they are passed not null
			// and empty
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			
			result = (List<T>) query.getResultList();

		} catch (NoResultException e) {
			System.out
					.println("No result found for named query: " + queryDynamic);
		} catch (Exception e) {
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
		}		
		
		return result;
	}
	
	protected long findCheckConstraintResult(String namedQuery, Map<String, Object> parameters) {
		long result = 0;

		try {
			Query query = em.createNamedQuery(namedQuery);

			// Method that will populate parameters if they are passed not null
			// and empty
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}

			result = (Long) query.getSingleResult();

		} catch (NoResultException e) {
			System.out
					.println("No result found for named query: " + namedQuery);
		} catch (Exception e) {
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
		}

		return result;
	}
	

	private void populateQueryParameters(Query query,
			Map<String, Object> parameters) {
		for (Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}
	
	protected List findManyResultByNativeQuery(String nativeQuery, Map<String, Object> parameters) {
		beginTransaction();
		List result = null;

		try {
			Query query = em.createNativeQuery(nativeQuery);

			// Method that will populate parameters if they are passed not null
			// and empty
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}

			result = query.getResultList();

		} catch (NoResultException e) {
			System.out
					.println("No result found for named query: " + nativeQuery);
		} catch (Exception e) {
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
		}

		closeTransaction();
		
		return result;
	}

}
