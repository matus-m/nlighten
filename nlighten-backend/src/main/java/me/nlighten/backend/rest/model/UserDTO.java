package me.nlighten.backend.rest.model;

import lombok.Getter;
import lombok.Setter;
import me.nlighten.backend.db.dao.UserDAO;
import me.nlighten.backend.db.model.User;

/**
 * The Class User.
 * 
 * @see User
 * @see UserDAO
 * @author Dorin Gheorghe Brage
 */
@Getter
@Setter
public class UserDTO extends TraceAbleDTO {

  /** The username. */
  String username;

  /** The password. */
  String password;

  /** The email. */
  String email;

}
