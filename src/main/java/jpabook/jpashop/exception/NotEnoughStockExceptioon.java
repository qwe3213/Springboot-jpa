package jpabook.jpashop.exception;

public class NotEnoughStockExceptioon extends RuntimeException{
    // override command + n
    public NotEnoughStockExceptioon() {
        super();
    }

    public NotEnoughStockExceptioon(String message) {
        super(message);
    }

    public NotEnoughStockExceptioon(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughStockExceptioon(Throwable cause) {
        super(cause);
    }

    protected NotEnoughStockExceptioon(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
