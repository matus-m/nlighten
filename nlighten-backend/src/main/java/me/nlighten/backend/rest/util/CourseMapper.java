package me.nlighten.backend.rest.util;

import java.util.List;

import fr.xebia.extras.selma.Mapper;
import fr.xebia.extras.selma.Maps;
import me.nlighten.backend.db.model.Course;
import me.nlighten.backend.rest.model.CourseDTO;

/**
 * The Interface DTOCourseMapper.
 * 
 * @author Lubo
 */
@Mapper
public interface CourseMapper {

  /**
   * From CourseDTO to Course, with all collections.
   *
   * @param courseDTO the course dto
   * @return the course
   */
  @Maps
  Course toCourse(CourseDTO courseDTO);

  /**
   * Update Course instance with CourseDTO data.
   *
   * @param courseDTO the course dto
   * @param course the course
   * @return the course
   */
  @Maps(withIgnoreFields = {"lessons", "comments", "questions"})
  Course toCourse(CourseDTO courseDTO, Course course);

  /**
   * From Course to CourseDTO, without all collections.
   *
   * @param course the course
   * @return the course dto
   */
  @Maps(withIgnoreFields = {"lessons", "comments", "questions"})
  CourseDTO toCourseDTO(Course course);

  /**
   * From Course to CourseDTO, with all collections.
   *
   * @param course the course
   * @return the course dto
   */
  @Maps
  CourseDTO toCourseDTOWithCollections(Course course);

  /**
   * From Courses to CourseDTO's, without all collections.
   *
   * @param courses the courses
   * @return the list
   */
  @Maps(withIgnoreFields = {"lessons", "comments", "questions"})
  List<CourseDTO> toCoursesDTO(List<Course> courses);
}
