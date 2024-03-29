## jpp-spring-boot-starter

### 概要说明
`webview2`,  `jdk17+`,  `node v18.17.1`  

基于 `ms webview2` 和 `webview_java` 桌面混合开发框架，嵌入 `spring-boot` 的 `web` 能力。  
用 `spring-bbot web` 模块托管桌面UI。因此此方案的本质是 **解决 `vue` 项目打包后整合到 `spring-boot` 中**。  

至于为什么要用 `vue3` 做UI，当然是因为原生 `html` 开发UI太难受了。

### 前端
推荐 `vite` + `vue3` ，前端必须设置根路径，前端根路径很重要，`spring-boot` 根据此路径区分前端和后端。  
建议直接使用 `jpp-spring-example` ，省去各种折腾。  

如果手动创建前端项目， 参考 `jpp-spring-boot-starter` ， `.env.**` 文件中 `VITE_FRONT_BASE` 是前端根路径，参考此配置的用法。  
#### `VITE_FRONT_BASE` 的作用
1. 创建路由时，设置前端根路径；
``` js
const basePath = import.meta.env.VITE_FRONT_BASE

const router = createRouter({
  history: createWebHistory(basePath),
  routes: [ 
    //... 
  ]
})
```
2. 设置打包后的 url 根路径。 `vite.config.ts` :
``` js
export default ({mode}) => {
  const env = loadEnv(mode, process.cwd())

  // 获取定义的前端根路径
  let frontBase = env.VITE_FRONT_BASE
  frontBase = frontBase ? frontBase : "/" 
  if(!frontBase.endsWith('/')) {
    frontBase += "/" 
  }

  return defineConfig({
    // 如果在路由中设置了全局路径(VITE_FRONT_BASE)，这个值必须保持一致
    base: frontBase,
	// 打包配置
    build: {
        outDir: '../src/main/resources/static/dist',
		// ...
    },
	// others ...
	
  })
}
```

### 后端
导入依赖 `pom.xml`
``` xml
<dependencies>
    <dependency>
      <groupId>cc.wanforme</groupId>
      <artifactId>jpp-spring-boot-starter</artifactId>
      <version>0.0.1</version>
    </dependency>
    <!-- region webview-java libs-->
    <dependency>
        <groupId>co.casterlabs.commons</groupId>
        <artifactId>platform</artifactId>
        <version>1.7.0</version>
    </dependency>
    <dependency>
        <groupId>co.casterlabs.commons</groupId>
        <artifactId>io</artifactId>
        <version>1.7.0</version>
    </dependency>
    <dependency>
        <groupId>co.casterlabs.commons</groupId>
        <artifactId>async</artifactId>
        <version>1.7.0</version>
    </dependency>
    <dependency>
        <groupId>co.casterlabs</groupId>
        <artifactId>rson</artifactId>
        <version>1.17.9</version>
    </dependency>
    <dependency>
        <groupId>net.java.dev.jna</groupId>
        <artifactId>jna</artifactId>
        <version>5.14.0</version>
    </dependency>
    <dependency>
        <groupId>net.java.dev.jna</groupId>
        <artifactId>jna-platform</artifactId>
        <version>5.14.0</version>
    </dependency>
    <!-- endregion -->
</dependencies>
```

应用配置
``` yml
jpp-ms-webview:
  # 自定义 ErrorController
  custom-error-controller: false
  # 前端 url 根路径， 对应前端 VITE_FRONT_BASE
  front-base-url: /jpp
  # 前端打包文件位置 （/src/main/resources/static/dist/）
  front-base-dir: /dist
  # 前端主文件 /src/main/resources/static/dist/index.html
  front-index-html: ${jpp-ms-webview.front-base-dir}/index.html
  # 前端首页url
  front-index-url: ${jpp-ms-webview.front-base-url}/index
```

关于 `jpp-ms-webview.front-index-url` 需要说明的是，上面的配置是打包后的配置，开发时填写前端 `vite` 启动的地址
``` yml
jpp-ms-webview:
  # ...
  # 前端首页url
  # after package build ； 前端打包后
  #front-index-url: ${jpp-ms-webview.front-base-url}/index
  # development ；开发环境
  front-index-url: http://localhost:8042${jpp-ms-webview.front-base-url}/index
```

### 运行打包程序
程序打包后，运行前需要安装 `webview2` 和 `jdk`  

`webview2` 在微软官网上可以下载 `windows` 版本的（win11系统自带，不需要手动安装）。 [下载](https://developer.microsoft.com/zh-cn/microsoft-edge/webview2/?form=MT00IS#download)  [download](https://developer.microsoft.com/en-us/microsoft-edge/webview2/?form=MA13LH#download)  

`*nix` 系统参考 [webview2](https://github.com/webview/webview?tab=readme-ov-file#prerequisites)

