package cn.wenqi.oauth2.sparklr.service.impl;

import cn.wenqi.oauth2.sparklr.model.PlatformClient;
import cn.wenqi.oauth2.sparklr.repository.PlatformClientRepository;
import cn.wenqi.oauth2.sparklr.service.PlatformClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 * @author wenqi
 */
@Service
public class PlatformClientServiceImpl implements PlatformClientService {

    @Autowired
    private PlatformClientRepository platformClientRepository;

    public PlatformClient loadClientByClientId(String clientId) throws ClientRegistrationException {
        return platformClientRepository.findByCid(clientId);
    }
}
