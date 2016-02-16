package db;

/**
 * Created by cathlene on 16/02/2016.
 */
public class DbException extends RuntimeException {


    public DbException() {
        super();
    }

    public DbException(String message, Throwable exception) {
        super(message, exception);
    }

    public DbException(String message) {
        super(message);
    }

    public DbException(Throwable exception) {
        super(exception);
    }
}
