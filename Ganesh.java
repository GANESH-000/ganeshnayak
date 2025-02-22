abstract class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }

    public abstract void displayDetails();  // Polymorphism
}

// Electronics subclass
class Electronics extends Product {
    private int warranty;  // in months

    public Electronics(String name, double price, int warranty) {
        super(name, price);
        this.warranty = warranty;
    }

    @Override
    public void displayDetails() {
        System.out.println("Electronics: " + getName() + " | Price: $" + getPrice() + " | Warranty: " + warranty + " months");
    }
}

// Clothing subclass
class Clothing extends Product {
    private String size;

    public Clothing(String name, double price, String size) {
        super(name, price);
        this.size = size;
    }

    @Override
    public void displayDetails() {
        System.out.println("Clothing: " + getName() + " | Price: $" + getPrice() + " | Size: " + size);
    }
}

// Discount Strategy Interface (Polymorphism)
interface DiscountStrategy {
    double applyDiscount(double price);
}

// Percentage-based discount
class PercentageDiscount implements DiscountStrategy {
    private double percent;

    public PercentageDiscount(double percent) {
        this.percent = percent;
    }

    @Override
    public double applyDiscount(double price) {
        return price - (price * percent / 100);
    }
}

// Flat discount
class FlatDiscount implements DiscountStrategy {
    private double discountAmount;

    public FlatDiscount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public double applyDiscount(double price) {
        return Math.max(price - discountAmount, 0);
    }
}

// Cart class
class Cart {
    private java.util.List<Product> products = new java.util.ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
        System.out.println(product.getName() + " added to cart.");
    }

    public double calculateTotal() {
        double total = 0;
        for (Product p : products) {
            total += p.getPrice();
        }
        return total;
    }

    public void displayCart() {
        System.out.println("\nCart Items:");
        for (Product p : products) {
            p.displayDetails();
        }
        System.out.println("Total Price: $" + calculateTotal());
    }

    public java.util.List<Product> getProducts() {
        return products;
    }
}

// Order class (Encapsulation)
class Order {
    private Cart cart;
    private DiscountStrategy discountStrategy;
    private String status;

    public Order(Cart cart, DiscountStrategy discountStrategy) {
        this.cart = cart;
        this.discountStrategy = discountStrategy;
        this.status = "Pending";
    }

    public void placeOrder() {
        double total = cart.calculateTotal();
        double finalPrice = discountStrategy.applyDiscount(total);
        System.out.println("\nOrder placed successfully!");
        System.out.println("Total before discount: $" + total);
        System.out.println("Total after discount: $" + finalPrice);
        this.status = "Completed";
    }

    public String getStatus() {
        return status;
    }
}

// Main class
public class Ganesh {
    public static void main(String[] args) {
        // Create products
        Electronics laptop = new Electronics("Laptop", 800, 24);
        Clothing tshirt = new Clothing("T-Shirt", 20, "M");

        // Create a cart and add products
        Cart cart = new Cart();
        cart.addProduct(laptop);
        cart.addProduct(tshirt);
        cart.displayCart();

        // Choose a discount strategy
        DiscountStrategy discount = new PercentageDiscount(10); // 10% off

        // Create and place an order
        Order order = new Order(cart, discount);
        order.placeOrder();
        System.out.println("Order Status: " + order.getStatus());
    }
}
