package com.projectpracticaldev.projetobancopoo.Entity;

import java.time.LocalDateTime;
import java.util.AbstractMap.SimpleEntry;

public interface Aplicacao {

    public SimpleEntry<Boolean, String> calcularRendimento(LocalDateTime hoje);
}
