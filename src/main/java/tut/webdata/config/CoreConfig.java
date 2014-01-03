package tut.webdata.config;

import tut.webdata.core.services.MenuEventHandler;
import tut.webdata.core.services.MenuService;
import tut.webdata.core.services.OrderEventHandler;
import tut.webdata.core.services.OrderService;
import tut.webdata.core.services.OrderStatusUpdateEventHandler;
import tut.webdata.core.services.OrderStatusUpdateService;
import tut.webdata.persistence.services.MenuPersistenceService;
import tut.webdata.persistence.services.OrderPersistenceService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreConfig {
	@Bean
	public MenuService menuService(MenuPersistenceService menuPersistenceService) {
		return new MenuEventHandler(menuPersistenceService);
	}
	@Bean
	public OrderService orderService(OrderPersistenceService orderPersistenceService) {
		return new OrderEventHandler(orderPersistenceService);
	}
	
	@Bean
	public OrderStatusUpdateService orderStatusUpdateService() {
		return new OrderStatusUpdateEventHandler();
	}
}
