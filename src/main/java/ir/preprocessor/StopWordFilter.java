package ir.preprocessor;

import ir.common.Config;
import ir.model.Store;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StopWordFilter {

    public static void filterStopWords(Map<String, Store> documentMap) {
        Set<String> stopWords = new HashSet<>();

        if (Config.getInstance().isStemmerEnabled()) {
            for (String str : Config.getInstance().getStemmedStopWords()) {
                stopWords.add(Stemmer.stem(str));
            }
        } else {
            stopWords = Config.getInstance().getStemmedStopWords();
        }

        if (Config.getInstance().isStopWordFilterEnabled()) {
            for (String word : stopWords) {
                documentMap.remove(word);
            }
        }
    }

    public static boolean filterStopWords(String token) {
        return Config.getInstance().isStopWordFilterEnabled() && Config.getInstance().getStopWords().contains(token);
    }
}
