package org.example;

import org.example.test_json.People;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.example.test_json.Root;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonSimpleParser {

    public List<String> parse(String fileName) {
        Root root = new Root();
        List expressions = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try(FileReader reader = new FileReader(fileName);) {
            JSONObject rootJsonObject = (JSONObject) parser.parse(reader);
            String name = (String) rootJsonObject.get("name");

            JSONArray peopleJsonArray = (JSONArray) rootJsonObject.get("expressions");
            List<People> peopleList = new ArrayList<>();
            for(Object it: peopleJsonArray) {
                JSONObject peopleJsonObject = (JSONObject) it;

                String namePeople = (String) peopleJsonObject.get("exp");

                expressions.add(namePeople);
            }

            root.setName(name);
            root.setPeople(peopleList);
            return expressions;
        } catch (Exception e) {
            System.out.println("Parsing error!" + e.toString());
        }

        return null;
    }
}
