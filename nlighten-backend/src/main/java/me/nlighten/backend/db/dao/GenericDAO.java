package me.nlighten.backend.db.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import me.nlighten.backend.db.dao.exception.DAOException;
import me.nlighten.backend.db.dao.exception.DAOMessageException;

/**
 * The Class GenericDAO.
 *
 * @param <T> the generic type
 * 
 * @author Lubo3
 */

public class GenericDAO<T> {

  /** The em. */
  @Inject
  private EntityManager em;

  /** The entity class. */
  private Class<T> entityClass;

  /**
   * Instantiates a new generic dao.
   */
  public GenericDAO() {
    Type genericSuperclass = getClass().getGenericSuperclass();
    if (genericSuperclass instanceof Class) {
      genericSuperclass = ((Class<?>) genericSuperclass).getGenericSuperclass();
    }
    ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
    Type type = parameterizedType.getActualTypeArguments()[0];
    Class<T> tmp = null;
    if (type instanceof Class) {
      tmp = (Class<T>) type;
    } else if (type instanceof ParameterizedType) {
      tmp = (Class<T>) ((ParameterizedType) type).getRawType();
    }
    this.entityClass = tmp;
  }

  /**
   * Save.
   *
   * @param t the t
   * @return the t
   * @throws DAOException the DAO exception
   */
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public T save(T t) throws DAOException {
    try {
      em.persist(t);
      em.flush();
      return t;
    } catch (Exception e) {
      throw new DAOException(DAOMessageException.OBJECT_COULD_NOT_BE_SAVED, e);
    }
  }

  /**
   * Merge.
   *
   * @param t the t
   * @return the t
   * @throws DAOException the DAO exception
   */
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public T merge(T t) throws DAOException {
    try {
      T result = null;
      result = em.merge(t);
      em.flush();
      return result;
    } catch (Exception e) {
      throw new DAOException(DAOMessageException.OBJECT_COULD_NOT_BE_MERGED, e);
    }
  }

  /**
   * Find by id.
   *
   * @param t the t
   * @param id the id
   * @return the t
   * @throws DAOException the DAO exception
   */
  public T findById(Object id) throws DAOException {
    try {
      return em.find(entityClass, id);
    } catch (NoResultException nre) {
      return null;
    } catch (Exception e) {
      throw new DAOException(DAOMessageException.COULD_NOT_FIND_OBJECT_BY_ID, e);
    }
  }

  /**
   * Find all.
   *
   * @param t the t
   * @return the list
   * @throws DAOException the DAO exception
   */
  public List<T> findAll() throws DAOException {
    try {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<T> cq = cb.createQuery(entityClass);

      Root<T> root = cq.from(entityClass);

      TypedQuery<T> typedQuery = em.createQuery(cq);
      return typedQuery.getResultList();
    } catch (Exception e) {
      throw new DAOException(DAOMessageException.COULD_NOT_FIND_ALL_OBJECTS, e);
    }
  }

  /**
   * Delete by id.
   *
   * @param id the id
   * @return true, if successful
   * @throws DAOException the DAO exception
   */
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public boolean deleteById(Object id) throws DAOException {
    try {
      boolean result = false;
      Object toRemove = findById(id);
      em.remove(toRemove);
      result = true;
      return result;
    } catch (Exception e) {
      throw new DAOException(DAOMessageException.OBJECT_COULD_NOT_BE_DELETED, e);
    }
  }

  /**
   * Delete.
   *
   * @param t the t
   * @return true, if successful
   * @throws DAOException the DAO exception
   */
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public boolean delete(T t) throws DAOException {
    try {
      boolean result = false;
      em.remove(t);
      result = true;
      return result;
    } catch (Exception e) {
      throw new DAOException(DAOMessageException.OBJECT_COULD_NOT_BE_DELETED, e);
    }
  }

  public EntityManager getEm() {
    return em;
  }
}
