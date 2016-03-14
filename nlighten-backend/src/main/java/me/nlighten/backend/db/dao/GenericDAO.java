package me.nlighten.backend.db.dao;

import java.util.List;

import javax.ejb.Stateless;
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
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class GenericDAO<T> {

  /** The em. */
  @Inject
  private EntityManager em;

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
      throw new DAOException(e, DAOMessageException.OBJECT_COULD_NOT_BE_SAVED);
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
      throw new DAOException(e, DAOMessageException.OBJECT_COULD_NOT_BE_MERGED);
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
  public T findById(Class<T> t, Object id) throws DAOException {
    try {
      return em.find(t, id);
    } catch (NoResultException nre) {
      return null;
    } catch (Exception e) {
      throw new DAOException(e, DAOMessageException.COULD_NOT_FIND_OBJECT_BY_ID);
    }
  }

  /**
   * Find all.
   *
   * @param t the t
   * @return the list
   * @throws DAOException the DAO exception
   */
  public List<T> findAll(Class<T> t) throws DAOException {
    try {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<T> cq = cb.createQuery(t);

      Root<T> root = cq.from(t);

      TypedQuery<T> typedQuery = em.createQuery(cq);
      return typedQuery.getResultList();
    } catch (Exception e) {
      throw new DAOException(e, DAOMessageException.COULD_NOT_FIND_ALL_OBJECTS);
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
      t = em.merge(t);
      em.remove(t);
      result = true;
      return result;
    } catch (Exception e) {
      throw new DAOException(e, DAOMessageException.OBJECT_COULD_NOT_BE_DELETED);
    }
  }

}
