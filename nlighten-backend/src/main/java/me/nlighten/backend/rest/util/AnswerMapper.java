package me.nlighten.backend.rest.util;

import java.util.List;

import fr.xebia.extras.selma.Mapper;
import fr.xebia.extras.selma.Maps;
import me.nlighten.backend.db.model.Answer;
import me.nlighten.backend.rest.model.AnswerDTO;

/**
 * The Interface DTOAnswerMapper.
 * 
 * @author Lubo
 */
@Mapper
public interface AnswerMapper {

  /**
   * From AnswerDTO to Answer, with all collections.
   *
   * @param answerDTO the answer dto
   * @return the answer
   */
  Answer toAnswer(AnswerDTO answerDTO);

  /**
   * Update Answer instance with AnswerDTO data.
   *
   * @param answerDTO the answer dto
   * @param answer the answer
   * @return the answer
   */
  @Maps(withIgnoreFields = {"question"})
  Answer toAnswer(AnswerDTO answerDTO, Answer answer);

  /**
   * From Answer to AnswerDTO, without all collections.
   *
   * @param answer the answer
   * @return the answer dto
   */
  @Maps(withIgnoreFields = {"question"})
  AnswerDTO toAnswerDTO(Answer answer);

  /**
   * From Answers to AnswerDTO's, without all collections.
   *
   * @param answers the answers
   * @return the list
   */
  @Maps(withIgnoreFields = {"question"})
  List<AnswerDTO> toAnswersDTO(List<Answer> answers);
}
