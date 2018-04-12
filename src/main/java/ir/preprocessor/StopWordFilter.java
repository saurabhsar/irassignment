package ir.preprocessor;

import ir.common.Config;
import ir.model.Store;

import java.util.Map;
import java.util.Set;

public class StopWordFilter {

    private static Set<String> stopWords;

    public static void filterStopWords(Map<String, Store> documentMap) {
        stopWords = Config.getInstance().getStopWords();

        for (String word : stopWords) {
            documentMap.remove(word);
        }
    }
}
