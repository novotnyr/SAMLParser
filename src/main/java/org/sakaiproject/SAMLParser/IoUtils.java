package org.sakaiproject.SAMLParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public abstract class IoUtils {
    public static String loadAsString(File file) throws FileNotFoundException, IOException {
        Scanner scanner = null;
        try {
            scanner = new Scanner(file, "UTF-8");
            String content = scanner.useDelimiter("\\A").next();
            if (scanner.ioException() != null) {
                throw scanner.ioException();
            }
            return content;
        }  finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
