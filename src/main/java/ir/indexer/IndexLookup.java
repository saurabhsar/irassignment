package ir.indexer;

import ir.common.Config;
import ir.file.Parser;
import ir.model.DocInfo;
import ir.model.SearchResults;
import ir.model.Store;
import ir.preprocessor.Stemmer;
import ir.preprocessor.StopWordFilter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static ir.indexer.Index.documentMap;

public class IndexLookup {

    private static String WHITESPACE = " ";

    public static SearchResults getData(String str) {
        Set<Store> stores = new HashSet<>();
        Set<String> ignoredWords = new HashSet<>();

        String[] tokens = Parser.tokenize(str, WHITESPACE);

        for (String token : tokens) {

            String stemmedString;
            if (Config.getInstance().isStemmerEnabled()) {
                stemmedString = Stemmer.stem(token);
            } else {
                stemmedString = token;
            }

            if (StopWordFilter.filterStopWords(token)) {
                continue;
            }

            if (!documentMap.containsKey(stemmedString)) {
                ignoredWords.add(token);
            } else {
                stores.add(documentMap.get(token));
            }
        }

        stores.remove(null);

        return new SearchResults(stores, ignoredWords);
    }

    public static String parseSearchResults(SearchResults searchResults) {
        StringBuilder stringBuilder = new StringBuilder();

        ignoredWordFilter(searchResults, stringBuilder);

        buildResult(searchResults, stringBuilder);

        if (!searchResults.getStoreList().isEmpty()) {
            searchResults.getStoreList().forEach(
                    stores -> stores.getDocInfos().forEach(
                            docInfo -> docInfo.getPositions().size()));
        }

        analyzeFrequency(stringBuilder, searchResults, false);

        return stringBuilder.toString();
    }

    public static String parseAsAnd(SearchResults searchResults, String input) {
        StringBuilder stringBuilder = new StringBuilder();

        ignoredWordFilter(searchResults, stringBuilder);

        if (stringBuilder.length() > 0) {
            return ""; //If ignored words are there then and query as failed
        }

        buildResult(searchResults, stringBuilder);

        if (!searchResults.getStoreList().isEmpty()) {
            searchResults.getStoreList().forEach(
                    stores -> stores.getDocInfos().forEach(
                            docInfo -> docInfo.getPositions().size()));
        }

        analyzeFrequency(stringBuilder, searchResults, true);

        return stringBuilder.toString();
    }

    private static void buildResult(SearchResults searchResults, StringBuilder stringBuilder) {
        if (!searchResults.getStoreList().isEmpty()) {
            stringBuilder.append("Found Words :");
            addWhitespace(stringBuilder);
            addNewLine(stringBuilder);
            for (Store store : searchResults.getStoreList()) {
                stringBuilder.append(store.getId());
                addWhitespace(stringBuilder);
                for (DocInfo docInfo : store.getDocInfos()) {
                    stringBuilder.append(docInfo.getDocId());
                    addWhitespace(stringBuilder);
                }
                addNewLine(stringBuilder);
            }
        }
    }

    private static void ignoredWordFilter(SearchResults searchResults, StringBuilder stringBuilder) {
        if (!searchResults.getIgnoredWords().isEmpty()) {
            stringBuilder.append("Ignored Words :");
            addWhitespace(stringBuilder);
            searchResults.getIgnoredWords().forEach(str -> stringBuilder.append(str).append(WHITESPACE));
            addNewLine(stringBuilder);
        }
    }

    private static void analyzeFrequency(StringBuilder stringBuilder, SearchResults searchResults, boolean isAnd) {
        Map<String, Integer> freq = new HashMap<>();

        for (Store store : searchResults.getStoreList()) {
            for (DocInfo docInfo : store.getDocInfos()) {
                if (freq.get(docInfo.getDocId()) == null) {
                    freq.put(docInfo.getDocId(), 1);
                } else {
                    int count = freq.get(docInfo.getDocId());
                    count++;
                    freq.put(docInfo.getDocId(), count);
                }
            }
        }

        int totalCount = searchResults.getStoreList().size() + searchResults.getIgnoredWords().size();

        stringBuilder.append("Document Frequency:");
        addNewLine(stringBuilder);

        for (Map.Entry<String, Integer> entry : freq.entrySet()) {

            Double frequency = Double.parseDouble(Integer.toString(entry.getValue()))/Double.parseDouble(Integer.toString(totalCount));
            if (isAnd && frequency != 1D) {
                continue;
            }

            stringBuilder.append(entry.getKey());
            addWhitespace(stringBuilder);
            stringBuilder.append(entry.getValue());
            addWhitespace(stringBuilder);
            stringBuilder.append("Out of");
            addWhitespace(stringBuilder);
            stringBuilder.append(totalCount);
            addWhitespace(stringBuilder);
            stringBuilder.append(totalCount).append(" occurances ");
            addWhitespace(stringBuilder);
            stringBuilder.append(Double.parseDouble(Integer.toString(entry.getValue()))/Double.parseDouble(Integer.toString(totalCount)));
            addNewLine(stringBuilder);
        }
    }

    private static void addWhitespace(StringBuilder stringBuilder) {
        stringBuilder.append(WHITESPACE);
    }

    private static void addComma(StringBuilder stringBuilder) {
        stringBuilder.append(",");
    }

    private static void addNewLine(StringBuilder stringBuilder) {
        stringBuilder.append("\n");
    }
}
