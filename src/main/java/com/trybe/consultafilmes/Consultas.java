package com.trybe.consultafilmes;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static java.util.Collections.emptySet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Consultas {

  private final Collection<Filme> filmes;

  public Consultas(Collection<Filme> filmes) {
    this.filmes = filmes;
  }

  /**
   * Consulta 1: a partir da coleção de filmes desta classe, este método retorna o conjunto de
   * atores que interpretaram a si próprios em pelo menos um desses filmes.
   *
   * <p>
   * Considera-se "atores que interpretaram a si próprios" aqueles que têm o seu nome como uma das
   * chaves do Map `atoresPorPersonagem` e também como um dos itens pertencentes ao conjunto
   * associado a esta mesma chave.
   * </p>
   */
  public Set<String> atoresQueInterpretaramSiProprios() {
    Set<String> atores = null;

    List<String> personagens =
        filmes.stream().flatMap(filme -> filme.personagens.stream()).collect(Collectors.toList());

    List<Set<String>> atoresporPersonagem = filmes.stream()
        .flatMap(filme -> filme.atoresPorPersonagem.values().stream()).collect(Collectors.toList());

    for (short index = 0; index < personagens.size(); index++) {
      String personagem = personagens.get(index);
      Set<String> tal = atoresporPersonagem.get(index);
      if (tal.contains(personagem)) {
        atores.add(personagem);
      }

    }

    if (atores != null) {
      return atores;
    }

    return emptySet(); // TODO: Implementar.
  }

  /**
   * Consulta 2: a partir da coleção de filmes desta classe, este método retorna a lista de atores
   * que atuaram em pelo menos um filme de um determinado diretor. A lista retornada está disposta
   * em ordem alfabética.
   *
   * <p>
   * Considera-se que um ator tenha atuado em um filme de um determinado diretor se ele tem o seu
   * nome como um dos itens do campo `atores`, ao mesmo tempo em que o diretor em questão tem o seu
   * nome como um dos itens do campo `diretores` do mesmo filme.
   * </p>
   */
  public List<String> atoresQueAtuaramEmFilmesDoDiretorEmOrdemAlfabetica(String diretor) {
    List<String> atores = null;

    for (Filme filme : filmes) {
      if (filme.diretores.contains(diretor)) {
        for (String ator : filme.atores) {
          atores.add(ator);
        }

      }
    }

    if (atores.size() > 0) {
      Collections.sort(atores);

      return atores;
    }

    return emptyList(); // TODO: Implementar.
  }

  /**
   * Consulta 3: a partir da coleção de filmes desta classe, este método retorna a lista de filmes
   * em que pelo menos um dos diretores tenha atuado. A lista retornada está disposta em ordem de
   * lançamento, com os filmes mais recentes no início.
   *
   * <p>
   * Considera-se "filmes em que pelo menos um dos diretores tenha atuado" aqueles em que pelo menos
   * um dos itens do campo `diretores` também é um item do campo `atores`.
   * </p>
   */
  public List<Filme> filmesEmQuePeloMenosUmDiretorAtuouMaisRecentesPrimeiro() {
    List<Filme> filmesRetorno =
        filmes.stream().filter(filme -> diretorAtua(filme)).collect(Collectors.toList());

    if (filmesRetorno.size() > 0) {
      Collections.sort(filmesRetorno, (acc, cur) -> acc.anoDeLancamento - cur.anoDeLancamento);
      return filmesRetorno;
    }

    return emptyList(); // TODO: Implementar.
  }

  private boolean diretorAtua(Filme filme) {
    List<String> diretores = new ArrayList<String>(filme.diretores);

    List<Set<String>> atoresporPersonagem =
        filme.atoresPorPersonagem.values().stream().collect(Collectors.toList());

    for (short index = 0; index < diretores.size(); index++) {
      String diretor = diretores.get(index);
      if (atoresporPersonagem.get(index).contains(diretor)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Consulta 4: a partir da coleção de filmes desta classe, este método retorna um Map contendo
   * todos os filmes lançados em um determinado ano agrupados por categoria.
   *
   * <p>
   * Cada chave do Map representa uma categoria, enquanto cada valor representa o conjunto de filmes
   * que se encaixam na categoria da chave correspondente.
   * </p>
   */
  public Map<String, Set<Filme>> filmesLancadosNoAnoAgrupadosPorCategoria(int ano) {
    return emptyMap(); // TODO: Implementar (bônus).
  }
}
