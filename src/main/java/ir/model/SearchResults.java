package ir.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class SearchResults {
    Set<Store> storeList;
    Set<String> ignoredWords;
    Set<String> stopWords;
}
