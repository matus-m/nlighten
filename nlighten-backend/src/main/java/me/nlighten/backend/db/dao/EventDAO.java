package me.nlighten.backend.db.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import me.nlighten.backend.db.dao.exception.DAOException;
import me.nlighten.backend.db.dao.exception.DAOMessageException;
import me.nlighten.backend.db.model.Event;

/**
 * The Class EventDAO.
 * 
 * @author Lubo
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class EventDAO extends GenericDAO<Event> {

  /**
   * Find by title.
   *
   * @param title the title
   * @return the event
   * @throws DAOException the DAO exception
   */
  public Event findByTitle(String title) throws DAOException {
    try {
      CriteriaBuilder cb = getEm().getCriteriaBuilder();
      CriteriaQuery<Event> cq = cb.createQuery(Event.class);

      Root<Event> root = cq.from(Event.class);
      cq.where(cb.equal(root.get("title"), title));

      TypedQuery<Event> typedQuery = getEm().createQuery(cq);
      return typedQuery.getSingleResult();
    } catch (Exception e) {
      throw new DAOException(DAOMessageException.COULD_NOT_FIND_OBJECT_BY_TITLE, e);
    }
  }
}
