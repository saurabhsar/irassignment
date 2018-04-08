package ir.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class DocInfo {
    String docId;
    Set<Integer> positions;

    public DocInfo construct(String docId, int position) {
        if (positions == null) {
            positions = new HashSet<Integer>();
        }

        this.docId = docId;
        this.positions.add(position);

        return this;
    }
}
