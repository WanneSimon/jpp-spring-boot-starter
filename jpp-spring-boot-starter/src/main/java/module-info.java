module jpp.spring.boot.starter {
    requires org.apache.tomcat.embed.core;
    requires spring.beans;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.web;
    requires spring.webmvc;

    opens cc.wanforme.mswebview;
}