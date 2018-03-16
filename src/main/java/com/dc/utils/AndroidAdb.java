package com.dc.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AndroidAdb {
    private Logger logger = LogManager.getLogger(this.getClass());
    private ProcessCommand cmd = new ProcessCommand();

    /**
     * 开启 adb 服务
     *
     * */
    @Deprecated
    public void startADB() {
        String output = null;
        try {
            output = cmd.runCommand("adb start-server");
            logger.info("开启 adb 服务");
        } catch (IOException e) {
            logger.catching(e);
        }
        String[] lines = output.split("\n");
        if (lines[0].contains("internal or external command")) {
            logger.error("请在系统设置 ANDROID_HOME 环境变量");
        }
    }

    /**
     * 获取设备号(udid)
     *
     * @return 设备号
     *
     * */
    public List<String> getDeviceUDID() {
        ArrayList<String> deviceSerail = new ArrayList<>();
        String output;
        try {
            output = cmd.runCommand("adb devices");
            String[] lines = output.split("\n");

            if (lines.length <= 1) {
                return deviceSerail;
            } else {
                String udidForLog = "";
                int linesLength = lines.length;
                for (int i = 1; i < linesLength; i++) {
                    lines[i] = lines[i].replaceAll("\\s+", "");

                    if (lines[i].contains("device")) {
                        lines[i] = lines[i].replaceAll("device", "");
                        String deviceID = lines[i];
                        deviceSerail.add(deviceID);
                        udidForLog = udidForLog + "[" + lines[i] + "]";
                        if (i != linesLength - 1) {
                            udidForLog = udidForLog + ", ";
                        }
                    } else if (lines[i].contains("unauthorized")) {
                        lines[i] = lines[i].replaceAll("unauthorized", "");
                        String deviceID = lines[i];
                    } else if (lines[i].contains("offline")) {
                        lines[i] = lines[i].replaceAll("offline", "");
                        String deviceID = lines[i];
                    }
                }
                logger.info("获取到已连接的设备：" + udidForLog);
            }
        } catch (IOException e) {
            logger.catching(e);
        }
        return deviceSerail;
    }

    /**
     * 获取指定设备的名称
     *
     * @param deviceID 设备号
     *
     * @return 设备名称
     *
     * */
    public String getDeviceName(String deviceID) {
        String deviceName = null;
        try {
            //设备型号
            String model = cmd.runCommand("adb -s " + deviceID + " shell getprop ro.product.model")
                    .replaceAll("\\s+", "");
            //设备品牌
            String brand = cmd.runCommand("adb -s " + deviceID + " shell getprop ro.product.brand")
                    .replaceAll("\\s+", "");
            deviceName = brand + "-" + model;
            logger.info("获取到设备 [" + deviceID + "] 的名称：[" + deviceName + "]");
        } catch (IOException e) {
            logger.catching(e);
        }
        return deviceName;
    }

    /**
     * 获取设备的系统版本号
     *
     * @param deviceID 设备号
     *
     * @return 设备系统版本号
     *
     * */
    public String getDeviceOSVersion(String deviceID) {
        String deviceOSVersion = null;
        try {
            String command = "adb -s " + deviceID + " shell getprop ro.build.version.release";
            deviceOSVersion = cmd.runCommand(command).replaceAll("\\s+", "");
            logger.info("获取到设备 [" + deviceID + "] 的系统版本号为 [" + deviceOSVersion + "]");
        } catch (IOException e) {
            logger.catching(e);
        }
        return deviceOSVersion;
    }

    /**
     * 使用 adb 命令截图
     *
     * @param deviceId 设备号
     *
     */
    public void getScreenShot(String deviceId) {
        String screenFile = this.getDeviceName(deviceId) + "-" + this.getDeviceOSVersion(deviceId) + "-" + DateTimeUtils.getFileDateTime() +".png";
        String screenshotFilePath = System.getProperty("user.dir") + "/Desktop/" + screenFile;
        logger.info("截图保存路径：" + screenshotFilePath);
        String screenFileInDevice = "/sdcard/" + screenFile;
        try {
            //使用 adb 命令截图
            cmd.runCommand("adb -s " + deviceId + " shell screencap " + screenFileInDevice);
            //先创建文件
            FileUtils.createFile(screenshotFilePath);
            //将截图文件拉回到电脑
            String pullLog = cmd.runCommand("adb -s " + deviceId + " pull " + screenFileInDevice + " " + screenshotFilePath);
            //在设备清空掉刚才的截图
            cmd.runCommand("adb -s " + deviceId + " shell rm " + screenFileInDevice);
            logger.info(pullLog);
            if (pullLog.equals("error")) {
                logger.error("保存屏幕截图失败");
            } else {
                logger.info("保存屏幕截图成功");
            }
        } catch (IOException e) {
            logger.error("保存屏幕截图失败\n", e);
        }
    }

    /**
     * 使用 adb 命令截图，默认使用当前连接的设备
     *
     */
    public void getScreenShot() {
        List<String> devices = this.getDeviceUDID();
        if (devices != null && devices.size() != 0) {
            for (String deviceId : devices) {
                this.getScreenShot(deviceId);
            }
        } else {
            logger.warn("暂无连接设备");
        }
    }

}
