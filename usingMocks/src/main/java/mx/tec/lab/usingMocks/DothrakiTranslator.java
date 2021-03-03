package mx.tec.lab.usingMocks;

import java.util.HashMap;
import java.util.Map;

public class DothrakiTranslator {
    Map<String, String> translationMap;

    public DothrakiTranslator() {
        translationMap = new HashMap<String, String>();
    }
    public void addTranslation(final String word, final String translation){
        translationMap.put(word, translation);
    }
    public String getTranslation(final String word){
        return translationMap.get(word);
    }
}
