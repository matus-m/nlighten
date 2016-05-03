package me.nlighten.backend.rest.model;

import java.util.Date;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class Lesson.
 * 
 * @author Lubo
 */
@Getter
@Setter
public class LessonDTO extends TraceAbleDTO {

  /** The title. */
  private String title;

  /** The description. */
  private String description;

  /** The order. */
  private int order;

  /** The duration. */
  private Date duration;

  /** The comments. */
  private Set<CommentDTO> comments;

  /** The questions. */
  private Set<QuestionDTO> questions;

  /** The course. */
  private CourseDTO course;
}
