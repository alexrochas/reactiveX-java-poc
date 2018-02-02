package br.com.alex.reactivex.example;

import io.reactivex.Observable;
import io.reactivex.subjects.ReplaySubject;
import jersey.repackaged.com.google.common.collect.Lists;
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
  public ResultStatus getEvents() {
    return Observable
            .fromArray(healthSubject.getValues())
            .defaultIfEmpty(new Event("Default message", "info"))
            .cast(Event.class)
            .reduce(new ResultStatus("green", Lists.newArrayList(), "default message"),
                    StatusStrategy::getResultStatus)
            .cast(ResultStatus.class)
            .blockingGet();
  }

}
