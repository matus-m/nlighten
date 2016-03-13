package me.nlighten.backend.db.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;

import me.nlighten.backend.db.dao.exception.DAOException;
import me.nlighten.backend.db.dao.exception.DAOMessageException;
import me.nlighten.backend.db.model.Lesson;

/**
 * The Class LessonDAO.
 * 
 * @author Lubo
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class LessonDAO extends GenericDAO<Lesson> {

  /**
   * Load by id.
   *
   * @param graphName the graph name
   * @param id the id
   * @return the lesson
   * @throws DAOException the DAO exception
   */
  public Lesson loadById(String graphName, Long id) throws DAOException {
    try {
      return (Lesson) getEm().createNamedQuery("Lesson.loadById").setParameter("id", id)
          .setHint("javax.persistence.loadgraph", getEm().getEntityGraph(graphName))
          .getSingleResult();
    } catch (NoResultException nre) {
      return null;
    } catch (Exception e) {
      throw new DAOException(DAOMessageException.COULD_NOT_FIND_OBJECT_BY_ID, e);
    }
  }
}
