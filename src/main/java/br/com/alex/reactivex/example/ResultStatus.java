package br.com.alex.reactivex.example;

import java.util.List;

public class ResultStatus {

  public String status;
  public String reason;
  public List<Event> events;

  public ResultStatus() {
  }

  public ResultStatus(String status, List<Event> events, String reason) {
    this.status = status;
    this.events = events;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<Event> getEvents() {
    return events;
  }

  public void setEvents(List<Event> events) {
    this.events = events;
  }
}
