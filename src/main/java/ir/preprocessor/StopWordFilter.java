package ir.preprocessor;

import ir.common.Config;
import ir.model.Store;

import java.util.Map;
import java.util.Set;

public class StopWordFilter {

    public static void filterStopWords(Map<String, Store> documentMap) {
        Set<String> stopWords = Config.getInstance().getStopWords();

        if (Config.getInstance().isStopWordFilterEnabled()) {
            for (String word : stopWords) {
                documentMap.remove(word);
            }
        }
    }
}
