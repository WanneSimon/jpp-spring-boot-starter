package cc.wanforme.mswebview;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.DefaultErrorViewResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Component
@ConditionalOnProperty(value = "jpp-ms-webview.custom-error-resolver", havingValue = "false")
public class JppErrorViewResolver extends DefaultErrorViewResolver {

    private MsWebviewProperties appProperties;

    public JppErrorViewResolver(ApplicationContext applicationContext, WebProperties.Resources resources,
                                MsWebviewProperties appProperties) {
        super(applicationContext, resources);
        this.appProperties = appProperties;
    }

    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        ModelAndView modelAndView = super.resolveErrorView(request, status, model);
        if(modelAndView!=null){
            return modelAndView;
        } else {
            String originPath = model.get("path").toString();
            // 处理特定url的 404 异常
            if(status == HttpStatus.NOT_FOUND &&
                    originPath.startsWith(appProperties.getFrontBaseUrl())) {
                return new ModelAndView(appProperties.getFrontIndexHtml(), model);
            }
        }
        return null;
    }

}
