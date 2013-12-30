package tut.webdata.events.orders;

import tut.webdata.events.RequestReadEvent;

import java.util.UUID;

public class RequestOrderStatusEvent extends RequestReadEvent {
  private UUID key;

  public RequestOrderStatusEvent(UUID key) {
    this.key = key;
  }

  public UUID getKey() {
    return key;
  }
}
