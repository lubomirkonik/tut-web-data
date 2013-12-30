package tut.webdata.persistence.services;

import com.gemstone.gemfire.cache.query.CqEvent;
import tut.webdata.core.services.OrderStatusUpdateService;
import tut.webdata.events.orders.SetOrderStatusEvent;
import tut.webdata.persistence.domain.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;

public class StatusUpdateGemfireNotificationListener {

  @Autowired
  private OrderStatusUpdateService orderStatusUpdateService;

  public void setOrderStatusUpdateService(OrderStatusUpdateService orderStatusUpdateService) {
    this.orderStatusUpdateService = orderStatusUpdateService;
  }

  public void handleEvent(CqEvent event) {

    if (!event.getBaseOperation().isCreate()) {
      return;
    }

    OrderStatus status = (OrderStatus) event.getNewValue();

    orderStatusUpdateService.setOrderStatus(
        new SetOrderStatusEvent(status.getOrderId(),
                                status.toStatusDetails()));

  }
}
