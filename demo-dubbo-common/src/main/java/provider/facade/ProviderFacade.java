package provider.facade;


import provider.bean.ProviderRequest;
import provider.bean.ProviderResponse;

import javax.jws.WebService;

@WebService
public interface ProviderFacade {
    public ProviderResponse provider(ProviderRequest req);
}
