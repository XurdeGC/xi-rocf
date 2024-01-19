package net.xurdegc.xi_rocf.dominiu.ov;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CorreuElectronicu(String cuenta) {
  public CorreuElectronicu(
      @NotBlank(message = "{correu_electronicu.cuenta.ensin.informar}")
          @Email(message = "{correu_electronicu.cuenta.invalida}")
          final String cuenta) {
    this.cuenta = cuenta;
  }
}
