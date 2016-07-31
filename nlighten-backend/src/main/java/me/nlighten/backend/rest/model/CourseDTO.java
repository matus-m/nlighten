package me.nlighten.backend.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import java.util.Set;

import me.nlighten.backend.rest.model.enums.CourseTypeDTO;
import me.nlighten.backend.rest.model.enums.DifficultyDTO;

/**
 * The Class Course.
 *
 * @author Lubo
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseDTO extends TraceAbleDTO {

  /**
   * The title.
   */
  private String title;

  /**
   * The description.
   */
  private String description;

  /**
   * The tags.
   */
  private String tags;

  /**
   * The author.
   */
  private String author;

  /**
   * The Course type.
   */
  private CourseTypeDTO CourseType;

  /**
   * The language.
   */
  private String language;

  /**
   * The duration.
   */
  private Date duration;

  /**
   * The difficulty.
   */
  private DifficultyDTO difficulty;

  /**
   * The released.
   */
  private Date released;

  /**
   * The rating.
   */
  private int rating;

  /**
   * The resources.
   */
  private String resources;

  /**
   * The lessons.
   */
  private Set<LessonDTO> lessons;

  /**
   * The comments.
   */
  private Set<CommentDTO> comments;

  /**
   * The questions.
   */
  private Set<QuestionDTO> questions;

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

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public CourseTypeDTO getCourseType() {
    return CourseType;
  }

  public void setCourseType(CourseTypeDTO CourseType) {
    this.CourseType = CourseType;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public Date getDuration() {
    return duration;
  }

  public void setDuration(Date duration) {
    this.duration = duration;
  }

  public DifficultyDTO getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(DifficultyDTO difficulty) {
    this.difficulty = difficulty;
  }

  public Date getReleased() {
    return released;
  }

  public void setReleased(Date released) {
    this.released = released;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public String getResources() {
    return resources;
  }

  public void setResources(String resources) {
    this.resources = resources;
  }

  public Set<LessonDTO> getLessons() {
    return lessons;
  }

  public void setLessons(Set<LessonDTO> lessons) {
    this.lessons = lessons;
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

}
