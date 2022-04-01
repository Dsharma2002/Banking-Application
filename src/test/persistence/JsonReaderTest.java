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

// unit test for Json Reader
public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            WorkRoom wr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            WorkRoom wr = reader.read();
            assertEquals("Divyansh's workroom", wr.getName());
            assertEquals(1, wr.numTransaction());
            assertEquals(0, wr.getTransactions().get(wr.getTransactions().size() - 1).getBalance());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            WorkRoom wr = reader.read();
            assertEquals("Divyansh's workroom", wr.getName());
            List<Transaction> transactions = wr.getTransactions();
            assertEquals(3, transactions.size());
            checkTransaction(110, transactions.get(0));
            checkTransaction(210, transactions.get(1));
            checkTransaction(10, transactions.get(2));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

