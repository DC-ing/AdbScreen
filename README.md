# AdbScreen

macOS 电脑上，对已连接的 Android 设备截图的小工具

## 安装环境
 1. 搭建 Java 开发环境
 2. 搭建 Android-SDK 开发环境

## 使用方法
### 1. 源码打包后，运行`jar`包即可。

 ```
java -jar AdbScreen-*.jar
 ```
 
### 2. 使用 shell 脚本运行
将 jar 和 shell 脚本（在项目源码根目录）下载到同一个目录中，使用命令修改 shell 脚本的执行权限，然后运行即可。
 
 ```
 $ chmod +x AdbScreen.sh
 $ ./AdbScreen.sh
 ```


