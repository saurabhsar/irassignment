package ir.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Store {
    String id;
    private List<DocInfo> docInfos = new ArrayList<DocInfo>();
}
