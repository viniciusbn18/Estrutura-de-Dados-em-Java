import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TadVetor {

    private int tamanho;
    private int minimo;
    private int maximo;
    private int valorVago;
    private int repetir;
    private int[] vetor;

    public TadVetor() {
    }

    public TadVetor(int maximo, int minimo, int repetir, int tamanho, int valorVago) {
        this.maximo = maximo;
        this.minimo = minimo;
        this.repetir = repetir;
        this.tamanho = tamanho;
        this.valorVago = valorVago;
        /*
         * Preenche o vetor com o valor escolhido para representar uma posição vaga.
         * */
        this.vetor = new int[this.tamanho];
        for (int i = 0; i < this.vetor.length; i++) {
            this.vetor[i] = this.valorVago;
        }
    }
    /*====================================================================================================================*/

    /**
     * Método para verificar se o vetor admite repetição ou não
     *
     * @return true ou false
     */
    public boolean permitirRepeticao() {
        if (this.repetir == 1) {
            return true;
        }
        if (this.repetir != 2) {
            System.out.println("Entrada de dados inválida.");
        }
        return false;
    }
    /*====================================================================================================================*/

    /**
     * Método para validar entrada do usuário. Verifica se o valor está dentro do intervalo escolhido
     *
     * @param valorUsuario
     * @return true or false
     */
    public boolean verificarMinimoEmaximo(int valorUsuario) throws IllegalArgumentException {
        if (valorUsuario < this.minimo || valorUsuario > this.maximo) {
            throw new IllegalArgumentException("Valor fora do intervalo válido. "
                    + "Por favor, escolha um número dentro do intervalo: " + this.minimo + " -- " + this.maximo);
        }
        return true;
    }

    /*====================================================================================================================*/
    public boolean verificarLimitesArray(int posicao) throws ArrayIndexOutOfBoundsException {
        if (posicao < 0 || posicao > this.vetor.length) {
            throw new ArrayIndexOutOfBoundsException("Posição fora do escopo do array. "
                    + "Por favor, escolha uma posição que esteja dentro dos limites do array.");
        }
        return true;
    }
    /*====================================================================================================================*/

    /**
     * Método que atribui um valor em determinada posição do vetor
     *
     * @param valorAtribuir
     * @param posicao
     */
    public void atribuirValor(int valorAtribuir, int posicao) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {

        if (permitirRepeticao()) {
            if (verificarMinimoEmaximo(valorAtribuir) && verificarLimitesArray(posicao)) {
                if (vetor[posicao] != this.valorVago) {
                    throw new IllegalArgumentException("A posição " + posicao + " está ocupada. " +
                            "Por favor escolha uma posição vaga.");
                }
                if (vetor[posicao] == this.valorVago) {
                    vetor[posicao] = valorAtribuir;
                    System.out.println("Valor operação realizada com sucesso!");
                    return;
                }
            }
            System.out.println("Erro inesperado. Tente novamente.");
            return;
        }
        if (!permitirRepeticao()) {/*Caso não admita repetição*/
            if (verificarMinimoEmaximo(valorAtribuir) && verificarLimitesArray(posicao)) {
                for (int item : vetor) {
                    if (item == valorAtribuir) {
                        System.out.println("Valor já existente no vetor. Por favor insira um número diferente.");
                        return;
                    }
                }
                if (vetor[posicao] == this.valorVago) {
                    vetor[posicao] = valorAtribuir;
                    System.out.println("Operação realizada com sucesso!");
                    return;
                }
                if (this.vetor[posicao] != this.valorVago) {
                    System.out.println("Posição ocupada. Por favor, tente outra posição.");
                }
            }
        }
    }

    /*====================================================================================================================*/

    /**
     * Método para alterar um valor dada uma posição escolhida pelo usuário.
     *
     * @param posicao      posiçao do vetor que o usuário quer alterar
     * @param valorAlterar valor que será atribuído na posicao escolhida
     */
    public void alterarValor(int valorAlterar, int posicao) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {

        if (permitirRepeticao()) {

            if ((verificarMinimoEmaximo(valorAlterar) && verificarLimitesArray(posicao))) {
                vetor[posicao] = valorAlterar;
                System.out.println("Valor alterado com sucesso!");
                return;
            }
            System.out.println("Vaga ocupada. Por favor, tente outra posição");
            return;
        }

        if (!permitirRepeticao()) { // ** não permite repetição **

            if (verificarLimitesArray(posicao) && verificarMinimoEmaximo(valorAlterar)) {
                for (int i = 0; i < vetor.length; i++) {
                    if (valorAlterar == vetor[i]) {
                        throw new IllegalArgumentException("Número já existente no vetor. " +
                                "Por favor escolha um número diferente.");
                    }
                }
                if (vetor[posicao] != valorVago) {
                    vetor[posicao] = valorAlterar;
                    System.out.println("Posição alterada com sucesso.");
                    return;
                }
                if (vetor[posicao] == valorVago) {
                    System.out.println("Posição já está vaga. Por favor, tente outra posição.");
                }
            }

        }
    }

    /**
     * Método que remove um valor dada uma posição inserida pelo usuário
     *
     * @throws IllegalArgumentException caso uma posição já esteja vaga.
     */
    public void removerValor(int posicao) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (vetor[posicao] == valorVago) {
            throw new IllegalArgumentException("Posição já está vaga. Por favor, escolha outra posição.");
        }
           /*Caso o usuário queira remover um valor (Remover um valor, nada mais é,
            do que alterar um valor para o valor que representa uma vaga desocupada)
            */
        if (verificarLimitesArray(posicao)) {
            if (this.vetor[posicao] != this.valorVago) {
                vetor[posicao] = valorVago;
                System.out.println("Valor removido com sucesso!");
            }
        }
    }

    /**
     * Método para buscar um valor e retornar sua posição. Se o vetor adimitir repetição,
     * o método deve mostrar todas as posições.
     */
    public void localizarValor(int valor) throws IllegalArgumentException {
        List<Integer> posicoesEncontradas = new ArrayList<>();
        if (verificarMinimoEmaximo(valor) && permitirRepeticao()) {
            for (int i = 0; i < this.vetor.length; i++) {
                if (this.vetor[i] == valor) {
                    posicoesEncontradas.add(i);
                }
            }
            if (posicoesEncontradas.isEmpty()) {
                System.out.println("O valor " + valor + " não está presente na lista");
                return;
            }
            System.out.println("O valor é encontrado nas posições: " + posicoesEncontradas.stream().toList());
            return;
        }

        if (!permitirRepeticao()) {
            for (int i = 0; i < this.vetor.length; i++) {
                if (this.vetor[i] == valor) {
                    System.out.println("O valor é encontrado na posição: " + i);
                    break;
                }
            }
            System.out.println("O valor " + valor + " não está presente na lista");
        }
    }

    /**
     * Método para inserir um valor dito pelo usuário, na primeira posição vaga que o sistema encontrar no vetor
     *
     * @param valor que inserido pelo usuário
     */
    public void inserirNaPrimeiraPosicaoVaga(int valor) throws IllegalArgumentException {

        if (permitirRepeticao() && verificarMinimoEmaximo(valor)) {
            for (int i = 0; i < this.vetor.length; i++) {
                if (this.vetor[i] == valorVago) {
                    this.vetor[i] = valor;
                    System.out.println("Operação realizada com sucesso!");
                    return;
                }
            }
            System.err.println("O vetor está cheio. Remova um valor para utilizar esta função.");

        }

        if (!permitirRepeticao() && verificarMinimoEmaximo(valor)) {
            for (int i = 0; i < this.vetor.length; i++) {
                if (this.vetor[i] == valor) {
                    throw new IllegalArgumentException("Valor existente no vetor. " +
                            "Por favor, adicione um valor que não esteja previamente presente no vetor.");
                }
            }
            for (int i = 0; i < this.vetor.length; i++) {
                if (this.vetor[i] == valorVago) {
                    this.vetor[i] = valor;
                    System.out.println("Operação realizada com sucesso!");
                    return;
                }
            }
        }
    }

    /**
     * Método que remove um valor da última posição ocupada
     *
     * @return void
     */
    public void removerDaUltimaPosicaoOcupada() {
        List<Integer> arrayDeIndicesOcupados = new ArrayList<>();
        for (int i = 0; i < this.vetor.length; i++) {
            if (vetor[i] != valorVago) {
                arrayDeIndicesOcupados.add(i);
            }
        }

        if (arrayDeIndicesOcupados.isEmpty()) {
            System.out.println("Não há valores ocupados no vetor. Por favor, adicione valores para utilizar esta função.");
            return;
        }
        int ultimaPosicao = arrayDeIndicesOcupados.getLast();
        vetor[ultimaPosicao] = valorVago;
        System.out.println("Operação realizada com sucesso!");
    }

    public void lerConteudoDeUmaPosicao(int posicao) throws ArrayIndexOutOfBoundsException {
        if (this.vetor[posicao] == this.valorVago && verificarLimitesArray(posicao)) {
            System.out.println("Não há valor contido na posição " + posicao);
            return;
        }

        if (verificarLimitesArray(posicao)) {
            System.out.println("O valor contido na poisção " + posicao + " é: " + vetor[posicao]);
        }
    }

    /**
     * Método que imprime o conteúdo do vetor
     *
     * @return void
     */
    public void imprimirConteudo() {
        System.out.println(Arrays.toString(this.vetor));
    }
}


