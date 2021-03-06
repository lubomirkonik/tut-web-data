package tut.webdata.events.orders;

import tut.webdata.events.UpdateEvent;

import java.util.UUID;

public class SetOrderStatusEvent extends UpdateEvent {

  private UUID key;
  private OrderStatusDetails orderStatus;

  public SetOrderStatusEvent(UUID key, OrderStatusDetails orderStatus) {
    this.key = key;
    this.orderStatus = orderStatus;
  }

  public UUID getKey() {
    return key;
  }

  public OrderStatusDetails getOrderStatus() {
    return orderStatus;
  }
}
