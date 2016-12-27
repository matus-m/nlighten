package me.nlighten.backend.test.db.dao;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import me.nlighten.backend.db.dao.UserDAO;
import me.nlighten.backend.db.dao.exception.DAOException;
import me.nlighten.backend.db.model.User;
import me.nlighten.backend.test.AbstractTest;

@RunWith(Arquillian.class)
public class UserDAOTest extends AbstractTest {

  /** The logger. */
  @Inject
  private Logger logger;

  @Inject
  private UserDAO userDAO;

  @Test
  public void createUser() {
    User user = EntityCreatorUtility.createUser();
    try {
      user = userDAO.save(user);
      Assert.assertNotNull(user);
    } catch (DAOException e) {
      logger.error(e.getMessageKey().name());
      Assert.assertFalse(true);
    }

  }
}
