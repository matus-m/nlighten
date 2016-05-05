package me.nlighten.backend.rest.util;

import java.util.List;

import fr.xebia.extras.selma.Mapper;
import fr.xebia.extras.selma.Maps;
import me.nlighten.backend.db.model.Comment;
import me.nlighten.backend.rest.model.CommentDTO;

/**
 * The Interface DTOCommentMapper.
 * 
 * @author Lubo
 */
@Mapper
public interface CommentMapper {

  /**
   * From CommentDTO to Comment, with all collections.
   *
   * @param commentDTO the comment dto
   * @return the comment
   */
  @Maps
  Comment toComment(CommentDTO commentDTO);

  /**
   * Update Comment instance with CommentDTO data.
   *
   * @param commentDTO the comment dto
   * @param comment the comment
   * @return the comment
   */
  @Maps(withIgnoreFields = {"course", "lesson"})
  Comment toComment(CommentDTO commentDTO, Comment comment);

  /**
   * From Comment to CommentDTO, without all collections.
   *
   * @param comment the comment
   * @return the comment dto
   */
  @Maps(withIgnoreFields = {"course", "lesson"})
  CommentDTO toCommentDTO(Comment comment);

  /**
   * From Comments to CommentDTO's, without all collections.
   *
   * @param comments the comments
   * @return the list
   */
  @Maps(withIgnoreFields = {"course", "lesson"})
  List<CommentDTO> toCommentsDTO(List<Comment> comments);
}
