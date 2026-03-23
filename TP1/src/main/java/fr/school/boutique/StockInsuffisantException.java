package fr.school.boutique;

// exception personnalisee quand le stock est insuffisant
public class StockInsuffisantException extends RuntimeException {
    public StockInsuffisantException(String message) {
        super(message);
    }
}
