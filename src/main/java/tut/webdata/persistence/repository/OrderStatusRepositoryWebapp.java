package tut.webdata.persistence.repository;

import tut.webdata.persistence.domain.OrderStatus;

import java.util.List;
import java.util.UUID;

public interface OrderStatusRepositoryWebapp {

  OrderStatus save(OrderStatus orderStatus);

  void delete(UUID key);

  OrderStatus findLatestById(UUID key);

  List<OrderStatus> findAll();
}
