package me.nlighten.backend.rest.model;

import java.util.Date;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import me.nlighten.backend.rest.model.enums.CourseTypeDTO;
import me.nlighten.backend.rest.model.enums.DifficultyDTO;

/**
 * The Class Course.
 * 
 * @author Lubo
 */
@Getter
@Setter
public class CourseDTO extends TraceAbleDTO {

  /** The title. */
  private String title;

  /** The description. */
  private String description;

  /** The tags. */
  private String tags;

  /** The author. */
  private String author;

  /** The Course type. */
  private CourseTypeDTO CourseType;

  /** The language. */
  private String language;

  /** The duration. */
  private Date duration;

  /** The difficulty. */
  private DifficultyDTO difficulty;

  /** The released. */
  private Date released;

  /** The rating. */
  private int rating;

  /** The resources. */
  private String resources;

  /** The lessons. */
  private Set<LessonDTO> lessons;

  /** The comments. */
  private Set<CommentDTO> comments;

  /** The questions. */
  private Set<QuestionDTO> questions;
}
