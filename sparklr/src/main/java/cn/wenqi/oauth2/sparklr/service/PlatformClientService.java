package cn.wenqi.oauth2.sparklr.service;

import cn.wenqi.oauth2.sparklr.model.PlatformClient;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

/**
 * @author wenqi
 */
public interface PlatformClientService extends ClientDetailsService {

    PlatformClient loadClientByClientId(String clientId) throws ClientRegistrationException;
}
