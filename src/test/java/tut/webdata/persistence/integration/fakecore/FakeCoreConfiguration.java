package tut.webdata.persistence.integration.fakecore;

import tut.webdata.core.services.OrderStatusUpdateService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FakeCoreConfiguration {

  @Bean OrderStatusUpdateService orderStatusUpdateService() {
    return new CountingOrderStatusService();
  }
}
