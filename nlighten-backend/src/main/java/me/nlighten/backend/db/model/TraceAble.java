package me.nlighten.backend.db.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The Class TraceAble.
 *
 * @author Lubo
 */
// @EntityListeners(TraceUser.class)
@MappedSuperclass
public class TraceAble {

  /**
   * The id.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  /**
   * The creation user.
   */
  @Column(name = "CREATION_USER")
  private String creationUser;

  /**
   * The modification user.
   */
  @Column(name = "MODIFICATION_USER")
  private String modificationUser;

  /**
   * The creation date.
   */
  @Column(name = "CREATION_DATE")
  @Temporal(TemporalType.DATE)
  private Date creationDate;

  /**
   * The update date.
   */
  @Column(name = "MODIFICATION_DATE")
  @Temporal(TemporalType.DATE)
  private Date updateDate;

  /**
   * The loaded from db.
   */
  @Transient
  private boolean loadedFromDB;

  @PostLoad
  public void postLoad() {
    loadedFromDB = true;
  }

  @PreUpdate
  public void preUpdate() {
    updateDate = new Date();
  }

  @PrePersist
  public void prePersist() {
    creationDate = new Date();
    updateDate = new Date();
  }

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
