package me.nlighten.backend.rest.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class TraceAble.
 * 
 * @author Lubo
 */
@Getter
@Setter
public class TraceAbleDTO {

  /** The id. */
  private long id;

  /** The creation user. */
  private String creationUser;

  /** The modification user. */
  private String modificationUser;

  /** The creation date. */
  private Date creationDate;

  /** The update date. */
  private Date updateDate;

  /** The loaded from db. */
  private boolean loadedFromDB;
}
