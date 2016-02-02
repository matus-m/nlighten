package me.nlighten.backend.db.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import me.nlighten.backend.db.enums.CourseType;
import me.nlighten.backend.db.enums.Difficulty;

/**
 * The Class Course.
 * 
 * @author Lubo
 */
@Entity
@Table(name = "COURSE")
public class Course extends TraceAble {

  /** The title. */
  private String title;

  /** The description. */
  @Column(length = 2048)
  private String description;

  /** The tags. */
  private String tags;

  /** The author. */
  private String author;

  /** The Course type. */
  @Column(name = "COURSE_TYPE")
  @Enumerated(EnumType.STRING)
  private CourseType CourseType;

  /** The language. */
  private String language;

  /** The duration. */
  @Temporal(TemporalType.TIMESTAMP)
  private Date duration;

  /** The difficulty. */
  @Enumerated(EnumType.STRING)
  private Difficulty difficulty;

  /** The released. */
  @Temporal(TemporalType.TIMESTAMP)
  private Date released;

  /** The rating. */
  private int rating;

  /** The resources. */
  private String resources;

  /** The lessons. */
  @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, orphanRemoval = true)
  private Set<Lesson> lessons;

  /** The comments. */
  @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, orphanRemoval = true)
  private Set<Comment> comments;

  /** The questions. */
  @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, orphanRemoval = true)
  private Set<Question> questions;

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

  public CourseType getCourseType() {
    return CourseType;
  }

  public void setCourseType(CourseType CourseType) {
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

  public Difficulty getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(Difficulty difficulty) {
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

  public Set<Lesson> getLessons() {
    return lessons;
  }

  public void setLessons(Set<Lesson> lessons) {
    this.lessons = lessons;
  }

  public Set<Comment> getComments() {
    return comments;
  }

  public void setComments(Set<Comment> comments) {
    this.comments = comments;
  }

  public Set<Question> getQuestions() {
    return questions;
  }

  public void setQuestions(Set<Question> questions) {
    this.questions = questions;
  }
}
