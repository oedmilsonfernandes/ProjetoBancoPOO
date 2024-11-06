package com.projectpracticaldev.projetobancopoo.Entity;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class Resultado<T> {
  private final T valor;
  private final String mensagemDeErro;
  private final boolean sucesso;

  private Resultado(T valor, String mensagemDeErro, boolean sucesso) {
    this.valor = valor;
    this.mensagemDeErro = mensagemDeErro;
    this.sucesso = sucesso;
  }

  public static <T> Resultado<T> sucesso(T valor) {
    return new Resultado<>(valor, null, true);
  }

  public static <T> Resultado<T> falhou(String mensagemDeErro) {
    return new Resultado<>(null, mensagemDeErro, false);
  }

  public boolean sucesso() {
    return sucesso;
  }

  public boolean falhou() {
    return !sucesso;
  }

  public Optional<T> pegarValor() {
    return Optional.ofNullable(valor);
  }

  public String pegarMensagemDeErro() {
    return this.mensagemDeErro;
  }

  public <U> Resultado<U> map(Function<? super T, ? extends U> mapper) {
    if (sucesso) {
      return Resultado.sucesso(mapper.apply(valor));
    } else {
      return Resultado.falhou(mensagemDeErro);
    }
  }

  public <U> Resultado<U> flatMap(Function<? super T, Resultado<U>> mapper) {
    if (sucesso) {
      return mapper.apply(valor);
    } else {
      return Resultado.falhou(mensagemDeErro);
    }
  }

  public void seSucesso(Consumer<? super T> consumer) {
    if (sucesso) {
      consumer.accept(valor);
    }
  }

  public void seFalhou(Consumer<? super String> consumer) {
    if (!sucesso) {
      consumer.accept(mensagemDeErro);
    }
  }
}
