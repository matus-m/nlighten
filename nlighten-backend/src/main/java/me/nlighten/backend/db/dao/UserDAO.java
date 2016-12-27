package me.nlighten.backend.db.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import me.nlighten.backend.db.dao.exception.DAOException;
import me.nlighten.backend.db.dao.exception.DAOMessageException;
import me.nlighten.backend.db.model.User;

/**
 * The Class UserDAO.
 * 
 * @author Dorin Gheorghe Brage
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class UserDAO extends GenericDAO<User> {

  /**
   * Load an User given the ID.
   * 
   * @param id the User ID
   * @return the User's object
   * @throws DAOException if {@link #findById(Object id) findById} it fails or if the query throws
   *         exception.
   */
  public User loadById(long id) throws DAOException {

    User user = null;

    try {

      user = findById(id);

      if (!user.equals(null)) {
        // LEFT JOIN FETCH to avoid laxy exception
        Query q = getEm().createQuery("SELECT u from USERS u WHERE u.id=:id");
        q.setParameter("id", id);

        user = (User) q.getSingleResult();

        return user;
      }

    } catch (Exception e) {
      throw new DAOException(DAOMessageException.COULD_NOT_LOAD_OBJECT_BY_ID, e);
    }

    return null;

  }
}
