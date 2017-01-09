package me.nlighten.backend.test.db.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import me.nlighten.backend.db.enums.CourseType;
import me.nlighten.backend.db.enums.Difficulty;
import me.nlighten.backend.db.model.Answer;
import me.nlighten.backend.db.model.Comment;
import me.nlighten.backend.db.model.Course;
import me.nlighten.backend.db.model.Event;
import me.nlighten.backend.db.model.Lesson;
import me.nlighten.backend.db.model.Question;
import me.nlighten.backend.db.model.User;

/**
 * The Class EntityCreatorUtility, is for creating object instances with random
 * data.
 * 
 * @author Lubo3
 */
public class EntityCreatorUtility {

  /** The random. */
  private static Random random = new Random();

  /**
   * Instantiates a new entity creator utility.
   */
  private EntityCreatorUtility() {
  }

  /**
   * Creates the course.
   *
   * @return the course
   */
  public static Course createCourse() {
    Course course = new Course();
    course.setTitle("name" + random.nextInt());
    course.setDescription("description" + random.nextInt());
    course.setTags("tags" + random.nextInt());
    course.setAuthor(createAuthor());
    course.setCourseType(CourseType.values()[random.nextInt(CourseType.values().length)]);
    course.setLanguage("language" + random.nextInt());
    course.setDuration(new Date());
    course.setDifficulty(Difficulty.values()[random.nextInt(Difficulty.values().length)]);
    course.setReleased(new Date());
    course.setRating(random.nextInt());
    course.setResources("resources" + random.nextInt());

    Set<Lesson> lessons = new HashSet<>();
    Set<Comment> comments = new HashSet<>();
    Set<Question> questions = new HashSet<>();
    Set<Event> events = new HashSet<>();

    for (int i = 0; i < random.nextInt(3); i++) {
      lessons.add(createLesson(course));
      comments.add(createComment(course, null));
      questions.add(createQuestion(course, null));
      events.add(createEvent(course));
    }

    course.setLessons(lessons);
    course.setComments(comments);
    course.setQuestions(questions);
    course.setEvents(events);
    return course;
  }

  /**
   * Creates the lesson.
   *
   * @param course
   *          the course
   * @return the lesson
   */
  public static Lesson createLesson(Course course) {
    Lesson lesson = new Lesson();
    lesson.setTitle("name" + random.nextInt());
    lesson.setDescription("description" + random.nextInt());
    lesson.setOrder(random.nextInt());
    lesson.setDuration(new Date());

    Set<Comment> comments = new HashSet<>();
    Set<Question> questions = new HashSet<>();
    for (int i = 0; i < random.nextInt(3); i++) {
      comments.add(createComment(null, lesson));
      questions.add(createQuestion(null, lesson));
    }

    lesson.setComments(comments);
    lesson.setQuestions(questions);
    lesson.setCourse(course);
    return lesson;
  }

  /**
   * Creates the comment.
   *
   * @param course
   *          the course
   * @param lesson
   *          the lesson
   * @return the comment
   */
  public static Comment createComment(Course course, Lesson lesson) {
    Comment comment = new Comment();
    comment.setAuthor(createAuthor());
    comment.setText("text" + random.nextInt());
    comment.setCourse(course);
    comment.setLesson(lesson);
    return comment;
  }

  /**
   * Creates the question.
   *
   * @param course
   *          the course
   * @param lesson
   *          the lesson
   * @return the question
   */
  public static Question createQuestion(Course course, Lesson lesson) {
    Question question = new Question();
    question.setAuthor(createAuthor());
    question.setText("text" + random.nextInt());
    question.setCourse(course);
    question.setLesson(lesson);

    Set<Answer> answers = new HashSet<>();
    for (int i = 0; i < random.nextInt(3); i++) {
      answers.add(createAnswer(question));
    }

    question.setAnswers(answers);
    return question;
  }

  /**
   * Creates the answer.
   *
   * @param question
   *          the question
   * @return the answer
   */
  public static Answer createAnswer(Question question) {
    Answer answer = new Answer();
    answer.setAuthor(createAuthor());
    answer.setText("text" + random.nextInt());
    answer.setQuestion(question);
    return answer;
  }

  /**
   * Creates the event.
   *
   * @param course
   *          the course
   * @return the event
   */
  public static Event createEvent(Course course) {
    Event event = new Event();
    event.setTitle("title");
    event.setOnAir(random.nextBoolean());
    event.setCourse(course);
    return event;
  }

  public static User createUser() {

    String userString = "user" + random.nextInt();

    User user = new User();
    user.setUsername(userString);
    user.setEmail(userString + "@localhost");
    user.setPassword(userString);

    Event ownedEvent = createEvent(createCourse());
    Event event = createEvent(createCourse());

    user.getEvents().add(ownedEvent);
    user.getSuscribedEvents().add(event);

    return user;
  }
  

  public static User updatedUser() {
    User user = createUser();
    user.setUsername("moderator");
    user.setEmail("moderator@localhost");
    user.setPassword("p@$$w0r2");
    return null;
  }

  public static User createAuthor() {
    User author = new User();
    author.setUsername("admin");
    author.setEmail("admin@localhost");
    author.setPassword("password");
    author.setAnswers(new ArrayList<>());
    author.setEvents(new ArrayList<>());
    author.setSuscribedEvents(new ArrayList<>());

    return author;
  }

  public static User updatedAuthor() {
    User author = createAuthor();
    author.setUsername("collaborator");
    author.setEmail("collaborator@localhost");
    return author;
  }


}
