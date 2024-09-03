package net.xurdegc.xi_rocf.domain.validation.base;

import java.util.ResourceBundle;

public enum ValidationErrorMessageLoader {
  INSTANCE("texts.validation.mensaxesError");

  private final ResourceBundle validationErrorMessages;

  ValidationErrorMessageLoader(String validationErrorMessageBundleBaseName) {
    this.validationErrorMessages = ResourceBundle.getBundle(validationErrorMessageBundleBaseName);
  }

  public static ValidationErrorMessageLoader getInstance() {
    return INSTANCE;
  }

  public ResourceBundle getValidationErrorMessages() {
    return validationErrorMessages;
  }
}
