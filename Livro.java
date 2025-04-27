import java.time.LocalDate;

public abstract class Livro {
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private int numeroPaginas;
 
    public abstract String getFormato();

    public final int calcularTempoPublicacao(){
        int anoAtual = LocalDate.now().getYear();
        return anoAtual - this.anoPublicacao;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo.trim();
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor.trim();
    }
    public int getAnoPublicacao() {
        return anoPublicacao;
    }
    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }
    public int getNumeroPaginas() {
        return numeroPaginas;
    }
    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    @Override
    public String toString() {
        String descricao = 
        "Título: " + getTitulo() +
        " - Autor: " + getAutor() +
        " - Ano: " + getAnoPublicacao();
        return descricao;
    }

}