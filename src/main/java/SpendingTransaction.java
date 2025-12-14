class SpendingTransaction extends Transaction {
    public SpendingTransaction(String name, double amount, String category) {
        super(name, amount, category);
    }

    @Override
    public String toString() {
        return "[SPEND] " + super.toString();
    }
}
