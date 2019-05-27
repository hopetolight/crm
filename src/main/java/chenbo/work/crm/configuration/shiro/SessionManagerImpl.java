package chenbo.work.crm.configuration.shiro;

import chenbo.work.crm.constants.AuthContants;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 自定义sessionId获取
 * @className SessionManagerImpl
 * @authtor ChenBo
 * @date 2019/5/27
 */
public class SessionManagerImpl extends DefaultWebSessionManager {
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {

        String sessionId = WebUtils.toHttp(request).getHeader(AuthContants.AUTHORIZATION);
        if (StringUtils.isBlank(sessionId)) return super.getSessionId(request, response);
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,AuthContants.REFERENCED_SESSION_ID_SOURCE);
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
        return sessionId;
    }
}
