package it.unibo.encapsulation.interfaces;

public class StrictBankAccount implements BankAccount {

    private final static int ATM_TRANSACTION_FEE = 1;
    private static final double MANAGEMENT_FEE = 5;
    private static final double TRANSACTION_FEE = 0.1;

    private final int id;
    private double balance;
    private int transactions;

    public StrictBankAccount(final int id, final double balance) {
        this.id = id;
        this.balance = balance;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public int getTransactionsCount() {
        return this.transactions;
    }

    @Override
    public void deposit(final int id, final double amount) {
        if (this.id == id) {
            this.balance += amount;
            this.transactions++;
        }
    }

    @Override
    public void withdraw(final int id, final double amount) {
        if (this.balance >= amount) {
            this.deposit(id, -amount);
        }
    }

    @Override
    public void depositFromATM(final int id, final double amount) {
        this.deposit(id, amount - ATM_TRANSACTION_FEE);
    }

    @Override
    public void withdrawFromATM(final int id, final double amount) {
        this.withdraw(id, amount + ATM_TRANSACTION_FEE);
    }

    @Override
    public void chargeManagementFees(final int id) {
        this.withdraw(id, MANAGEMENT_FEE + TRANSACTION_FEE * this.transactions);
        this.transactions = 0;
    }
}
