package chenbo.work.crm.configuration.shiro;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 *
 * @className ExceptionHandlerManager
 * @authtor ChenBo
 * @date 2019/5/27
 */
public class ExceptionHandlerManager implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        FastJsonJsonView view = new FastJsonJsonView();
        HashMap<String, Object> attributes = new HashMap<>();
        if (ex instanceof UnauthenticatedException){
            attributes.put("code",1001);
            attributes.put("message","token错误");
        }else if (ex instanceof UnauthorizedException){
            attributes.put("code",1002);
            attributes.put("message",ex.getMessage());
        }
        view.setAttributesMap(attributes);
        modelAndView.setView(view);
        return modelAndView;
    }
}
