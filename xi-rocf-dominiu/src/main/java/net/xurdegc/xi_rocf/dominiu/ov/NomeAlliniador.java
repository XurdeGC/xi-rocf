package net.xurdegc.xi_rocf.dominiu.ov;

import jakarta.validation.constraints.NotBlank;

public record NomeAlliniador(String testu) {
  public NomeAlliniador(
      @NotBlank(message = "{nome_alliniador.ensin.informar}") final String testu) {
    this.testu = testu;
  }
}
