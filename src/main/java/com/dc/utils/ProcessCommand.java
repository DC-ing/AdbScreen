package com.dc.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessCommand {

    static Process p;

    /**
     * 在终端上运行命令，用于 Android 和 iOS 获取信息
     *
     * @param command 运行命令
     *
     * @return 执行命令后，所产生的日志
     *
     * @throws IOException IO异常
     */
    public String runCommand(String command) throws IOException {
        p = Runtime.getRuntime().exec(command);
        //命令执行后，获得输出日志
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        String allLine = "";
        while ((line = r.readLine()) != null) {
            allLine = allLine + "" + line + "\n";
            if (line.contains("Console LogLevel: debug") && line.contains("Complete")) {
                break;
            }
        }
        return allLine;
    }
}

