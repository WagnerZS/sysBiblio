import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Biblioteca {
    // BD em memória
    private List<Livro> acervo = new ArrayList<>();

    private final int ANO_PUBLICACAO_MINIMA = 1400;

    public Livro adicionar(Livro novoLivro) throws Exception {
        if (Utils.nullOrEmpty(novoLivro.getTitulo())){
            throw new Exception("Título inválido!");
        }else if (Utils.nullOrEmpty(novoLivro.getAutor())){
            throw new Exception("Autor inválido!");
        }else if (novoLivro.getNumeroPaginas() <= 0){
            throw new Exception("Quantidade de páginas inválido!");
        }else if (livroCadastrado(novoLivro.getTitulo())) {
            throw new Exception("Já existe um livro com esse título cadastrado!");
        } else if(novoLivro.getAnoPublicacao() < ANO_PUBLICACAO_MINIMA){
            throw new Exception("Ano de publicação inválido!");
        }

        acervo.add(novoLivro);

        return novoLivro;
    }

    public List<Livro> pesquisarPorTitulo(String titulo) {
        List<Livro> livrosEncontrados = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase().trim())) {
                livrosEncontrados.add(livro);
            }
        }
        return livrosEncontrados;
    }

    public List<Livro> pesquisarTodos() {
        return acervo;
    }

    public int removerPorTitulo(String titulo) {
        int livrosRemovidos = 0;
        List<Livro> listLivroParaRemover = new ArrayList<Livro>();
        for (Livro livro : acervo) {
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase().trim())) {
                listLivroParaRemover.add(livro);
                livrosRemovidos++;
            }
        }
        acervo.removeAll(listLivroParaRemover);
        return livrosRemovidos;
    }

    public Livro buscarLivroAleatorio() {
        int tamanhoAcervo = acervo.size();
        Random random = new Random();
        return acervo.get(random.nextInt(tamanhoAcervo));
    }

    public Boolean isAcervoVazio(){
        return acervo.isEmpty();
    }

    public List<Livro> pesquisarPorAutor(String autor) {
        List<Livro> livrosEncontrados = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getAutor().toLowerCase().contains(autor.toLowerCase().trim())) {
                livrosEncontrados.add(livro);
            }
        }
        return livrosEncontrados;
    }

    private boolean livroCadastrado(String titulo) {
        for (Livro livro : acervo){
            return livro.getTitulo().toLowerCase().equals(titulo.toLowerCase());
        }
        return false;
    }

}