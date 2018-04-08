package ir.assignment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.file.Parser;
import ir.indexer.IndexCreator;

import java.io.File;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String directoryName = "src/main/java/ir/resources/";
        File directory = new File(directoryName);
        ObjectMapper objectMapper = new ObjectMapper();

        for (File file : directory.listFiles()){
            if (file.isFile()){
                System.out.println(file.getName());
                IndexCreator.createDocumentMap(
                        Parser.tokenize(
                                Parser.readDocxFile(directoryName + file.getName()),
                                " "), file.getName());
            }
        }
        try {
            System.out.println(objectMapper.writeValueAsString(IndexCreator.documentMap));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
