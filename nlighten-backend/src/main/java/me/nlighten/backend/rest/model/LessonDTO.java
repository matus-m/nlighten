package me.nlighten.backend.rest.model;

import java.util.Date;
import java.util.Set;

/**
 * The Class Lesson.
 *
 * @author Lubo
 */
public class LessonDTO extends TraceAbleDTO {

  /**
   * The title.
   */
  private String title;

  /**
   * The description.
   */
  private String description;

  /**
   * The order.
   */
  private int order;

  /**
   * The duration.
   */
  private Date duration;

  /**
   * The comments.
   */
  private Set<CommentDTO> comments;

  /**
   * The questions.
   */
  private Set<QuestionDTO> questions;

  /**
   * The course.
   */
  private CourseDTO course;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public Date getDuration() {
    return duration;
  }

  public void setDuration(Date duration) {
    this.duration = duration;
  }

  public Set<CommentDTO> getComments() {
    return comments;
  }

  public void setComments(Set<CommentDTO> comments) {
    this.comments = comments;
  }

  public Set<QuestionDTO> getQuestions() {
    return questions;
  }

  public void setQuestions(Set<QuestionDTO> questions) {
    this.questions = questions;
  }

  public CourseDTO getCourse() {
    return course;
  }

  public void setCourse(CourseDTO course) {
    this.course = course;
  }

}
