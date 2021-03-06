package tut.webdata.config;

import tut.webdata.persistence.repository.OrderStatusRepository;
import tut.webdata.persistence.services.StatusUpdateGemfireNotificationListener;
import org.springframework.context.annotation.*;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ImportResource({"classpath:gemfire/client.xml"})
@EnableTransactionManagement
@EnableGemfireRepositories(basePackages = "tut.webdata.persistence.repository",
    includeFilters = @ComponentScan.Filter(value = {OrderStatusRepository.class}, type = FilterType.ASSIGNABLE_TYPE))
public class GemfireConfiguration {

  @Bean public StatusUpdateGemfireNotificationListener statusUpdateListener() {
    return new StatusUpdateGemfireNotificationListener();
  }

}
