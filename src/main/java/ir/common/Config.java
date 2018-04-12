package ir.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import static ir.common.Constants.directoryName;
import static ir.common.Constants.config;

@Data
public class Config {

    //List taken from https://www.ranks.nl/stopwords
    public Set<String> stopWords;

    @JsonIgnore
    private static Config object;

    static {
        String content = "";
        try
        {
            content = new String ( Files.readAllBytes( Paths.get(directoryName + config + "config.json") ) );
            object = ObjectMapperFacade.getInstance().readValue(content, Config.class);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static Config getInstance() {
        if (object == null) {
            object = new Config();
        }

        return object;
    }
}
