package ir.indexer;

import ir.model.DocInfo;
import ir.model.Store;
import ir.preprocessor.Stemmer;

import java.util.Map;

import static ir.indexer.Index.documentMap;

public class IndexCreator {

    public static Map<String, Store> createDocumentMap(String[] listOfStrings, String documentId) {
        int counter = 0;
        for (String str : listOfStrings) {

            DocInfo docInfo = new DocInfo();
            docInfo.construct(documentId, counter);
            counter++;

            String stemmedStr = Stemmer.stem(str);

            if(documentMap.containsKey(stemmedStr)) {
                boolean flag = false;
                for (DocInfo docInfo1 : documentMap.get(stemmedStr).getDocInfos()){
                    if (docInfo1.getDocId().equals(documentId)) {
                        docInfo1.getPositions().add(counter);
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    documentMap.get(stemmedStr).getDocInfos().add(docInfo);
                }
            } else {
                Store store = new Store();
                store.setId(stemmedStr);
                store.getDocInfos().add(docInfo);

                documentMap.put(stemmedStr, store);
            }
        }

        return documentMap;
    }
}
