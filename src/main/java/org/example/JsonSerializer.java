package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerializer<T> {
   public String toJson(T objet){
       ObjectMapper objectMapper=new ObjectMapper();
       try {
          String json= objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objet);
          return json;

       } catch (JsonProcessingException e) {
           throw new RuntimeException(e);
       }
   }

}
