package conf_readers;

import com.sun.source.doctree.SeeTree;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class PropertiesReader implements IConfReader {

  @Override
  public Map<String, String> read() {
    String rootDir = System.getProperty("user.dir");

    Map<String, String> prop = new HashMap<>();

    try {
      InputStream input = new FileInputStream(String.format("%s/src/resources/%s", rootDir, "db.properties"));
      Properties properties = new Properties();
      properties.load(input);

      for(Object key: properties.keySet()) {
        prop.put((String) key, (String)properties.get(key));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return prop;
  }
}
