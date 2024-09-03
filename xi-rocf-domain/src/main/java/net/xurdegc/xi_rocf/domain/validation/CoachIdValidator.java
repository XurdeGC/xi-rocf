package net.xurdegc.xi_rocf.domain.validation;

import java.util.*;
import net.xurdegc.xi_rocf.domain.validation.base.IdValidator;

public class CoachIdValidator extends IdValidator {
  private static final String COACH_ID_LABEL_MISSING_MESSAGE_KEY = "coach_id.label.missing";

  @Override
  public void validate(final UUID validatableCoachIdLabel, final String coachIdTypeName) {
    validateId(validatableCoachIdLabel, coachIdTypeName, COACH_ID_LABEL_MISSING_MESSAGE_KEY);
  }
}
