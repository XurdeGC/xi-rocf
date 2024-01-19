package net.xurdegc.xi_rocf.dominiu.ov;

import jakarta.validation.constraints.NotBlank;

public record NomeAlministrador(String testu) {
  public NomeAlministrador(
      @NotBlank(message = "{nome_alministrador.ensin.informar}") final String testu) {
    this.testu = testu;
  }
}
