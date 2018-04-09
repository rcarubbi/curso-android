package br.com.impacta.curso.prj_022_mvp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void somar_isCorrect() throws Exception {
        assertEquals(8, ToolBox.somar(3, 5));
    }

    @Test
    public void somar_isIncorreto() throws Exception {
        assertNotEquals(
                10,
                ToolBox.somar(3, 5)
        );

    }


    @Test
    public void subtracao_isCorrect() throws Exception {
        assertEquals(-2, ToolBox.subtrair(3, 5));
    }


    @Test
    public void subtracao_isInCorrect() throws Exception {
        assertNotEquals(
                10,
                ToolBox.subtrair(3, 5)
        );
    }




}