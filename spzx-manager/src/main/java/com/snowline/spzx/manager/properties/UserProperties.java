package com.snowline.spzx.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Snow
 * @create 2024-01-14 2:23
 */

@Data
@ConfigurationProperties(prefix = "spzx.auth")
public class UserProperties {

    private List<String> noAuthUrls;

}
