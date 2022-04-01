package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
// Unit test for transaction
class TransactionTest {
    Transaction account;

    @BeforeEach
    public void runBefore() {
        account = new Transaction("TestModel", 1, 10);
    }

    @Test
    public void testNewValidAccount() {
        account = new Transaction("Blah", 12, -1);
        assertEquals(0, account.balance);
        assertEquals(0, account.getBalance());
    }

    @Test
    public void testGetName() {
        account = new Transaction("Blah", 12, 1110);
        assertEquals("Blah", account.getName());
    }

    @Test
    public void testGetBalance() {
        assertEquals(10, account.getBalance());
    }

    @Test
    public void testGetBankId() {
        assertEquals(1, account.getBankId());
    }

    @Test
    public void testGetRecentTransaction() {
        int amount = 10;
        account.simpleDeposit(amount);
        assertEquals(10, account.getLatestTransaction());
    }

    @Test
    public void testSetBalance() {
        account.setBalance(40);
        assertEquals(40, account.getBalance());
    }

    @Test
    public void testGetLatestDepositTransaction() {
        int amount = 5;
        account.simpleDeposit(amount);
        assertEquals(5, account.latestTransaction);
    }

    @Test
    public void testGetLatestWithdrawTransaction() {
        int amount = 8;
        account.simpleWithdraw(amount);
        assertEquals(-8, account.latestTransaction);
    }

    @Test
    public void testSimpleDepositOnce() {
        int amount = 100;
        account.simpleDeposit(amount);
        assertEquals(110, account.getBalance());
    }

    @Test
    public void testSimpleDepositMany() {
        int amount = 5;
        account = new Transaction("Manny", 123, 990);
        for (int i = 0; i < 2; i++) {
            account.simpleDeposit(amount);
        }
        assertEquals(1000, account.getBalance());
    }

    @Test
    public void testSimpleWithdrawOnce() {
        int amount = 200;
        account = new Transaction("Manny", 123, 990);
        account.simpleWithdraw(amount);
        assertEquals(790, account.getBalance());
    }

    @Test
    public void testSimpleWithdrawMany() {
        int amount = 200;
        account = new Transaction("Manny", 123, 1000);
        for (int i = 0; i < 2; i++) {
            account.simpleWithdraw(amount);
        }
        assertEquals(600, account.getBalance());
    }

    @Test
    public void testSimpleKnowBalance() {
        int years = 2;
        assertEquals(16.5, account.simpleKnowBalance(years));
    }

    @Test
    public void testSimpleKnowBalanceZero() {
        int years = 12;
        int i = 2;
        account.simpleWithdraw(i);
        assertEquals(39.2, account.simpleKnowBalance(years));
    }

    @Test
    public void testSimpleRecentTransaction() {
        assertEquals(0, account.simpleRecentTransaction());
    }

    @Test
    public void testSimpleRecentTransactionDeposit() {
        int amount = 5;
        double depositedAmount = account.simpleDeposit(amount);
        assertEquals(15, depositedAmount);
        assertEquals(1, account.simpleRecentTransaction());
    }

    @Test
    public void testSimpleRecentTransactionWithdraw() {
        int amount = 5;
        double withdrawAmount = account.simpleWithdraw(amount);
        assertEquals(5, withdrawAmount);
        assertEquals(-1, account.simpleRecentTransaction());
    }

    @Test
    public void testToString() {
        int amount = 5;
        account.simpleDeposit(amount);
        assertEquals("balance: 15.0", account.toString());
    }

}