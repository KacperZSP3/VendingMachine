interface VendingMachine {
    // Metody wymagające implementacji
    void insertMoney(double amount);  // Wpłata pieniędzy
    void selectItem(String item);     // Wybór produktu

    // Metoda domyślna – informacja o połączeniu z serwerem
    default void connectToServer() {
        System.out.println("Connecting to server to check product availability...");
    }

    // Metoda statyczna – informacja o wyłączeniu automatu
    static void shutdown() {
        System.out.println("Vending machine is shutting down. Thank you for using our service.");
    }
}

class VendingMachineImpl implements VendingMachine {
    private double balance;

    // Konstruktor ustawiający początkowy stan konta
    public VendingMachineImpl() {
        this.balance = 0.0;
    }

    // Implementacja metody insertMoney
    @Override
    public void insertMoney(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Inserted: $" + amount);
            System.out.println("Current balance: $" + balance);
        } else {
            System.out.println("Invalid amount. Please insert a positive value.");
        }
    }

    // Implementacja metody selectItem
    @Override
    public void selectItem(String item) {
        if (balance >= 2.0) {  // Zakładamy, że każdy przedmiot kosztuje $2.0
            balance -= 2.0;
            System.out.println("Dispensing item: " + item);
            System.out.println("Remaining balance: $" + balance);
        } else {
            System.out.println("Insufficient funds. Please insert more money.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Tworzymy nowy obiekt VendingMachineImpl
        VendingMachine vendingMachine = new VendingMachineImpl();

        // Wywołujemy metodę domyślną
        vendingMachine.connectToServer();

        // Wpłata $5.0
        vendingMachine.insertMoney(5.0);

        // Wybór produktu
        vendingMachine.selectItem("Chips");

        // Wybór kolejnego produktu bez wystarczających środków
        vendingMachine.selectItem("Soda");

        // Wywołujemy metodę statyczną – zamknięcie automatu
        VendingMachine.shutdown();
    }
}

