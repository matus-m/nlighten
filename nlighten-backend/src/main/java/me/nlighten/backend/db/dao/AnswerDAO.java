package me.nlighten.backend.db.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import me.nlighten.backend.db.model.Answer;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AnswerDAO extends GenericDAO<Answer> {
}
