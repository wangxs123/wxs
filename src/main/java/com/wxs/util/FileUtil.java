package com.wxs.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件工具类
 *
 * @author: wxs
 * @date: 2019/05/29 10:28
 */
public class FileUtil {

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        String fileUrl=filePath + File.separator + fileName;
        FileOutputStream out = new FileOutputStream(filePath + File.separator + fileName);
        out.write(file);
        out.flush();
        out.close();
        Runtime.getRuntime().exec("chmod 777 -R " + fileUrl);
    }

    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();

            for (int i = 0; i < children.length; ++i) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        return dir.delete();
    }

    public static List<String> getFileList(String fileDir) {
        List<String> list = new ArrayList();
        File file = new File(fileDir);
        if (file.exists() && file.isDirectory()) {
            File[] array = file.listFiles();

            for(int i = 0; i < array.length; ++i) {
                if (array[i].isFile()) {
                    list.add(array[i].getName());
                }
            }
        }

        return list;
    }
}
