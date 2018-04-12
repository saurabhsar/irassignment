package ir.assignment;

import com.fasterxml.jackson.core.JsonProcessingException;
import ir.common.Config;
import ir.common.ObjectMapperFacade;
import ir.file.Parser;
import ir.indexer.IndexCreator;
import ir.preprocessor.StopWordFilter;

import java.io.File;
import java.util.Objects;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        File directory = new File(Config.getInstance().getResourcesDirectoryName());

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

        try {
            System.out.println(ObjectMapperFacade.getInstance().writeValueAsString(IndexCreator.documentMap));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
