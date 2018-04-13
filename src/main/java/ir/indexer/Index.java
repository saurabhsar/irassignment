package ir.indexer;

import ir.model.Store;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Index {
    public static Map<String, Store> documentMap = new HashMap<String, Store>();
}
