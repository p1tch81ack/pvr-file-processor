package org.shullfamily.pvrfileprocessor;

import java.io.File;
import java.net.URL;

/**
 *
 * Created by joe on 3/13/2016.
 */
public class FileSystem {
    public static File getApplicationDirectory() {
        URL codeSourceLocation  = FileSystem.class.getProtectionDomain().getCodeSource().getLocation();
        String codeSourceLocationString = codeSourceLocation.toString();
        if(codeSourceLocationString.startsWith("file:/")) {
            String fullPath = codeSourceLocationString.substring(6);
            File fullPathFile = new File(fullPath);
            if(fullPathFile.isFile()) {
                return fullPathFile.getParentFile();
            }
            else {
                return fullPathFile;
            }
        }
        return null;
    }
}
