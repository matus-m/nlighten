package me.nlighten.backend.db.model;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;

/**
 * The listener for receiving principalName.
 *
 * @author Lubo
 */
@Stateless
public class PrincipalNameListener {

  @Resource
  private EJBContext ejbContext;

  /**
   * Gets the principal name.
   *
   * @return the principal name
   */
  public String getPrincipalName() {
    String userName = "anonymous";
    if (ejbContext.getCallerPrincipal() != null) {
      userName = ejbContext.getCallerPrincipal().getName();
    }
    return userName;
  }

}
