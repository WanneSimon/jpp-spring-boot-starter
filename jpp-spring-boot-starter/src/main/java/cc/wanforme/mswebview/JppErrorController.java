package cc.wanforme.mswebview;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/error")
@ConditionalOnProperty(value = "jpp-ms-webview.custom-error-controller", havingValue = "true")
public class JppErrorController extends BasicErrorController  {

    private MsWebviewProperties appProperties;

    public JppErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties,
                              MsWebviewProperties appProperties) {
        super(errorAttributes, errorProperties);
        this.appProperties = appProperties;
    }

    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = getStatus(request);
        Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(
                request, getErrorAttributeOptions(request, MediaType.TEXT_HTML)));
        response.setStatus(status.value());

        ModelAndView modelAndView = resolveErrorView(request, response, status, model);
        if(modelAndView!=null){
            return modelAndView;
        }else {
            String originPath = model.get("path").toString();
            // 处理特定url的 404 异常
            if(status == HttpStatus.NOT_FOUND &&
                    originPath.startsWith(appProperties.getFrontBaseUrl())) {
                return new ModelAndView(appProperties.getFrontIndexHtml(), model);
            }
        }

       return super.errorHtml(request, response);
    }

}
