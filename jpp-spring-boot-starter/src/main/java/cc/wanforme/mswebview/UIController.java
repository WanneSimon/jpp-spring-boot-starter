package cc.wanforme.mswebview;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UIController {

    private MsWebviewProperties appProperties;

    public UIController(MsWebviewProperties appProperties) {
        this.appProperties = appProperties;
    }

    @GetMapping("/ms-hi")
    @ResponseBody
    public String hello() {
        return "hello";
    }

    /** 此请求用于映射前端的静态资源，前端url最终会404，然后交给 ErrorController 处理，并重定向到前端首页 */
    @GetMapping("${jpp-ms-webview.front-base-url}/**")
    public void appRoot(HttpServletRequest request, HttpServletResponse response) throws Exception {
          String loc = request.getRequestURI().substring(appProperties.getFrontBaseUrl().length());
          String staticLoc = appProperties.getFrontBaseDir() + loc;
          request.getRequestDispatcher(staticLoc).forward(request, response);
    }

}
