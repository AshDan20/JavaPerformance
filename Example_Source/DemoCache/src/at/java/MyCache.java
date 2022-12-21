package at.java;

import java.util.HashMap;
import java.util.Map;

public class MyCache {
    volatile private Map<String,String> map = new HashMap<>();

    public String read(String key){
        synchronized (this) {
            return map.get(key);
        }
    }

    public void refreshCache(){
        synchronized (this) {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("eins", "eins");
            tempMap.put("eins", "eins");

            map = tempMap;
        }
    }

}
