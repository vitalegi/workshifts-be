package it.vitalegi.workshifts.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import it.vitalegi.workshifts.optimizer.WorkShiftOptimizer;

@Configuration
public class SpringConfig {

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public WorkShiftOptimizer optimizer() {
		return new WorkShiftOptimizer();
	}
}
