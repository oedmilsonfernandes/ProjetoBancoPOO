package com.projectpracticaldev.projetobancopoo.Entity;

import java.time.LocalDateTime;

public interface Aplicacao {
  public Resultado<Boolean> calcularRendimento(LocalDateTime hoje);
}
