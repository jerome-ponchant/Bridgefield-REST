package fr.bridgefield.brr.services.localization;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class LocalizationMessageConfig {

	
	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	
	LocalizationService messageService() {
		return new MessageLocalizationService();
	}
}
