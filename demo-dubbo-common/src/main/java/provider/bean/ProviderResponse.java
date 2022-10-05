package provider.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: star
 * @time: 2021/8/16 20:15
 */
@Data
public class ProviderResponse implements Serializable {
    private static final long serialVersionUID = 1956372163374483580L;
    private String result;
}
