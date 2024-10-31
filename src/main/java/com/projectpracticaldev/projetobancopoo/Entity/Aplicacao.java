package com.projectpracticaldev.projetobancopoo.Entity;

import java.time.LocalDateTime;
import com.projectpracticaldev.utils.Resultado;

public interface Aplicacao {

    public Resultado<Boolean> calcularRendimento(LocalDateTime hoje);
}
