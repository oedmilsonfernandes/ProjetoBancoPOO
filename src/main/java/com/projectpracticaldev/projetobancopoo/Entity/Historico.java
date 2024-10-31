package com.projectpracticaldev.projetobancopoo.Entity;

import java.util.Date;

public record Historico(String tipoMovimentacao, Date dataMovimentacao, Double valor) {}
