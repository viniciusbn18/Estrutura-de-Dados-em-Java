import java.util.InputMismatchException;
import java.util.Scanner;

public class TadMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tamanho = 0, minimo = 0, maximo, repetir = 0, vagaValor = 0;

        System.out.println("============================================================================");
        System.out.println("                          Configurações Iniciais:                           ");
        System.out.println("============================================================================");
        System.out.println();

        boolean continuarTamanho = true;
        while (continuarTamanho) {
            System.out.print("Qual o tamanho do vetor? ");
            tamanho = sc.nextInt();
            sc.nextLine();
            if (tamanho <= 0) {
                System.out.println("Erro: não é possível definir um vetor de tamanho negativo.");
            } else {
                continuarTamanho = false;
            }
        }
        //Valor máximo
        System.out.print("Qual o valor máximo? ");
        maximo = sc.nextInt();
        sc.nextLine();

        //Valor mínimo
        boolean continuarMinimo = true;
        while (continuarMinimo) {
            System.out.print("Qual o valor mínimo? ");
            minimo = sc.nextInt();
            sc.nextLine();
            if (minimo >= maximo) {
                System.out.println("Erro: O valor mínimo deve ser menor que " + maximo);
            }
            if (minimo < maximo) {
                continuarMinimo = false;
            }
        }
        //Admite repetições ou não
        boolean continuarRepetir = true;
        while (continuarRepetir) {
            System.out.println("""
                    Quer que o vetor admita repetições de valores?
                    1 - Sim
                    2 - Não""");
            repetir = sc.nextInt();
            sc.nextLine();
            if (repetir == 1 || repetir == 2) {
                continuarRepetir = false;
            } else {
                System.out.println("Erro: Escolha uma das opções.");
            }
        }

        //Indicador de posição vaga
        boolean continuarVaga = true;
        while (continuarVaga) {
            System.out.print("Qual número você quer que indique como posição vaga no vetor? ");
            vagaValor = sc.nextInt();
            sc.nextLine();

            if (vagaValor >= minimo && vagaValor <= maximo) {
                System.out.println("Erro: o valor deve estar fora do intervalo: " + minimo + " -- " + maximo);
            } else {
                continuarVaga = false;
            }
        }

        TadVetor vetor = new TadVetor(maximo, minimo, repetir, tamanho, vagaValor);

        System.out.println("============================================================================");
        System.out.println("                               Bem-vindo:                                   ");
        System.out.println("============================================================================");

        boolean programaEmExecucao = true;
        while (programaEmExecucao) {

            System.out.println("Escolha uma das opções: ");
            System.out.println("""
                    1 - Atribuir um valor a determinada posição\s
                    2 - Alterar valor de determinada posição\s
                    3 - Remover o valor de determinada posição\s
                    4 - Ler o conteúdo de determinada posição\s
                    5 - Localizar um valor e retornar posição\s
                    6 - Inserir na primeira posição vaga\s
                    7 - Remover da última posição ocupada\s
                    8 - Imprimir vetor\s
                    9 - Sair do Programa
                    """);
            System.out.print("Digite sua escolha: ");
            try {
                int opcaoUsuario = sc.nextInt();
                sc.nextLine();
                System.out.println();

                switch (opcaoUsuario) {
                    case 1:
                        try {
                            System.out.println("--- Atribuir Valor ---");
                            System.out.print("Qual posição quer atribuir? ");
                            int posicaoAtribuir = sc.nextInt();
                            sc.nextLine();

                            System.out.println("Qual valor quer atribuir? ");
                            System.out.print("Digite sua escolha: ");
                            int valorAtribuir = sc.nextInt();
                            sc.nextLine();

                            vetor.atribuirValor(valorAtribuir, posicaoAtribuir);
                        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException exception) {
                            System.out.println(exception.getMessage());
                        }
                        break;

                    case 2:
                        System.out.println("ALterar Valor:");
                        System.out.println();
                        try {
                            System.out.print("Qual posição deseja alterar? ");
                            int posicaoAlterar = sc.nextInt();
                            sc.nextLine();

                            System.out.print("Para qual valor deseja alterar? ");
                            int valorAlterar = sc.nextInt();
                            sc.nextLine();

                            vetor.alterarValor(valorAlterar, posicaoAlterar);
                        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException exception) {
                            System.out.println(exception.getMessage());
                        }
                        break;

                    case 3:
                        System.out.println("Remover um valor de uma posição:");
                        System.out.println();
                        try {
                            System.out.print("Qual posição deseja remover? ");
                            int posicaoRemover = sc.nextInt();
                            sc.nextLine();
                            vetor.removerValor(posicaoRemover);
                        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 4:
                        System.out.println("Ler o conteúdo de determinada posição:");
                        System.out.println();
                        try {
                            System.out.print("Qual posição quer ler o conteúdo? ");
                            int posicaoConteudo = sc.nextInt();
                            sc.nextLine();
                            vetor.lerConteudoDeUmaPosicao(posicaoConteudo);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 5:
                        System.out.print("Localizar um valor e retornar posição: ");
                        System.out.println();
                        try {
                            System.out.print("Qual valor deseja localizar? ");
                            int valorLocalizar = sc.nextInt();
                            sc.nextLine();

                            vetor.localizarValor(valorLocalizar);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 6:
                        System.out.println("Inserir na primeira posição vaga:");
                        System.out.println();
                        try {
                            System.out.print("Qual valor deseja inserir? ");
                            int valorInserir = sc.nextInt();
                            sc.nextLine();
                            vetor.inserirNaPrimeiraPosicaoVaga(valorInserir);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 7:
                        System.out.println("Remover da última posição ocupada: ");
                        System.out.println("Vetor atual: ");
                        vetor.imprimirConteudo();
                        vetor.removerDaUltimaPosicaoOcupada();
                        System.out.println("Vetor após a operação: ");
                        vetor.imprimirConteudo();
                        break;

                    case 8:
                        System.out.print("Vetor atual: ");
                        vetor.imprimirConteudo();
                        break;

                    case 9:
                        System.out.println("Deseja sair?");
                        System.out.println("1 - sim\n" + "2 - não");
                        int escolhaUsuario = sc.nextInt();
                        sc.nextLine();
                        if (escolhaUsuario == 1) {
                            System.out.println("Até a Próxima!");
                            programaEmExecucao = false;
                        }
                        break;

                    default:
                        System.out.println("Entrada iválida. Por favor, escolha uma das opções.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Entrada de dados inválida. Digite um número!");
                sc.nextLine();
            }
        }
    }


}