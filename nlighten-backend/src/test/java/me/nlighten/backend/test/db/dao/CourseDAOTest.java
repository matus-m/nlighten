package me.nlighten.backend.test.db.dao;

import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import me.nlighten.backend.db.dao.CommentDAO;
import me.nlighten.backend.db.dao.CourseDAO;
import me.nlighten.backend.db.dao.LessonDAO;
import me.nlighten.backend.db.dao.QuestionDAO;
import me.nlighten.backend.db.dao.exception.DAOException;
import me.nlighten.backend.db.model.Comment;
import me.nlighten.backend.db.model.Course;
import me.nlighten.backend.db.model.Lesson;
import me.nlighten.backend.db.model.Question;
import me.nlighten.backend.test.AbstractTest;

/**
 * The Class CourseDAOTest.
 * 
 * @author Lubo3
 */
@RunWith(Arquillian.class)
public class CourseDAOTest extends AbstractTest {

  /** The logger. */
  @Inject
  private Logger logger;

  /** The course dao. */
  @Inject
  private CourseDAO courseDAO;

  /** The comment dao. */
  @Inject
  private CommentDAO commentDAO;

  /** The lesson dao. */
  @Inject
  private LessonDAO lessonDAO;

  /** The question dao. */
  @Inject
  private QuestionDAO questionDAO;

  /**
   * Save test.
   *
   * @throws Exception the exception
   */
  @Test
  public void saveTest() throws Exception {
    try {
      Course course = EntityCreatorUtility.createCourse();
      Course savedCourse = courseDAO.save(course);
      Assert.assertNotNull(savedCourse);
      Assert.assertEquals(course.getId(), savedCourse.getId());
    } catch (DAOException e) {
      logger.error(e.getMessageKey().name());
      Assert.assertFalse(true);
    }
  }

  /**
   * Merge test.
   *
   * @throws Exception the exception
   */
  @Test
  public void mergeTest() throws Exception {
    try {
      Course course = EntityCreatorUtility.createCourse();
      Course savedCourse = courseDAO.save(course);
      Assert.assertNotNull(savedCourse);
      Assert.assertEquals(course.getId(), savedCourse.getId());

      savedCourse.setTitle("modifiedName");
      Course mergedCourse = courseDAO.merge(savedCourse);
      Assert.assertNotNull(mergedCourse);
      Assert.assertEquals(savedCourse.getTitle(), mergedCourse.getTitle());
    } catch (DAOException e) {
      logger.error(e.getMessageKey().name());
      Assert.assertFalse(true);
    }
  }

  /**
   * Find by id test.
   *
   * @throws Exception the exception
   */
  @Test
  public void findByIdTest() throws Exception {
    try {
      Course course = EntityCreatorUtility.createCourse();
      Course savedCourse = courseDAO.save(course);
      Assert.assertNotNull(savedCourse);
      Assert.assertEquals(course.getId(), savedCourse.getId());

      Course foundCourse = courseDAO.findById(savedCourse.getId());
      Assert.assertNotNull(foundCourse);
      Assert.assertEquals(savedCourse.getId(), foundCourse.getId());
    } catch (DAOException e) {
      logger.error(e.getMessageKey().name());
      Assert.assertFalse(true);
    }
  }

  /**
   * Find all.
   *
   * @throws Exception the exception
   */
  @Test
  public void findAll() throws Exception {
    try {
      Course course = EntityCreatorUtility.createCourse();
      Course savedCourse = (Course) courseDAO.save(course);
      Assert.assertNotNull(savedCourse);
      Assert.assertEquals(course.getId(), savedCourse.getId());

      List<Course> foundCourses = courseDAO.findAll();
      Assert.assertNotNull(foundCourses);
      Assert.assertTrue(foundCourses.size() > 0);
    } catch (DAOException e) {
      logger.error(e.getMessageKey().name());
      Assert.assertFalse(true);
    }
  }

  /**
   * Delete.
   *
   * @throws Exception the exception
   */
  @Test
  public void delete() throws Exception {
    try {
      // save course
      Course course = EntityCreatorUtility.createCourse();
      Course savedCourse = (Course) courseDAO.save(course);
      Assert.assertNotNull(savedCourse);
      Assert.assertEquals(course.getId(), savedCourse.getId());

      // save comment
      for (Comment comment : course.getComments()) {
        Comment savedComment = commentDAO.save(comment);
        Assert.assertNotNull(savedComment);
        Assert.assertEquals(comment.getId(), savedComment.getId());
      }

      // save lesson
      for (Lesson lesson : course.getLessons()) {
        Lesson savedLesson = lessonDAO.save(lesson);
        Assert.assertNotNull(savedLesson);
        Assert.assertEquals(lesson.getId(), savedLesson.getId());
      }

      // save question
      for (Question question : course.getQuestions()) {
        Question savedQuestion = questionDAO.save(question);
        Assert.assertNotNull(savedQuestion);
        Assert.assertEquals(question.getId(), savedQuestion.getId());
      }

      Boolean isDeleted = courseDAO.deleteById(savedCourse.getId());
      Assert.assertTrue(isDeleted);
      Course foundCourse = courseDAO.findById(savedCourse.getId());
      Assert.assertNull(foundCourse);
    } catch (DAOException e) {
      logger.error(e.getMessageKey().name());
      Assert.assertFalse(true);
    }
  }

}
