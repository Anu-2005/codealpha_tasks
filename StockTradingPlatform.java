import java.util.*;

class Stock {
    String name;
    double price;

    Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class Portfolio {
    HashMap<String, Integer> holdings = new HashMap<>();

    void buy(String stock, int qty) {
        holdings.put(stock, holdings.getOrDefault(stock, 0) + qty);
        System.out.println("Bought " + qty + " shares of " + stock);
    }

    void sell(String stock, int qty) {
        if (!holdings.containsKey(stock) || holdings.get(stock) < qty) {
            System.out.println("Not enough shares to sell!");
            return;
        }
        holdings.put(stock, holdings.get(stock) - qty);
        System.out.println("Sold " + qty + " shares of " + stock);
    }

    void viewPortfolio() {
        System.out.println("\n===== Portfolio =====");
        if (holdings.isEmpty()) System.out.println("No stocks owned.");
        else holdings.forEach((s, q) -> System.out.println(s + " : " + q + " shares"));
    }
}

public class StockTradingPlatform {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // sample stock list
        ArrayList<Stock> market = new ArrayList<>();
        market.add(new Stock("TATA", 245.50));
        market.add(new Stock("RELIANCE", 1420.75));
        market.add(new Stock("INFOSYS", 1230.40));

        Portfolio portfolio = new Portfolio();

        while (true) {
            System.out.println("\n===== Stock Trading System =====");
            System.out.println("1. View Market Prices");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n--- Market Data ---");
                    for (Stock s : market)
                        System.out.println(s.name + " : ₹" + s.price);
                    break;

                case 2:
                    System.out.print("Enter stock name: ");
                    String buyName = sc.next().toUpperCase();
                    System.out.print("Enter quantity: ");
                    int buyQty = sc.nextInt();
                    portfolio.buy(buyName, buyQty);
                    break;

                case 3:
                    System.out.print("Enter stock name: ");
                    String sellName = sc.next().toUpperCase();
                    System.out.print("Enter quantity: ");
                    int sellQty = sc.nextInt();
                    portfolio.sell(sellName, sellQty);
                    break;

                case 4:
                    portfolio.viewPortfolio();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}

