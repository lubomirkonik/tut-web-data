package tut.webdata.core.services;


import tut.webdata.events.orders.OrderStatusEvent;
import tut.webdata.events.orders.SetOrderStatusEvent;

public interface OrderStatusUpdateService {

  OrderStatusEvent setOrderStatus(SetOrderStatusEvent orderStatusEvent);

}
