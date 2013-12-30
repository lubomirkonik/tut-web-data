package tut.webdata.core.services;

import tut.webdata.core.services.OrderStatusUpdateService;
import tut.webdata.events.orders.OrderStatusEvent;
//import tut.webdata.events.orders.RequestOrderStatusEvent;
import tut.webdata.events.orders.SetOrderStatusEvent;
//import tut.webdata.persistence.services.OrderPersistenceService;

//  implementacia OrderStatusUpdateService
public class OrderStatusUpdateEventHandler implements OrderStatusUpdateService {
//  private OrderPersistenceService orderPersistenceService; //final
  
//  public OrderStatusUpdateEventHandler(
//		OrderPersistenceService orderPersistenceService) { //final
//	this.orderPersistenceService = orderPersistenceService;
//  }

  @Override
  public OrderStatusEvent setOrderStatus(SetOrderStatusEvent orderStatusEvent) {
//  nie je potrebne z gemfire vytiahnut data	  
//	OrderStatusEvent event = orderPersistenceService.requestOrderStatus(new RequestOrderStatusEvent(orderStatusEvent.getKey()));  
//    return event;
    return new OrderStatusEvent(orderStatusEvent.getKey(), orderStatusEvent.getOrderStatus());
  }
}
