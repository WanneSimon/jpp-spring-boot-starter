package cc.wanforme.mswebview;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JppErrorConfiguration {

    @Bean
    @ConditionalOnMissingBean(ErrorProperties.class)
    public ErrorProperties errorProperties() {
        return new ErrorProperties();
    }
}
