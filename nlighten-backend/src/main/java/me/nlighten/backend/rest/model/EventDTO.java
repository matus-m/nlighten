package me.nlighten.backend.rest.model;

import java.util.Date;

import me.nlighten.backend.db.model.Course;

/**
 * The Class EventDTO.
 *
 * @author Lubo
 */
public class EventDTO {

  /**
   * The id.
   */
  private long id;

  /**
   * The title.
   */
  private String title;

  /**
   * The start time.
   */
  private Date startTime;

  /**
   * The on air.
   */
  private boolean onAir;

  /**
   * The course.
   */
  private Course course;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public boolean isOnAir() {
    return onAir;
  }

  public void setOnAir(boolean onAir) {
    this.onAir = onAir;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

}
