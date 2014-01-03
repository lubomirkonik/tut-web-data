package tut.webdata.core.services;

import tut.webdata.core.services.OrderStatusUpdateService;
import tut.webdata.events.orders.OrderStatusEvent;
import tut.webdata.events.orders.SetOrderStatusEvent;

public class OrderStatusUpdateEventHandler implements OrderStatusUpdateService {

  @Override
  public OrderStatusEvent setOrderStatus(SetOrderStatusEvent orderStatusEvent) {
    return new OrderStatusEvent(orderStatusEvent.getKey(), orderStatusEvent.getOrderStatus());
  }
}
