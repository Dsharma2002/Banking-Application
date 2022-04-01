package persistence;

import model.Transaction;
import model.WorkRoom;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

/**
 * parts of the code were inspired and taken from JsonSerializationDemo
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;
    Transaction transaction;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public WorkRoom read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkRoom(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private WorkRoom parseWorkRoom(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        WorkRoom wr = new WorkRoom(name);
        addTransactions(wr, jsonObject);
        return wr;
    }

    // MODIFIES: wr
    // EFFECTS: parses transactions from JSON object and adds them to workroom
    private void addTransactions(WorkRoom wr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("transactions");
        for (Object json : jsonArray) {
            JSONObject nextTransaction = (JSONObject) json;
            addTransaction(wr, nextTransaction);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses transaction from JSON object and adds it to workroom
    private void addTransaction(WorkRoom wr, JSONObject jsonObject) {
        //double myBalance = jsonObject.getJSONArray("transactions").getJSONObject(1).getString("balance");
        transaction = new Transaction("Div", 11, jsonObject.getDouble("balance"));
        wr.addTransaction(transaction);
    }
}
