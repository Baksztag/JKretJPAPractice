import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jkret on 23/12/2017.
 */
@Entity
public class Transaction_ {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int TransactionId;

    private int TransactionNumber;
    private int Quantity;

    @ManyToMany
    private Set<Product> sales;

    public Transaction_() {
    }

    public Transaction_(int transactionNumber, int quantity) {
        TransactionNumber = transactionNumber;
        Quantity = quantity;
        sales = new HashSet<>();
    }

    public int getTransactionNumber() {
        return TransactionNumber;
    }

    public void setTransactionNumber(int transactionNumber) {
        TransactionNumber = transactionNumber;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public void addSoldProduct(Product product) {
        this.sales.add(product);
    }

    public Set<Product> getSales() {
        return sales;
    }

    @Override
    public String toString() {
        return "Transaction_{" +
                "TransactionId=" + TransactionId +
                ", TransactionNumber=" + TransactionNumber +
                '}';
    }
}
