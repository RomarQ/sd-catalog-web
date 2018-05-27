package sd.catalog.customExeception;

public class CustomMessageException extends RuntimeException {

    public CustomMessageException(String reason) {
        super(reason);
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        return null;
    }

}
