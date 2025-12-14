class Transaction {
    protected String name;
    protected double amount;
    protected String category;

    public Transaction(String name, double amount, String category) {
        this.name = name;
        this.amount = amount;
        this.category = category;
    }

    public String toString() {
        return name + " - $" + amount + " (" + category + ")";
    }
}