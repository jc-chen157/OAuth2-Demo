package resource.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  private Logger logger = LoggerFactory.getLogger(ResourceServerConfig.class);

  @Value("${oauth2.client-id}")
  private String clientId;

  @Value("${oauth2.client-secret}")
  private String secret;

  @Value("${oauth2.check-token-access}")
  private String checkTokenEndpointUrl;

  @Bean
  public RemoteTokenServices tokenService() {
    RemoteTokenServices tokenService = new RemoteTokenServices();
    tokenService.setClientId(clientId);
    tokenService.setClientSecret(secret);
    tokenService.setCheckTokenEndpointUrl(checkTokenEndpointUrl);
    return tokenService;
  }

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources.tokenServices(tokenService());
  }
}
