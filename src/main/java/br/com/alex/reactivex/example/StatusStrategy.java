package br.com.alex.reactivex.example;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StatusStrategy {

  public static ResultStatus getResultStatus(ResultStatus resultStatus, Event event) {
    ResultStatus tempStatus = new ResultStatus();
    tempStatus.setStatus(event.getStatus());
    tempStatus.setEvents(List.of(event));

    if(!resultStatus.getEvents().isEmpty()) {
      return mergeStatus(resultStatus, tempStatus);
    } else {
      return tempStatus;
    }
  }

  private static ResultStatus mergeStatus(ResultStatus resultStatus, ResultStatus tempStatus) {
    List<Event> mergedList = List.of(resultStatus.getEvents(), tempStatus.getEvents())
            .stream()
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

    System.out.println(Objects.toString(mergedList));
    if (Objects.equals(resultStatus.getStatus(), "green")) {
      if (!Objects.equals(tempStatus.getStatus(), "green")) {
        return new ResultStatus("yellow", mergedList, "recent errors occurred");
      }
    }

    return new ResultStatus(tempStatus.getStatus(), mergedList, tempStatus.getReason());
  }
}
