package ir.assignment;

import com.fasterxml.jackson.core.JsonProcessingException;
import ir.common.Config;
import ir.common.ObjectMapperFacade;
import ir.file.Parser;
import ir.indexer.Index;
import ir.indexer.IndexCreator;
import ir.indexer.IndexLookup;
import ir.model.SearchResults;
import ir.preprocessor.StopWordFilter;

import java.io.File;
import java.util.Objects;

import static ir.indexer.IndexLookup.parseSearchResults;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        File directory = new File(Config.getInstance().getResourcesDirectoryName());
        SearchResults searchResults;
        for (File file : Objects.requireNonNull(directory.listFiles())){
            if (file.isFile()){
                System.out.println(file.getName());
                StopWordFilter.filterStopWords(
                    IndexCreator.createDocumentMap(
                            Parser.tokenize(
                                    Parser.readDocxFile(Config.getInstance().getResourcesDirectoryName() + file.getName()),
                                    " "), (String) file.getName().subSequence(0, file.getName().length() -5 )));
            }
        }

        searchResults = IndexLookup.getData("n alumni lane library ways to give find a person about us mission to develop and apply innovative techniques for efficient quantitative analysis and display of medical imaging data through interdisciplinary collaboration goals education to train physicians and technologists locally and");

        try {
            System.out.println(ObjectMapperFacade.getInstance().writeValueAsString(Index.documentMap));
            System.out.println(parseSearchResults(searchResults));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
