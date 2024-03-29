package cc.wanforme.mswebview;

import dev.webview.webview_java.Webview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class WebviewLauncher implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(WebviewLauncher.class);

    private MsWebviewProperties properties;
    private UIServerHolder uiServerHolder;

    @Value("${server.ssl.enabled:false}")
    private boolean sslEnabled;

    @Override
    public void run(String... args) throws Exception {
        startWebview();
    }

    public void startWebview() {
        Webview wv = new Webview(true, 800, 600);

        String url = properties.getFrontIndexUrl();
        if (!url.startsWith("http")) {
            int port = uiServerHolder.getPort();
            String schema = sslEnabled ? "https://" : "http://";
            url = String.format("%s127.0.0.1:%s",schema,  port) + url;
        }

        LOG.info("frontend - url: " + url);
        wv.loadURL(url);
        wv.run();
        wv.close();
    }

    @Autowired
    public void setProperties(MsWebviewProperties properties) {
        this.properties = properties;
    }
    @Autowired
    public void setUiServerHolder(UIServerHolder uiServerHolder) {
        this.uiServerHolder = uiServerHolder;
    }
}
