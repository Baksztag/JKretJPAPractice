import java.util.List;

/**
 * Created by jkret on 26/12/2017.
 */
public class TransactionWrapper {
    private int TransactionNumber;
    private int Quantity;
    private List<Integer> ProductIds;

    public TransactionWrapper() {
    }

    public TransactionWrapper(int transactionNumber, int quantity, List<Integer> productIds) {
        TransactionNumber = transactionNumber;
        Quantity = quantity;
        ProductIds = productIds;
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

    public List<Integer> getProductIds() {
        return ProductIds;
    }

    public void setProductIds(List<Integer> productIds) {
        ProductIds = productIds;
    }
}
