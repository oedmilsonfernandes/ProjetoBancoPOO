package com.projectpracticaldev.projetobancopoo.Entity;

import java.time.LocalDateTime;

public record Historico(String tipoMovimentacao, LocalDateTime dataMovimentacao, Double valor) {}
