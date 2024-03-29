package cc.wanforme.mswebview;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class UIServerHolder implements ApplicationListener<WebServerInitializedEvent> {
    private WebServer webServer;

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        webServer = event.getWebServer();
    }

    public WebServer getWebServer() {
        return webServer;
    }

    public int getPort() {
        return webServer.getPort();
    }

}
