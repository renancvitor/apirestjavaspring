package med.voll.api.medico;

import org.hibernate.validator.internal.engine.messageinterpolation.parser.EscapedState;

public record DadosDetalhamentoMedico(Long id,
                                      String nome,
                                      String email,
                                      String cfm,
                                      String telefone,
                                      Especialidade especialidade) {


    public DadosDetalhamentoMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(),
        medico.getEspecialidade());
    }
}
