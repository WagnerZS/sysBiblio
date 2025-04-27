public final class LivroFisico extends Livro{
    private int numeroExemplares;
    private String dimensoes;

    public int getNumeroExemplares() {
        return numeroExemplares;
    }
    public void setNumeroExemplares(int numeroExemplares) {
        this.numeroExemplares = numeroExemplares;
    }
    public String getDimensoes() {
        return dimensoes;
    }
    public void setDimensoes(String dimensoes) {
        this.dimensoes = dimensoes;
    }

    @Override
    public String toString() {
        String descricao = super.toString();
        descricao += " - Exemplares: " + getNumeroExemplares()
        + " - Formato: " + getFormato()
        + " - Tempo de publicação: " + super.calcularTempoPublicacao() + " anos.";
        return descricao;
    }

    @Override
    public String getFormato() {
        return "Físico";
    }

}
