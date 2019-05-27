package chenbo.work.crm.configuration.shiro;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @className ShiroProperties
 * @authtor ChenBo
 * @date 2019/5/27
 */
@ConfigurationProperties(prefix = "spring.shiro")
@Data
public class ShiroProperties {
    /** redis地址 */
    private String redisHost;
    /** redis端口 */
    private Integer redisPort;
    /** redis链接超时时间 */
    private Integer redisTimeout;
    /** redis密码 */
    private String redisPassword;
}
