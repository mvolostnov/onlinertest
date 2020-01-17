package by.onliner.test;

import lombok.extern.log4j.Log4j;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


@Log4j
public class PropertyLoader {

    public String getProperty(String filePath, String key) throws IOException {
        InputStream inputStream = null;
        try {
            Properties appProps = new Properties();
            inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
            if (inputStream != null) {
                appProps.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + filePath + "' not found in the classpath");
            }
 //           Date time = new Date(System.currentTimeMillis());
            // get the property value and print it out
            return appProps.getProperty(key);
        } catch (Exception e) {
            log.error("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return null;
    }
}