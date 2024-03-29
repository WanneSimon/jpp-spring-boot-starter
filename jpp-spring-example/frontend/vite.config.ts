// import { fileURLToPath, URL } from 'node:url'
import { defineConfig, loadEnv } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import {resolve } from "path"
import viteCompression from 'vite-plugin-compression'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'

const WEB_PORT = process.env.port || process.env.npm_config_port || '8042' // dev port

// https://vitejs.dev/config/
// https://vitejs.cn/config/#config-intellisense
export default ({mode}) => {
  // 加载环境变量
  const env = loadEnv(mode, process.cwd())
  // console.log("env", env)

  // 获取定义的前端根路径
  let frontBase = env.VITE_FRONT_BASE
  frontBase = frontBase ? frontBase : "/" 
  if(!frontBase.endsWith('/')) {
    frontBase += "/" 
  }

  return defineConfig({
    // 如果在路由中设置了全局路径(VITE_FRONT_BASE)，这个值必须保持一致
    // base: '/coco/', 
    base: frontBase,
    plugins: [
      vue(),
      vueJsx(),
      viteCompression(),

      AutoImport({
        resolvers: [
        ],
      }),
      Components({
        resolvers: [ 
        ],
      }),
    ],
    resolve: {
      alias: {
        // '@': fileURLToPath(new URL('./src', import.meta.url)),
        '@': resolve('src') // 将"/@"设置为src目录的别名
      }
    },
    css: {
      preprocessorOptions: {
        scss: {
          // additionalData: `@use "~/styles/element/index.scss" as *;`,
        },
      },
    },
    // 强制预构建插件包
    optimizeDeps: {
      include: ['axios'], 
    },
    // 打包配置
    build: {
        target: 'modules', // 设置最终构建的浏览器兼容目标。modules:支持原生 ES 模块的浏览器
        //outDir: 'dist', // 指定输出路径
        outDir: '../src/main/resources/static/dist',
        assetsDir: 'assets', // 指定生成静态资源的存放路径
        sourcemap: false, // 构建后是否生成 source map 文件
        minify: 'terser' // 混淆器，terser构建后文件体积更小
    },
    // 本地运行配置，及反向代理配置
    server: {
      host: 'localhost', // 指定服务器主机名
      port: parseInt(WEB_PORT), // 指定服务器端口
      open: false, // 在服务器启动时自动在浏览器中打开应用程序
      strictPort: false, // 设为 false 时，若端口已被占用则会尝试下一个可用端口,而不是直接退出
      https: false, // 是否开启 https
      cors: true, // 为开发服务器配置 CORS。默认启用并允许任何源
      // proxy: { // 为开发服务器配置自定义代理规则
      //     [env.VITE_API_HOST]: {
      //         target: 'http://localhost:8041', //代理接口
      //         changeOrigin: true,
      //         rewrite: (path) => path.replace(/^\/api/, '')
      //     }
      // }
    }
  })
}


