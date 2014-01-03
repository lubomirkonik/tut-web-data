package tut.webdata.web.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tut.webdata.core.services.OrderService;
import tut.webdata.events.orders.OrderDetailsEvent;
import tut.webdata.events.orders.OrderStatusEvent;
import tut.webdata.events.orders.RequestOrderDetailsEvent;
import tut.webdata.events.orders.RequestOrderStatusEvent;
import tut.webdata.persistence.repository.OrderStatusRepository;
import tut.webdata.web.domain.OrderStatus;

@Controller
@RequestMapping("/order/{orderId}")
public class OrderStatusController {

	private static final Logger LOG = LoggerFactory
			.getLogger(OrderStatusController.class);

	@Autowired
	private OrderService orderService;
	
	//fixture
	@Autowired
	private OrderStatusRepository orderStatusRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String orderStatus(@ModelAttribute("orderStatus") OrderStatus orderStatus) {
		LOG.debug("Get order status for order id {} customer {}", orderStatus.getOrderId(), orderStatus.getName());
		return "/order";
	}

	@ModelAttribute("orderStatus")
	private OrderStatus getOrderStatus(@PathVariable("orderId") String orderId) {
		OrderDetailsEvent orderDetailsEvent = orderService.requestOrderDetails(new RequestOrderDetailsEvent(UUID.fromString(orderId)));
		OrderStatusEvent orderStatusEvent = orderService.requestOrderStatusByOrderId(new RequestOrderStatusEvent(UUID.fromString(orderId)));

		OrderStatus status = new OrderStatus();
		status.setName(orderDetailsEvent.getOrderDetails().getName());
		status.setOrderId(orderId);
		status.setStatus(orderStatusEvent.getOrderStatus().getStatus());
		
		return status;
	}
}
