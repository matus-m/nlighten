package me.nlighten.backend.websocket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.websocket.Session;

import me.nlighten.backend.db.dao.EventDAO;
import me.nlighten.backend.db.dao.exception.DAOException;
import me.nlighten.backend.db.model.Event;

@Named("handler")
@ApplicationScoped
public class DeviceSessionHandler {

  @EJB
  private EventDAO eventDAO;

  private final Set<Session> sessions = new HashSet<Session>();

  private List<Event> events;

  public void sendToAllConnectedSessions(Message message, String event) {
    for (Session session : sessions) {
      try {
        if (session.isOpen() && session.getUserProperties().get("event").equals(event)) {
          session.getBasicRemote().sendObject(message);
        }
      } catch (Exception e) {
        sessions.remove(session);
      }
    }
  }

  public void checkAndConnectToEvent(Session session, String event) {
    try {
      Event foundEvent = eventDAO.findByTitle(event);
      Date currentDateAndTime = Calendar.getInstance().getTime();
      // check whether event exist and whether event is started
      if (currentDateAndTime.compareTo(foundEvent.getStartTime()) >= 0) {
        session.getUserProperties().put("event", event);
        session.getUserProperties().put("userId", UUID.randomUUID().toString());
        addSession(session);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void addSession(Session session) {
    sessions.add(session);
  }

  public void removeSession(Session session) {
    sessions.remove(session);
  }

  /////////////////////// test purposes ////////////////////////////////

  public void createEvent() throws ParseException {
    try {
      Event event = new Event();
      event.setTitle("myEvent" + Math.random());
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
