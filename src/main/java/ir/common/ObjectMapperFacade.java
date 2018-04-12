package ir.common;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperFacade {
    private static ObjectMapper objectMapper;

    private ObjectMapperFacade(){

    }

    public static ObjectMapper getInstance() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        return objectMapper;
    }
}
