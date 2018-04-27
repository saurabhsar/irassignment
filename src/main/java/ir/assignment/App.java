package ir.assignment;

import ir.common.ObjectMapperFacade;
import ir.file.Parser;
import ir.indexer.Index;
import ir.indexer.IndexCreator;
import ir.indexer.IndexLookup;
import ir.model.SearchResults;
import ir.preprocessor.StopWordFilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

import static ir.indexer.IndexLookup.parseAsAnd;
import static ir.indexer.IndexLookup.parseSearchResults;

/**
 * Hello world!
 *
 */
public class App 
{
    private static String OS = System.getProperty("os.name").toLowerCase();

    public static void main( String[] args ) throws IOException {

        File directory = new File(args[0]);
        SearchResults searchResults;
        for (File file : Objects.requireNonNull(directory.listFiles())){
            System.out.println(file.getAbsolutePath());
            if (file.isFile()){
                StopWordFilter.filterStopWords(
                    IndexCreator.createDocumentMap(
                            Parser.tokenize(
                                    Parser.readDocxFile(directory.getAbsolutePath() + getSeparatorBasedOnOs() + file.getName()),
                                    " "), (String) file.getName().subSequence(0, file.getName().length() -5 )));
            }
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Query Type:\n1. OR\n2. AND\n3. Print Index\n4.Exit\nPlease enter selection (1 or 2 or 3 or 4) : ");
            String queryType = reader.readLine();
//            searchResults = IndexLookup.getData("n alumni lane library ways to give find a person about us mission to develop and apply innovative techniques for efficient quantitative analysis and display of medical imaging data through interdisciplinary collaboration goals education to train physicians and technologists locally and");

            if (queryType.equalsIgnoreCase("1")) {
                System.out.println("Enter search String");
                String query = reader.readLine();
                searchResults = IndexLookup.getData(query, false);
                try {
                    System.out.println(parseSearchResults(searchResults));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (queryType.equalsIgnoreCase("2")) {
                System.out.println("Enter search String");
                String query = reader.readLine();
                searchResults = IndexLookup.getData(query, true);
                System.out.println(parseAsAnd(searchResults));
            } else if (queryType.equalsIgnoreCase("3")) {
                System.out.println(ObjectMapperFacade.getInstance().writeValueAsString(Index.documentMap));
            } else {
                System.out.println("Exiting ad input is neither 1 or 2 or 3");
                System.exit(0);
            }
        }
    }

    private static String getSeparatorBasedOnOs() {
        if (OS.contains("win")) {
            return "\\";
        } else {
            return "/";
        }
    }
}
