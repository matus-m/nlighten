package me.nlighten.backend.rest.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import me.nlighten.backend.db.model.Course;


/**
 * The Class EventDTO.
 * 
 * @author Lubo
 */
@Getter
@Setter
public class EventDTO {

  /** The id. */
  private long id;

  /** The title. */
  private String title;

  /** The start time. */
  private Date startTime;

  /** The on air. */
  private boolean onAir;

  /** The course. */
  private Course course;
}
