package net.xurdegc.xi_rocf.domain.validation.base;

import java.text.MessageFormat;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.stream.Collectors;

public abstract class Validator<T> {
  private static final String ERROR_CREATING_MESSAGE_KEY = "error.creating";
  protected static final ResourceBundle VALIDATION_ERROR_MESSAGES =
      ValidationErrorMessageLoader.getInstance().getValidationErrorMessages();
  protected final Map<Integer, String> validationErrors = new TreeMap<>();

  public abstract void validate(final T valueToBeValidated, final String typeName);

  protected void buildIllegalArgumentException(final String typeName) {
    final String exceptionValidationErrors =
        validationErrors.entrySet().stream()
            .map(ev -> ev.getKey() + " - " + ev.getValue())
            .collect(Collectors.joining(System.lineSeparator()));

    throw new IllegalArgumentException(
        new MessageFormat(VALIDATION_ERROR_MESSAGES.getString(ERROR_CREATING_MESSAGE_KEY))
                .format(new Object[] {typeName})
            + System.lineSeparator()
            + exceptionValidationErrors);
  }
}
