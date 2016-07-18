package me.nlighten.backend.websocket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.websocket.Session;

import me.nlighten.backend.db.dao.EventDAO;
import me.nlighten.backend.db.dao.exception.DAOException;
import me.nlighten.backend.db.model.Event;

/**
 * The Class DeviceSessionHandler.
 * 
 * @author Lubo
 */
@Named("handler")
@ApplicationScoped
public class DeviceSessionHandler {

  /** The event dao. */
  @EJB
  private EventDAO eventDAO;

  /** The sessions. */
  private final Set<Session> sessions = new HashSet<Session>();

  /** The events. */
  private List<Event> events;

  /**
   * Send to all connected sessions.
   *
   * @param message the message
   * @param event the event
   * @param userId the user id
   */
  public void sendToAllConnectedSessions(Message message, String event, String userId) {
    for (Session session : sessions) {
      try {
        if (session.isOpen() && session.getUserProperties().get("event").equals(event)
            && !session.getUserProperties().get("userId").equals(userId)) {
          if (message.getReceivers() == null || message.getReceivers().isEmpty()) {
            session.getBasicRemote().sendObject(message);
          } else if (message.getReceivers() != null
              && message.getReceivers().contains(session.getUserProperties().get("userId"))) {
            session.getBasicRemote().sendObject(message);
          }
        }
      } catch (Exception e) {
        sessions.remove(session);
      }
    }
  }

  /**
   * Check and connect to event.
   *
   * @param session the session
   * @param event the event
   * @param userId the user id
   */
  public void checkAndConnectToEvent(Session session, String event, String userId) {
    try {
      Event foundEvent = eventDAO.findByTitle(event);
      Date currentDateAndTime = Calendar.getInstance().getTime();
      // check whether event exist and whether event is started
      if (currentDateAndTime.compareTo(foundEvent.getStartTime()) >= 0) {
        session.getUserProperties().put("event", event);
        session.getUserProperties().put("userId", userId);
        addSession(session);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Adds the session.
   *
   * @param session the session
   */
  public void addSession(Session session) {
    sessions.add(session);
  }

  /**
   * Removes the session.
   *
   * @param session the session
   */
  public void removeSession(Session session) {
    sessions.remove(session);
  }

  /////////////////////// test purposes ////////////////////////////////

  public void createEvent() throws ParseException {
    try {
      Event event = new Event();
      event.setTitle("myEvent" + sessions.size());
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
      String dateInString = "2016-06-26 14:00:00";
      Date date = sdf.parse(dateInString);
      event.setStartTime(date);
      eventDAO.save(event);
    } catch (DAOException e) {
      e.printStackTrace();
    }
  }

  public void findEvents() {
    try {
      events = eventDAO.findAll();
    } catch (DAOException e) {
      e.printStackTrace();
    }
  }

  public List<Event> getEvents() {
    return events;
  }
}
