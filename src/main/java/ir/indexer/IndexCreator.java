package ir.indexer;

import ir.file.Parser;
import ir.model.DocInfo;
import ir.model.SearchResults;
import ir.model.Store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexCreator {

    public static Map<String, Store> documentMap = new HashMap<String, Store>();

    public static void createDocumentMap(String[] listOfStrings, String documentId) {
        int counter = 0;
        for (String str : listOfStrings) {

            DocInfo docInfo = new DocInfo();
            docInfo.construct(documentId, counter);
            counter++;

            if(documentMap.containsKey(str)) {
                boolean flag = false;
                for (DocInfo docInfo1 : documentMap.get(str).getDocInfos()){
                    if (docInfo1.getDocId().equals(documentId)) {
                        docInfo1.getPositions().add(counter);
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    documentMap.get(str).getDocInfos().add(docInfo);
                }
            } else {
                Store store = new Store();
                store.setId(str);
                store.getDocInfos().add(docInfo);

                documentMap.put(str, store);
            }
        }
    }

    public static SearchResults getData(String str) {

        List<Store> stores = new ArrayList<Store>();
        List<String> ignoredWords = new ArrayList<String>();

        String[] tokens = Parser.tokenize(str, " ");
        for (String token : tokens) {
            if (!documentMap.containsKey(token)) {
                ignoredWords.add(token);
            }
            stores.add(documentMap.get(token));
        }

        return new SearchResults(stores, ignoredWords);
    }
}
