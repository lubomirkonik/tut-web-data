package tut.webdata.persistence.repository;

import tut.webdata.persistence.domain.Order;

import java.util.UUID;

public interface OrdersRepositoryWebapp {

  Order save(Order order);

  void delete(UUID key);

  Order findById(UUID key);

  Iterable<Order> findAll();
}
