package net.xurdegc.xi_rocf.domain.specification;

import java.time.LocalDate;
import net.xurdegc.xi_rocf.domain.entity.Footballer;
import net.xurdegc.xi_rocf.domain.vo.DataNacenciaFutbolista;
import net.xurdegc.xi_rocf.domain.vo.Disponibilida;
import net.xurdegc.xi_rocf.domain.vo.Dorsal;
import net.xurdegc.xi_rocf.domain.vo.FootballerId;
import net.xurdegc.xi_rocf.domain.vo.Llateralida;
import net.xurdegc.xi_rocf.domain.vo.NomeFutbolista;

public interface FootballerSpecificationTestable {
  default Footballer createDummyFootballer() {
    return Footballer.entamador(
            FootballerId.NEW.get(),
            NomeFutbolista.entamador(
                    new NomeFutbolista.NomeCompletu("Xxxxx Xxxxx Xxxxxx"),
                    new NomeFutbolista.ApelliuDorsal("Xxxxxx"))
                .colNomatuDorsal(new NomeFutbolista.NomatuDorsal("Xxxxxx"))
                .entama(),
            new DataNacenciaFutbolista(LocalDate.of(1, 1, 1)),
            Llateralida.DERECHA,
            Disponibilida.DISPONIBLE)
        .colDorsal(new Dorsal((byte) 1))
        .entama();
  }
}
