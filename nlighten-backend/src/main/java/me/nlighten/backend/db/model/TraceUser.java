package me.nlighten.backend.db.model;

import javax.inject.Inject;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * The Class TraceUser.
 * 
 * @author Lubo3
 */
public class TraceUser {

   /** The principal name listener. */
   @Inject
   private PrincipalNameListener principalNameListener;

  /**
   * Pre update.
   *
   * @param traceAble the trace able
   */
  @PreUpdate
  public void preUpdate(TraceAble traceAble) {
     traceAble.setModificationUser(principalNameListener.getPrincipalName());
  }

  /**
   * Pre persist.
   *
   * @param traceAble the trace able
   */
  @PrePersist
  public void prePersist(TraceAble traceAble) {
     traceAble.setCreationUser(principalNameListener.getPrincipalName());
     traceAble.setModificationUser(principalNameListener.getPrincipalName());
  }

}
