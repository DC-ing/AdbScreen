# AdbScreen

 macOS 电脑上，对已连接的 Android 设备截图的小工具

 ## 安装环境
 1. 搭建 Java 开发环境
 2. 搭建 Android-SDK 开发环境

 ## 使用方法
### 1. 源码打包后，运行`jar`包即可。

 ```
java -jar AdbScreen-1.0.jar
 ```
 
 ### 2. 使用 shell 脚本简单封装 java 命令
 编写一个简单的 Shell 脚本，简化输入命令。

 首先，将打包好的`jar`移动到指定的目录中，在同一个目录下，创建一个 shell 脚本文件(AdbScreen.sh)，如下所示

 ```
#!/bin/sh

java -jar AdbScreen.jar

 ```

 终端进入到此目录下，使用命令`./AdbScreen.sh`即可