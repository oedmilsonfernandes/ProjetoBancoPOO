package com.projectpracticaldev.projetobancopoo.Entity;

import java.util.Date;
import java.util.AbstractMap.SimpleEntry;

public interface Aplicacao {

    public SimpleEntry<Boolean, String> calcularRendimento(Date hoje);
}
