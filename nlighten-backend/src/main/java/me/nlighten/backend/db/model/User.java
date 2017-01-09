package me.nlighten.backend.db.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * The User class.
 * 
 * @author Dorin Gheorghe Brage
 */

@Getter
@Setter
@Entity
@Table(name = "USER")
public class User extends TraceAble {

  /** The username. */
  String username;

  /** The password. */
  String password;

  /** The email. */
  String email;

  /** The list of events create by the user **/
  @OneToMany(mappedBy = "owner")
  List<Event> events = new ArrayList<Event>();

  /** The list of subscribed events */
  @ManyToMany(mappedBy = "participants")
  List<Event> suscribedEvents = new ArrayList<Event>();
  
  @OneToMany(mappedBy = "author")
  List<Answer> answers = new ArrayList<Answer>();
  
  @OneToMany(mappedBy = "author")
  List<Comment> comments = new ArrayList<Comment>();
  

}
