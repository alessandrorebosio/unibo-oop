package it.unibo.bank.impl;

import it.unibo.bank.api.AccountHolder;
import it.unibo.bank.api.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.function.Executable;

/**
 * Test class for the {@link StrictBankAccount} class.
 */
class TestStrictBankAccount {

    private static final int INITIAL_AMOUNT = 100;

    // Create a new AccountHolder and a StrictBankAccount for it each time tests are
    // executed.
    private AccountHolder mRossi;
    private BankAccount bankAccount;

    /**
     * Prepare the tests.
     */
    @BeforeEach
    public void setUp() {
        this.mRossi = new AccountHolder("Mario", "Rossi", 1);
        this.bankAccount = new StrictBankAccount(mRossi, 0.0);
    }

    /**
     * Test the initial state of the StrictBankAccount.
     */
    @Test
    public void testInitialization() {
        assertEquals(0.0, bankAccount.getBalance());
        assertEquals(0, bankAccount.getTransactionsCount());
        assertEquals(mRossi, bankAccount.getAccountHolder());
    }

    /**
     * Perform a deposit of 100€, compute the management fees, and check that the
     * balance is correctly reduced.
     */
    @Test
    public void testManagementFees() {
        this.bankAccount.deposit(this.mRossi.getUserID(), INITIAL_AMOUNT);
        assertEquals(1, this.bankAccount.getTransactionsCount());
        assertEquals(INITIAL_AMOUNT, this.bankAccount.getBalance());
        this.bankAccount.chargeManagementFees(this.mRossi.getUserID());
        assertEquals(0, this.bankAccount.getTransactionsCount());
        assertEquals(INITIAL_AMOUNT - StrictBankAccount.TRANSACTION_FEE - StrictBankAccount.MANAGEMENT_FEE,
                bankAccount.getBalance());
    }

    /**
     * Test that withdrawing a negative amount causes a failure.
     */
    @Test
    public void testNegativeWithdraw() {
        try {
            bankAccount.withdraw(mRossi.getUserID(), -INITIAL_AMOUNT);
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
    }

    /**
     * Test that withdrawing more money than it is in the account is not allowed.
     */
    @Test
    public void testWithdrawingTooMuch() {
        this.bankAccount.deposit(this.mRossi.getUserID(), INITIAL_AMOUNT);
        assertEquals(1, this.bankAccount.getTransactionsCount());
        this.bankAccount.withdraw(this.mRossi.getUserID(), 50);
        assertEquals(2, this.bankAccount.getTransactionsCount());
        assertEquals(50, this.bankAccount.getBalance());
        assertThrows(IllegalArgumentException.class, new Executable() {

            @Override
            public void execute() throws Throwable {
                bankAccount.withdraw(mRossi.getUserID(), 100);
            }

        });
    }
}
