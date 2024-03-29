package cc.wanforme.mswebview;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jpp-ms-webview")
public class MsWebviewProperties {
    private boolean customErrorController;
    private String frontBaseUrl;
    private String frontBaseDir;
    private String frontIndexHtml;
    private String frontIndexUrl;

    public void setCustomErrorController(boolean customErrorController) {
        this.customErrorController = customErrorController;
    }

    public boolean isCustomErrorController() {
        return customErrorController;
    }

    public String getFrontBaseUrl() {
        return frontBaseUrl;
    }

    public void setFrontBaseUrl(String frontBaseUrl) {
        this.frontBaseUrl = frontBaseUrl;
    }

    public String getFrontBaseDir() {
        return frontBaseDir;
    }

    public void setFrontBaseDir(String frontBaseDir) {
        this.frontBaseDir = frontBaseDir;
    }

    public String getFrontIndexHtml() {
        return frontIndexHtml;
    }

    public void setFrontIndexHtml(String frontIndexHtml) {
        this.frontIndexHtml = frontIndexHtml;
    }

    public String getFrontIndexUrl() {
        return frontIndexUrl;
    }

    public void setFrontIndexUrl(String frontIndexUrl) {
        this.frontIndexUrl = frontIndexUrl;
    }
}
