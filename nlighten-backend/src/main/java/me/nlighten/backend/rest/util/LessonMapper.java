package me.nlighten.backend.rest.util;

import java.util.List;

import fr.xebia.extras.selma.Mapper;
import fr.xebia.extras.selma.Maps;
import me.nlighten.backend.db.model.Lesson;
import me.nlighten.backend.rest.model.LessonDTO;

/**
 * The Interface LessonMapper.
 * 
 * @author Lubo
 */
@Mapper
public interface LessonMapper {

  /**
   * From LessonDTO to Lesson, with all collections.
   *
   * @param LessonDTO the lesson dto
   * @return the lesson
   */
  @Maps
  Lesson toLesson(LessonDTO LessonDTO);

  /**
   * Update Lesson instance with LessonDTO data.
   *
   * @param LessonDTO the lesson dto
   * @param lesson the lesson
   * @return the lesson
   */
  @Maps(withIgnoreFields = {"course", "comments", "questions"})
  Lesson toLesson(LessonDTO LessonDTO, Lesson lesson);

  /**
   * From Lesson to LessonDTO, without all collections.
   *
   * @param Lesson the lesson
   * @return the lesson dto
   */
  @Maps(withIgnoreFields = {"course", "comments", "questions"})
  LessonDTO toLessonDTO(Lesson Lesson);

  /**
   * From Lesson to LessonDTO, with all collections.
   *
   * @param lesson the lesson
   * @return the lesson dto
   */
  @Maps
  LessonDTO toLessonDTOWithCollections(Lesson lesson);

  /**
   * From Lessons to LessonDTO's, without all collections.
   *
   * @param Lessons the lessons
   * @return the list
   */
  @Maps(withIgnoreFields = {"course", "comments", "questions"})
  List<LessonDTO> toLessonsDTO(List<Lesson> Lessons);
}
