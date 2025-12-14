class IncomeTransaction extends Transaction {
    public IncomeTransaction(String name, double amount, String category) {
        super(name, amount, category);
    }

    @Override
    public String toString() {
        return "[INCOME] " + super.toString();
    }
}
