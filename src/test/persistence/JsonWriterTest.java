package persistence;

import model.Transaction;
import model.WorkRoom;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * parts of the code are inspired and taken from JsonSerializationDemo
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */

// unit test for Json Writer
public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            WorkRoom wr = new WorkRoom("My work room");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            WorkRoom wr = new WorkRoom("Divyansh's workroom");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkRoom.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkRoom.json");
            wr = reader.read();
            assertEquals("Divyansh's workroom", wr.getName());
            assertEquals(0, wr.numTransaction());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            WorkRoom wr = new WorkRoom("Divyansh's workroom");
            wr.addTransaction(new Transaction("Div", 11, 110));
            wr.addTransaction(new Transaction("Div", 11, 210));
            wr.addTransaction(new Transaction("Div", 11, 10));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkRoom.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkRoom.json");
            wr = reader.read();
            assertEquals("Divyansh's workroom", wr.getName());
            List<Transaction> transactions = wr.getTransactions();
            assertEquals(3, transactions.size());
            checkTransaction(110, transactions.get(0));
            checkTransaction(210, transactions.get(1));
            checkTransaction(10, transactions.get(2));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
