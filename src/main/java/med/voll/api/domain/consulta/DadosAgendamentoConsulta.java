package med.voll.api.domain.consulta;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(Long idMedico,
                                       @NotNull
                                       @JsonAlias("paciente_id") Long idPaciente,
                                       @NotNull
                                       @Future
                                       @JsonAlias("medico_id")LocalDateTime data) {
}
