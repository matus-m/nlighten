package me.nlighten.backend.db.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import me.nlighten.backend.db.model.Comment;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CommentDAO extends GenericDAO<Comment> {
}
