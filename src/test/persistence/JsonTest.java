package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import model.Transaction;

// Unit test for Json
public class JsonTest {
    protected void checkTransaction(int balance, Transaction transaction) {
        assertEquals(balance, transaction.getBalance());
    }
}
