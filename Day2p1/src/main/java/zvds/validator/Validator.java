package zvds.validator;

public interface Validator {
    <T> boolean isValid(T input);
}
