package it.unibo.inheritance.impl;

public class ExtendedStrictBankAccount extends SimpleBankAccount {

    private static final double TRANSACTION_FEE = 0.1;

    public ExtendedStrictBankAccount(final int usrID, final double balance) {
        super(usrID, balance);
    }

    @Override
    public void withdraw(int id, double amount) {
        if (getBalance() >= amount) {
            super.withdraw(id, amount);
        }
    }

    @Override
    public void chargeManagementFees(int id) {
        final double amount = MANAGEMENT_FEE + getTransactionsCount() * TRANSACTION_FEE;
        if (getid() == id && getBalance() >= amount) {
            setBalance(getBalance() - amount);
            resetTransactions();
        }
    }

}
