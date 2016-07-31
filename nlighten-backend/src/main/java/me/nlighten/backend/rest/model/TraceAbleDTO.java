package me.nlighten.backend.rest.model;

import java.util.Date;

/**
 * The Class TraceAble.
 *
 * @author Lubo
 */
public class TraceAbleDTO {

  /**
   * The id.
   */
  private long id;

  /**
   * The creation user.
   */
  private String creationUser;

  /**
   * The modification user.
   */
  private String modificationUser;

  /**
   * The creation date.
   */
  private Date creationDate;

  /**
   * The update date.
   */
  private Date updateDate;

  /**
   * The loaded from db.
   */
  private boolean loadedFromDB;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCreationUser() {
    return creationUser;
  }

  public void setCreationUser(String creationUser) {
    this.creationUser = creationUser;
  }

  public String getModificationUser() {
    return modificationUser;
  }

  public void setModificationUser(String modificationUser) {
    this.modificationUser = modificationUser;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }

  public boolean isLoadedFromDB() {
    return loadedFromDB;
  }

  public void setLoadedFromDB(boolean loadedFromDB) {
    this.loadedFromDB = loadedFromDB;
  }

}
