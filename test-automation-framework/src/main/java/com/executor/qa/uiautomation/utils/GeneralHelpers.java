package com.executor.qa.uiautomation.utils;

import java.io.File;

public class GeneralHelpers {
    private static final String MODULE_NAME = "common.utils";

    public static String getBasePath() {
        String path = GeneralHelpers.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        return path.replaceAll("common.utils.*", "");
    }

    public static boolean isFileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }
}
