package br.com.alex.reactivex.example;

import io.reactivex.Observable;
import io.reactivex.subjects.ReplaySubject;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Api {

  @Autowired
  private ReplaySubject<Event> healthSubject;

  @RequestMapping(value = "/event", method = RequestMethod.POST)
  public String addEvent(@RequestBody Event event) {
    System.out.println(String.format("Event %s with status %s", event.getMessage(), event.getStatus()));
    healthSubject.onNext(event);
    return "ok";
  }

  @RequestMapping(value = "/events", method = RequestMethod.GET)
  public List<Event> getEvents() {
    return Observable
            .fromArray(healthSubject.getValues())
            .defaultIfEmpty(new Event("Default message", "info"))
            .cast(Event.class)
            .toList()
            .blockingGet();
  }

}
