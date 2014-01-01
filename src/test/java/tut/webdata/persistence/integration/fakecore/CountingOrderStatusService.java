package tut.webdata.persistence.integration.fakecore;

import tut.webdata.core.services.OrderStatusUpdateService;
import tut.webdata.events.orders.OrderStatusEvent;
import tut.webdata.events.orders.SetOrderStatusEvent;

import java.util.concurrent.CountDownLatch;

/**
 * A testing Stub.
 * Stands in the place of the Core OrderStatusUpdateService. Allows tests to count
 * the number of update status events via a CountDownLatch, which will be set manually
 * in the test
 */
public class CountingOrderStatusService implements OrderStatusUpdateService {

  private CountDownLatch latch;

  public void setLatch(CountDownLatch latch) {
    this.latch = latch;
  }

  @Override
  public OrderStatusEvent setOrderStatus(SetOrderStatusEvent orderStatusEvent) {
    latch.countDown();
    return OrderStatusEvent.notFound(orderStatusEvent.getKey());
  }
}
