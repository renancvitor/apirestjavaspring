package med.voll.api.domain.medico;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Deveria devolver null quando o único médico cadastrado não está disponível na data")
    void escolherMedicoAleatorioLivreNaDataCenario01() {
        var proximaSegundaAsDez = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 8);

        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.ORTOPEDIA);
        var paciente = cadastrarPaciente("Paciente", "paciente@voll.med", "00000000000");
        cadastrarConsulta(medico, paciente, proximaSegundaAsDez);

        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(
                Especialidade.ORTOPEDIA, proximaSegundaAsDez);
        assertThat(medicoLivre).isNull();
    }

    private Medico cadastrarMedico(String medico, String mail, String number,
                                   Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        testEntityManager.persist(medico);
        return medico;
    }

    private DadosCadastroMedico dadosMedico(String nome, String email,
                                            String crm, Especialidade especialidade) {
        return new DadosCadastroMedico(
                nome,
                email,
                crm,
                especialidade);
    }
}