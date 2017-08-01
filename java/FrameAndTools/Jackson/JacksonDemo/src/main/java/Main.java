package main.java;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {


    public static void Json2UserObjDemo() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // 这里User类必须拥有空构造方法， User的内部类Name也是。
        User user = mapper.readValue(new File("user.json"), User.class);
        System.out.println(user);
    }

    public static void UserObj2JsonDemo() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User("BorLion", "Zhu", User.Gender.MALE, true, "Rm9vYmFyIQ==".getBytes());
        System.out.println(mapper.writeValueAsString(user));
    }

    public static void Json2MapDemo() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.readValue(new File("user.json"), Map.class));
    }

    public static void Json2TypeReferenceDemo() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.readValue(new File("users.json"), new TypeReference<Map<String,User>>() { }));
    }

    public static void Map2JsonDemo() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("first", "BorLion");
        nameMap.put("lash", "Zhu");
        map.put("name", nameMap);
        map.put("Gender", "MALE");
        map.put("verified", true);
        map.put("userImage", "Um05dlltRnlJUT09");
        System.out.println(mapper.writeValueAsString(map));
    }

    public static void Json2JsonNodeDemo() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(new File("user.json"));
        System.out.println(rootNode.path("name").path("last").textValue());
    }

    public static void OutputStreamDemo() throws IOException {
        String out = "";
        JsonFactory f = new JsonFactory();
        FileOutputStream fos = new FileOutputStream(new File("user.out.json"));
        JsonGenerator g = f.createGenerator(fos);

        g.writeStartObject();
        g.writeObjectFieldStart("name");
        g.writeStringField("first", "BorLion");
        g.writeStringField("last", "Zhu");
        g.writeEndObject(); // for field 'name'
        g.writeStringField("gender", "MALE");
        g.writeBooleanField("verified", true);
        g.writeBinaryField("userImage", "Rm9vYmFyIQ==".getBytes());
        g.writeEndObject();
        g.writeRaw('\n');
        g.close();

    }

    public static void parsingDemo() throws IOException {
        JsonFactory f = new JsonFactory();
        JsonParser jp = f.createParser(new File("user.json"));
        User user = new User();
        jp.nextToken(); // will return JsonToken.START_OBJECT
        while (jp.nextToken() == JsonToken.FIELD_NAME) {
            String fieldname = jp.getCurrentName();
            jp.nextToken(); // move to value, or START_OBJECT/START_ARRAY
            if ("name".equals(fieldname)) { // contains an object
                User.Name name = new User.Name();
                while (jp.nextToken() != JsonToken.END_OBJECT) {
                    String namefield = jp.getCurrentName();
                    jp.nextToken(); // move to value
                    if ("first".equals(namefield)) {
                        name.setFirst(jp.getText());
                    } else if ("last".equals(namefield)) {
                        name.setLast(jp.getText());
                    } else {
                        throw new IllegalStateException("Unrecognized field '"+fieldname+"'!");
                    }
                }
                user.setName(name);
            } else if ("gender".equals(fieldname)) {
                user.setGender(User.Gender.valueOf(jp.getText()));
            } else if ("verified".equals(fieldname)) {
                user.setVerified(jp.getCurrentToken() == JsonToken.VALUE_TRUE);
            } else if ("userImage".equals(fieldname)) {
                user.setUserImage(jp.getBinaryValue());
            } else {
                throw new IllegalStateException("Unrecognized field '"+fieldname+"'!");
            }
        }
        jp.close();
        System.out.println(user);
    }

    public static void arraysParsingDemo() throws IOException {
        JsonFactory f = new JsonFactory();
        JsonParser jp = f.createParser(new File("user.array.json"));
        ObjectMapper mapper = new ObjectMapper();
        List<User> userArray = new ArrayList<User>();
        jp.nextToken();
        while(jp.nextToken() == JsonToken.START_OBJECT) {
            userArray.add(mapper.readValue(jp, User.class));
        }
        for (User user : userArray) {
            System.out.println("-----");
            System.out.println(user);
        }
    }

    public static void main(String[] args) throws IOException {
        Json2UserObjDemo();
        UserObj2JsonDemo();
        Json2MapDemo();
        Json2TypeReferenceDemo();
        Map2JsonDemo();
        Json2JsonNodeDemo();
        OutputStreamDemo();
        parsingDemo();
        arraysParsingDemo();
    }

}
