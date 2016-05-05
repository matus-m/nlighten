package me.nlighten.backend.rest.util;

import java.util.List;

import fr.xebia.extras.selma.Mapper;
import fr.xebia.extras.selma.Maps;
import me.nlighten.backend.db.model.Question;
import me.nlighten.backend.rest.model.QuestionDTO;

/**
 * The Interface DTOQuestionMapper.
 * 
 * @author Lubo
 */
@Mapper
public interface QuestionMapper {

  /**
   * From questionDTO to question, with all collections.
   *
   * @param questionDTO the question dto
   * @return the question
   */
  Question toQuestion(QuestionDTO questionDTO);
  
  /**
   * Update question instance with questionDTO data.
   *
   * @param questionDTO the question dto
   * @param question the question
   * @return the question
   */
  @Maps(withIgnoreFields = {"course", "lesson", "answers"})
  Question toQuestion(QuestionDTO questionDTO, Question question);

  /**
   * From question to questionDTO, without all collections.
   *
   * @param question the question
   * @return the question dto
   */
  @Maps(withIgnoreFields = {"course", "lesson", "answers"})
  QuestionDTO toQuestionDTO(Question question);
  
  /**
   * From question to questionDTO, with all collections.
   *
   * @param question the question
   * @return the question dto
   */
  QuestionDTO toQuestionDTOWithCollections(Question question);

  /**
   * From questions to questionDTO's, without all collections.
   *
   * @param questions the questions
   * @return the list
   */
  @Maps(withIgnoreFields = {"course", "lesson", "answers"})
  List<QuestionDTO> toQuestionsDTO(List<Question> questions);
}
