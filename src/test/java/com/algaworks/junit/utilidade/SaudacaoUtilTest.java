package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.algaworks.junit.utilidade.SaudacaoUtil.saudar;
import static com.algaworks.junit.utilidade.SaudacaoUtilConditions.igualBomDia;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SaudacaoUtilTest {

    /**
     * @Method assertEquals(expected, actual)
     * Verifica se o valor esperado é igual ao valor atual e lança uma exceção AssertionError se não for.
     * Podemos usar este método, por exemplo, para garantir que o resultado de uma operação matemática seja o esperado.
     */
    @Test
    public void saudarBomdia() {
        for (int hora : TipoSaudacao.BOM_DIA.getHoras()) {
            String saudacao = saudar(hora);
            assertThat(saudacao).is(igualBomDia());
        }
    }

    @Test
    public void saudarBoaTarde() {
        for (int hora : TipoSaudacao.BOA_TARDE.getHoras()) {
            String saudacao = saudar(hora);
            assertEquals("Boa tarde", saudacao, String.format("Saudacao incorreta | HORA: %s", hora));
        }
    }

    @Test
    public void saudarBoaNoite() {
        for (int hora : TipoSaudacao.BOA_NOITE.getHoras()) {
            String saudacao = saudar(hora);
            assertEquals("Boa noite", saudacao, String.format("Saudacao incorreta | HORA: %s", hora));
        }
    }

    @Test
    public void deveLancarException() {
        int horaInvalida = -10;
        assertThatThrownBy(() -> saudar(horaInvalida))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Hora inválida");
    }

    /**
     * @Method assertDoesNotThrow é usado para garantir que um determinado bloco de código não lança uma exceção.
     * Ele é usado como um mecanismo de teste para garantir que o código esteja funcionando corretamente e não está gerando exceções inesperadas.
     */
    @Test
    public void naoDeveLancarException() {
        int horaValida = 0;
        Executable chamadaValidaDeMetodo = () -> saudar(horaValida);
        assertDoesNotThrow(chamadaValidaDeMetodo);
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 6, 7, 8, 9, 10, 11})
    public void Dado_horario_matinal_quando_saudar_entao_deve_retornar_bom_dia(int hora) {
        String saudacao = saudar(hora);
        assertEquals(TipoSaudacao.BOM_DIA.getDescricao(), saudacao, String.format("Saudacao incorreta | HORA: %s", hora));
    }
}