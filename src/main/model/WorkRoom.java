package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * parts of the code were inspired and taken from JsonSerializationDemo
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */


// Represents a workroom having a collection of transactions
public class WorkRoom implements Writable {
    private String name;
    private List<Transaction> transactions;

    // EFFECTS: constructs workroom with a name and empty list of transactions
    public WorkRoom(String name) {
        this.name = name;
        transactions = new ArrayList<>();
    }

    //REQUIRES: name
    //EFFECTS: returns name
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds transaction to this workroom
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        EventLog.getInstance().logEvent(new Event("adds transaction to this workroom"));
    }

    // EFFECTS: returns an unmodifiable list of transactions in this workroom
    public List<Transaction> getTransactions() {
        EventLog.getInstance().logEvent(new Event("returns list of transactions in this workroom"));
        return Collections.unmodifiableList(transactions);
    }

    // EFFECTS: returns number of transactions in this workroom
    public int numTransaction() {
        return transactions.size();
    }

    //EFFECTS: creates new JSON object, puts name and transactions and returns the object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("transactions", transactionsToJson());
        json.put("name", name);
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray transactionsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Transaction t : transactions) {
            jsonArray.put(t.toJson());
        }
        EventLog.getInstance().logEvent(new Event("returns in workroom as a JSON array"));
        return jsonArray;
    }
}

