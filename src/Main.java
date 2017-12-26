import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

import static spark.Spark.*;


/**
 * Created by jkret on 22/12/2017.
 */
public class Main {
    private static SessionFactory sessionFactory = null;

    public static void main(String[] args) {

        sessionFactory = getSessionFactory();
        staticFiles.location("/public");
        enableCORS();

        post("/api/product", (req, res) -> {
            Session session = sessionFactory.openSession();
            Gson gson = new Gson();
            Transaction tx = session.beginTransaction();
            try {
                Product product = gson.fromJson(req.body(), Product.class);
                System.out.println(product);
                session.save(product);
                tx.commit();
                return "OK";
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            } finally {
                if (session.isOpen()) {
                    session.close();
                }
            }
        });

        post("/api/supplier", (req, res) -> {
            Session session = sessionFactory.openSession();
            Gson gson = new Gson();
            Transaction tx = session.beginTransaction();
            try {
                Supplier supplier = gson.fromJson(req.body(), Supplier.class);
                System.out.println(supplier);
                session.save(supplier);
                tx.commit();
                return "OK";
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            } finally {
                if (session.isOpen()) {
                    session.close();
                }
            }
        });

        post("/api/customer", (req, res) -> {
            Session session = sessionFactory.openSession();
            Gson gson = new Gson();
            Transaction tx = session.beginTransaction();
            try {
                Customer customer = gson.fromJson(req.body(), Customer.class);
                System.out.println(customer);
                session.save(customer);
                tx.commit();
                return "OK";
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            } finally {
                if (session.isOpen()) {
                    session.close();
                }
            }
        });

        post("/api/transaction", (req, res) -> {
            Session session = sessionFactory.openSession();
            Gson gson = new Gson();
            Transaction tx = session.beginTransaction();
            try {
                Transaction_ transaction = gson.fromJson(req.body(), Transaction_.class);
                System.out.println(transaction);
                session.save(transaction);
                tx.commit();
                return "OK";
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            } finally {
                if (session.isOpen()) {
                    session.close();
                }
            }
        });

        post("/api/transaction/products", (req, res) -> {
            Session session = sessionFactory.openSession();
            Gson gson = new Gson();
            Transaction tx = session.beginTransaction();
            try {
                TransactionWrapper transactionWithIds = gson.fromJson(req.body(), TransactionWrapper.class);
                Query query = session.createQuery(
                                "FROM Product WHERE ProductId IN (:ids)"
                );
                query.setParameterList("ids", transactionWithIds.getProductIds());
                List<Product> selectedProducts = query.getResultList();
                System.out.println(selectedProducts);

                Transaction_ transaction = new Transaction_(
                        transactionWithIds.getTransactionNumber(),
                        transactionWithIds.getQuantity()
                );
                for (Product product : selectedProducts) {
                    transaction.addSoldProduct(product);
                    product.addTransaction(transaction);
                    session.save(product);
                }
                session.save(transaction);
                tx.commit();
                return "OK";
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            } finally {
                if (session.isOpen()) {
                    session.close();
                }
            }
        });

        get("/customers", (req, res) -> {
            Session session = sessionFactory.openSession();
            Gson gson = new Gson();
            try {
                List<Customer> customers = session.createQuery("FROM Customer").getResultList();
                System.out.println(customers);


                return gson.toJson(customers);
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
            finally {
                if (session.isOpen()) {
                    session.close();
                }
            }
        });

        get("/suppliers", (req, res) -> {
            Session session = sessionFactory.openSession();
            Gson gson = new Gson();
            try {
                List<Supplier> suppliers = session.createQuery("FROM Supplier").getResultList();
                System.out.println(suppliers);


                return gson.toJson(suppliers);
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
            finally {
                if (session.isOpen()) {
                    session.close();
                }
            }
        });

        get("/products", (req, res) -> {
            Session session = sessionFactory.openSession();
            Gson gson = new Gson();
            try {
                List<Product> products = session.createQuery("FROM Product").getResultList();
                System.out.println(products);


                return gson.toJson(products);
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
            finally {
                if (session.isOpen()) {
                    session.close();
                }
            }
        });

        get("/transactions", (req, res) -> {
            Session session = sessionFactory.openSession();
            Gson gson = new Gson();
            try {
                List<Transaction_> transactions = session.createQuery("FROM Transaction_").getResultList();
                System.out.println(transactions);


                return gson.toJson(transactions);
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
            finally {
                if (session.isOpen()) {
                    session.close();
                }
            }
        });
    }

    private static void enableCORS() {
        options("/*",
                (request, response) -> {
                    String accessControlRequestHeaders = request
                            .headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers",
                                accessControlRequestHeaders);
                    }

                    String accessControlRequestMethod = request
                            .headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods",
                                accessControlRequestMethod);
                    }

                    return "OK";
                });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
    }


    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            sessionFactory = configuration.configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
