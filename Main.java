import java.util.List;
import java.util.Scanner;

public class Main {
    private static Biblioteca biblio = new Biblioteca();

    public static void main(String[] args) {
        limparTela();
        String menu = """
                ====== SYSBIBLIO ======
                Escolha uma das opções abaixo:
                1 - Adicionar novo livro
                2 - Pesquisar livro por título
                3 - Pesquisar livro por autor
                4 - Listar todos os livros
                5 - Remover livro por título
                6 - Livro aleatório! (Extra)
                0 - Sair
                """;
        int opcao;
        Scanner lerTeclado = new Scanner(System.in);
        do {
            opcao = inputNumerico(lerTeclado, menu);
            limparTela();
            if (biblio.isAcervoVazio() && opcao > 1) {
                System.out.println(
                        "Nenhum livro cadastrado. Favor cadastrar pelo menos 1 livro para liberar demais funcionalidades!");
                continue;
            }
            switch (opcao) {
                case 1:
                    adicionar(lerTeclado);
                    break;
                case 2:
                    pesquisarPorTitulo(lerTeclado);
                    break;
                case 3:
                    pesquisarPorAutor(lerTeclado);
                    break;
                case 4:
                    pesquisarTodos();
                    break;
                case 5:
                    removerPorTitulo(lerTeclado);
                    break;
                case 6:
                    buscarLivroAleatorio();
                    break;
                case 0:
                    System.out.println("Encerrando programa ...");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (opcao != 0);
    }

    private static void adicionar(Scanner lerTeclado) {
        System.out.println("Digite o título do livro:");
        String titulo = lerTeclado.nextLine();
        System.out.println("Digite o autor do livro:");
        String autor = lerTeclado.nextLine();
        int anoPublicacao = inputNumerico(lerTeclado, "Digite o ano da publicação:");
        int numeroPaginas = inputNumerico(lerTeclado, "Digite o número de páginas:");

        Livro novoLivro;

        int tipoLivro = inputNumerico(lerTeclado, "Qual o tipo do livro: 1-Físico, 2-Digital");
        if (tipoLivro == 1) {
            novoLivro = new LivroFisico();
            System.out.println("Digite as dimensões do livro:");
            String dimensoes = lerTeclado.nextLine();
            int numeroExemplares = inputNumerico(lerTeclado, "Digite o número de exemplares:");

            LivroFisico novoLivroFisico = (LivroFisico) novoLivro;
            novoLivroFisico.setDimensoes(dimensoes);
            novoLivroFisico.setNumeroExemplares(numeroExemplares);
        } else {
            novoLivro = new LivroDigital();
            System.out.println("Digite o formato do arquivo:");
            String formatoArquivo = lerTeclado.nextLine();

            LivroDigital novoLivroDigital = (LivroDigital) novoLivro;
            novoLivroDigital.setFormatoArquivo(formatoArquivo);
        }

        novoLivro.setTitulo(titulo);
        novoLivro.setAutor(autor);
        novoLivro.setAnoPublicacao(anoPublicacao);
        novoLivro.setNumeroPaginas(numeroPaginas);

        try {
            biblio.adicionar(novoLivro);
            System.out.println("Livro adicionado com Sucesso!");
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public static void pesquisarTodos() {
        List<Livro> livros = biblio.pesquisarTodos();
        if (livros.isEmpty()) {
            System.out.println("NENHUM LIVRO CADASTRADO");
        } else {
            System.out.println("Livros Cadastrados:");
            for (Livro livro : livros) {
                System.out.println(livro.toString());
            }
        }
    }

    private static int inputNumerico(Scanner lerTeclado, String mensagem) {
        int valor = 0;
        boolean entradaValida = false;
        System.out.println(mensagem);
        do {
            String valorStr = lerTeclado.nextLine();
            try {
                valor = Integer.parseInt(valorStr);
                entradaValida = true;
            } catch (Exception e) {
                System.out.println("Erro. Por favor informe um número Inteiro");
            }
        } while (!entradaValida);
        return valor;
    }

    private static void removerPorTitulo(Scanner lerTeclado) {
        System.out.println("Digite a palavra-chave para remover livros:");
        String titulo = lerTeclado.nextLine();

        try {
            int qtdLivrosRemovidos = biblio.removerPorTitulo(titulo);
            if (qtdLivrosRemovidos == 0) {
                System.out.println("Nenhum livro removido.");
            } else {
                System.out.println("Foram removidos " + qtdLivrosRemovidos + " livro(s)");
            }
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    private static void pesquisarPorTitulo(Scanner lerTeclado) {
        System.out.println("Digite a palavra-chave para pesquisar livros:");
        String titulo = lerTeclado.nextLine();

        try {
            List<Livro> livrosPesquisados = biblio.pesquisarPorTitulo(titulo);
            if (!livrosPesquisados.isEmpty()) {
                System.out.println("Livros pesquisados");
                for (Livro livro : livrosPesquisados) {
                    System.out.println(livro.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    private static void buscarLivroAleatorio() {
        Livro livroAleatorio = biblio.buscarLivroAleatorio();
        System.out.printf("Livro sorteado: %s", livroAleatorio.toString());
    }

    private static void pesquisarPorAutor(Scanner lerTeclado) {
        System.out.println("Digite a palavra-chave para pesquisar livros:");
        String autor = lerTeclado.nextLine();

        try {
            List<Livro> livrosPesquisados = biblio.pesquisarPorAutor(autor);
            if (!livrosPesquisados.isEmpty()) {
                System.out.println("Livros pesquisados");
                for (Livro livro : livrosPesquisados) {
                    System.out.println(livro.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    private static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}