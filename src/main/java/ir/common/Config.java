package ir.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import static ir.common.Constants.directoryName;
import static ir.common.Constants.config;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Config {

    //List taken from https://www.ranks.nl/stopwords
    private Set<String> stopWords;
    private boolean stopWordFilterEnabled;
    private boolean stemmerEnabled;
    private String resourcesDirectoryName;

    @JsonIgnore
    private Set<String> stemmedStopWords = new HashSet<>();

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
            //In case config file is missing
            String conf = "{\"stopWords\":[\"a\",\"about\",\"above\",\"after\",\"again\",\"against\",\"all\",\"am\",\"an\",\"and\",\"any\",\"are\",\"aren't\",\"as\",\"at\",\"be\",\"because\",\"been\",\"before\",\"being\",\"below\",\"between\",\"both\",\"but\",\"by\",\"can't\",\"cannot\",\"could\",\"couldn't\",\"did\",\"didn't\",\"do\",\"does\",\"doesn't\",\"doing\",\"don't\",\"down\",\"during\",\"each\",\"few\",\"for\",\"from\",\"further\",\"had\",\"hadn't\",\"has\",\"hasn't\",\"have\",\"haven't\",\"having\",\"he\",\"he'd\",\"he'll\",\"he's\",\"her\",\"here\",\"here's\",\"hers\",\"herself\",\"him\",\"himself\",\"his\",\"how\",\"how's\",\"i\",\"i'd\",\"i'll\",\"i'm\",\"i've\",\"if\",\"in\",\"into\",\"is\",\"isn't\",\"it\",\"it's\",\"its\",\"itself\",\"let's\",\"me\",\"more\",\"most\",\"mustn't\",\"my\",\"myself\",\"no\",\"nor\",\"not\",\"of\",\"off\",\"on\",\"once\",\"only\",\"or\",\"other\",\"ought\",\"our\",\"ours\",\"ourselves\",\"out\",\"over\",\"own\",\"same\",\"shan't\",\"she\",\"she'd\",\"she'll\",\"she's\",\"should\",\"shouldn't\",\"so\",\"some\",\"such\",\"than\",\"that\",\"that's\",\"the\",\"their\",\"theirs\",\"them\",\"themselves\",\"then\",\"there\",\"there's\",\"these\",\"they\",\"they'd\",\"they'll\",\"they're\",\"they've\",\"this\",\"those\",\"through\",\"to\",\"too\",\"under\",\"until\",\"up\",\"very\",\"was\",\"wasn't\",\"we\",\"we'd\",\"we'll\",\"we're\",\"we've\",\"were\",\"weren't\",\"what\",\"what's\",\"when\",\"when's\",\"where\",\"where's\",\"which\",\"while\",\"who\",\"who's\",\"whom\",\"why\",\"why's\",\"with\",\"won't\",\"would\",\"wouldn't\",\"you\",\"you'd\",\"you'll\",\"you're\",\"you've\",\"your\",\"yourselves\",\"yours\",\"yourself\"],\"stopWordFilterEnabled\":true,\"stemmerEnabled\":true,\"resourcesDirectoryName\":\"src/main/java/ir/resources/\"}";
            try {
                object = ObjectMapperFacade.getInstance().readValue(conf, Config.class);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static Config getInstance() {
        if (object == null) {
            object = new Config();
        }

        return object;
    }
}
