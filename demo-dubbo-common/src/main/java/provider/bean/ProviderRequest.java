package provider.bean;

import lombok.Data;
import provider.common.AbstractRequest;

/**
 * @description:
 * @author: star
 * @time: 2021/8/16 20:14
 */
@Data
public class ProviderRequest extends AbstractRequest {
    private String name;
}
