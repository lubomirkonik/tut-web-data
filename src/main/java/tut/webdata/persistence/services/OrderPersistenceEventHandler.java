package tut.webdata.persistence.services;

import tut.webdata.events.orders.*;
import tut.webdata.persistence.domain.Order;
import tut.webdata.persistence.domain.OrderStatus;
import tut.webdata.persistence.repository.OrderStatusRepository;
import tut.webdata.persistence.repository.OrdersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderPersistenceEventHandler implements OrderPersistenceService {

  private final OrdersRepository orderRepository;
  private final OrderStatusRepository orderStatusRepository;

  public OrderPersistenceEventHandler(
      final OrdersRepository orderRepository,
      final OrderStatusRepository orderStatusRepository) {
    this.orderRepository = orderRepository;
    this.orderStatusRepository = orderStatusRepository;
  }

  @Override
  public OrderStatusEvent setOrderStatus(SetOrderStatusEvent event) {

    OrderStatus status = OrderStatus.fromStatusDetails(event.getOrderStatus());

    status = orderStatusRepository.save(status);
    
    return new OrderStatusEvent(status.getId(), status.toStatusDetails());
  }

  @Override
  public OrderCreatedEvent createOrder(CreateOrderEvent createOrderEvent) {
    Order order = Order.fromOrderDetails(createOrderEvent.getDetails());

    order = orderRepository.save(order);
    
    //webapp: (order.getKey(), order.toOrderDetails())
    return new OrderCreatedEvent(UUID.fromString(order.getId()), order.toOrderDetails());
  }

  @Override
  public AllOrdersEvent requestAllOrders(RequestAllOrdersEvent requestAllCurrentOrdersEvent) {
    List<OrderDetails> generatedDetails = new ArrayList<OrderDetails>();
    for (Order order : orderRepository.findAll()) {
      generatedDetails.add(order.toOrderDetails());
    }
    return new AllOrdersEvent(generatedDetails);
  }
  
//	webapp
//  @Override
//  public OrderDetailsEvent requestOrderDetails(RequestOrderDetailsEvent requestOrderDetailsEvent) {
//
//    Order order = orderRepository.findById(requestOrderDetailsEvent.getKey());
//
//    if (order == null) {
//      return OrderDetailsEvent.notFound(requestOrderDetailsEvent.getKey());
//    }
//
//    return new OrderDetailsEvent(
//            requestOrderDetailsEvent.getKey(),
//            order.toOrderDetails());
//  }
  
  @Override
  public OrderDetailsEvent requestOrderDetails(RequestOrderDetailsEvent requestOrderDetailsEvent) {

    Order order = orderRepository.findOne(requestOrderDetailsEvent.getKey().toString());

    if (order == null) {
      return OrderDetailsEvent.notFound(requestOrderDetailsEvent.getKey());
    }

    return new OrderDetailsEvent(
        requestOrderDetailsEvent.getKey(),
        order.toOrderDetails());
  }  

//  webapp  
//  @Override
//  public OrderUpdatedEvent setOrderPayment(SetOrderPaymentEvent setOrderPaymentEvent) {
//    Order order = orderRepository.findById(setOrderPaymentEvent.getKey());
//
//    if(order == null) {
//      return OrderUpdatedEvent.notFound(setOrderPaymentEvent.getKey());
//    }
//
//    //TODO, handling payment details...
//
//    return new OrderUpdatedEvent(order.getKey(), order.toOrderDetails());
//  }

  @Override
  public OrderUpdatedEvent setOrderPayment(SetOrderPaymentEvent setOrderPaymentEvent) {
    Order order = orderRepository.findOne(setOrderPaymentEvent.getKey().toString());

    if(order == null) {
      return OrderUpdatedEvent.notFound(setOrderPaymentEvent.getKey());
    }

    //TODO, handling payment details...

    return new OrderUpdatedEvent(UUID.fromString(order.getId()), order.toOrderDetails());
  }  
  
//	webapp  
//  @Override
//  public OrderDeletedEvent deleteOrder(DeleteOrderEvent deleteOrderEvent) {
//
//    Order order = orderRepository.findById(deleteOrderEvent.getKey());
//
//    if (order == null) {
//      return OrderDeletedEvent.notFound(deleteOrderEvent.getKey());
//    }
//
//    orderRepository.delete(deleteOrderEvent.getKey());
//
//    return new OrderDeletedEvent(deleteOrderEvent.getKey(), order.toOrderDetails());
//  }
  
  @Override
  public OrderDeletedEvent deleteOrder(DeleteOrderEvent deleteOrderEvent) {

    Order order = orderRepository.findOne(deleteOrderEvent.getKey().toString());

    if (order == null) {
      return OrderDeletedEvent.notFound(deleteOrderEvent.getKey());
    }

    orderRepository.delete(deleteOrderEvent.getKey().toString());

    return new OrderDeletedEvent(deleteOrderEvent.getKey(), order.toOrderDetails());
  }  

//	webapp   
//  @Override
//  public OrderStatusEvent requestOrderStatus(RequestOrderStatusEvent requestOrderDetailsEvent) {
//    
//    OrderStatus orderStatus = orderStatusRepository.findLatestById(requestOrderDetailsEvent.getKey());
//
//    if (orderStatus == null) {
//      return OrderStatusEvent.notFound(requestOrderDetailsEvent.getKey());
//    }
//
//    return new OrderStatusEvent(requestOrderDetailsEvent.getKey(), orderStatus.toStatusDetails());
//  }
  
  @Override
  public OrderStatusEvent requestOrderStatus(RequestOrderStatusEvent requestOrderDetailsEvent) {
    OrderStatus status = orderStatusRepository.findOne(requestOrderDetailsEvent.getKey());

    if (status == null) {
      return OrderStatusEvent.notFound(requestOrderDetailsEvent.getKey());
    }

    return new OrderStatusEvent(requestOrderDetailsEvent.getKey(), status.toStatusDetails());
  }  
  
  @Override
  public OrderStatusEvent requestOrderStatusByOrderId(RequestOrderStatusEvent requestOrderDetailsEvent) {
	OrderStatus status = orderStatusRepository.findByOrderId(requestOrderDetailsEvent.getKey());
	
	if (status == null) {
		return OrderStatusEvent.notFound(requestOrderDetailsEvent.getKey());
	}
	
	return new OrderStatusEvent(requestOrderDetailsEvent.getKey(), status.toStatusDetails());
  }
}
