import java.util.Scanner;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class Main {

    public static void inserirProdutos(String [][] matriz, int linhas, int colunas) {
        Scanner scanner = new Scanner(System.in);
        int linha;

        mostrarProdutos(matriz, linhas, colunas);
        do {
            System.out.println("Informe onde você deseja inserir o produto com valores de 1 à " + linhas);
            linha = scanner.nextInt();
        } while(linha < 1 || linha > linhas);

        System.out.println("Insira o nome do produto: ");
        matriz[linha-1][0] = scanner.next();
        System.out.println("Insira a quantidade de produtos: ");
        matriz[linha-1][1] = scanner.next();
        System.out.println("Insira o valor do produto: ");
        matriz[linha-1][2] = scanner.next();
        System.out.println("Produto adicionado.");
    }

    public static void mostrarProdutos(String [][] matriz, int linhas, int colunas) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Produto | Quantidade | Valor");
        for(int i = 0; i < linhas; i++) {
            System.out.print((i+1) + " - ");
            for(int j = 0; j < colunas; j++) {
                System.out.print(matriz[i][j] + " | ");

            }
            System.out.println();
        }
    }

    public static void calcularValor(String [][] matriz, int linhas) {
        Scanner scanner = new Scanner(System.in);
        Float valorFinal = 0.0f;
        for(int i = 0; i < linhas; i++) {
            if(matriz[i][0] != null) {
                valorFinal = valorFinal + Float.parseFloat(matriz[i][1]) * Float.parseFloat(matriz[i][2]);
            }
        }
        System.out.println("Valor final da lista de compras: " + valorFinal + " R$");
    }

    public static void removerProduto(String [][] matriz, int linhas, String nomeProduto) {
        boolean encontrado = false;
        for(int i = 0; i < linhas; i++) {
            if(matriz[i][0] != null && matriz[i][0].equals(nomeProduto)) {
                encontrado = true;
                matriz[i][0] = null;
                matriz[i][1] = null;
                matriz[i][2] = null;
            }
        }
        if(encontrado) {
            System.out.println("Produto removido.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }
    public static void calcularValorProduto(String[][] matriz, int linhas, String nomeProduto) {
        float valorProduto = 0.0f;
        boolean encontrado = false;

        for (int i = 0; i < linhas; i++) {
            if (matriz[i][0] != null && matriz[i][0].equals(nomeProduto)) {
                encontrado = true;
                valorProduto = Float.parseFloat(matriz[i][1]) * Float.parseFloat(matriz[i][2]);
                break;
            }
        }

        if (encontrado) {
            System.out.println("Valor do produto '" + nomeProduto + "': " + valorProduto + " R$");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public static void editarProduto(String[][] matriz, int linhas, String nomeProduto) {
        boolean encontrado = false;
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < linhas; i++) {
            if (matriz[i][0] != null && matriz[i][0].equals(nomeProduto)) {
                encontrado = true;
                System.out.println("Informe o novo nome do produto: ");
                matriz[i][0] = scanner.next();
                System.out.println("Informe a nova quantidade de produtos: ");
                matriz[i][1] = scanner.next();
                System.out.println("Informe o novo valor do produto: ");
                matriz[i][2] = scanner.next();
                break;
            }
        }

        if (encontrado) {
            System.out.println("Produto editado com sucesso.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public static void ordenarListaCompras(String[][] matriz, int linhas, int colunas) {
        for (int i = 0; i < linhas - 1; i++) {
            for (int j = 0; j < linhas - i - 1; j++) {

                if (matriz[j][0] != null && matriz[j + 1][0] != null && matriz[j][0].compareToIgnoreCase(matriz[j + 1][0]) > 0) {
                    String[] temp = matriz[j];
                    matriz[j] = matriz[j + 1];
                    matriz[j + 1] = temp;
                }
            }
        }
    }

    public static void gerarRelatorioCompras(String[][] matriz, int linhas, int colunas) {
        int quantidadeTotal = 0;
        float valorTotal = 0.0f;
        int quantidadeProdutos = 0;
        float mediaPrecos = 0.0f;

        for (int i = 0; i < linhas; i++) {
            if (matriz[i][0] != null) {
                int quantidade = Integer.parseInt(matriz[i][1]);
                float valor = Float.parseFloat(matriz[i][2]);

                quantidadeTotal += quantidade;
                valorTotal += quantidade * valor;
                quantidadeProdutos++;
            }
        }

        if (quantidadeProdutos > 0) {
            mediaPrecos = valorTotal / quantidadeProdutos;
        }

        System.out.println("Relatório de Compras");
        System.out.println("Quantidade Total de Produtos: " + quantidadeTotal);
        System.out.println("Valor Total da Compra: " + valorTotal + " R$");
        System.out.println("Média de Preços: " + mediaPrecos + " R$");
    }
    public static void copiarListaParaAreaTransferencia(String[][] matriz, int linhas, int colunas) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                sb.append(matriz[i][j]);

                if (j < colunas - 1) {
                    sb.append("\t");
                }
            }

            sb.append(System.lineSeparator());
        }

        StringSelection stringSelection = new StringSelection(sb.toString());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        System.out.println("A lista de compras foi copiada para a área de transferência.");
    }

    public static void main(String[] args) {
        String [][] lista;
        int produtos, opcao;
        int colunas = 3;
        Scanner scanner = new Scanner(System.in);
        String nomeProduto;

        System.out.println("Insira a quantidade de produtos que serão inseridos na sua lista de compras:");
        produtos = scanner.nextInt();
        lista = new String[produtos][colunas];

        do {
            System.out.println("Escolha uma opção: \n 1 - Mostrar lista de compras. \n 2 - Inserir produtos na lista de compras. \n 3 - Calcular valor dos produtos da lista de compras. \n 4 - Remover produto da lista de compras. \n 5 - Calcular valor de apenas um produto. \n 6 - Editar um produto. \n 7 - Ordenar lista de compras por ordem alfabética.  \n 8 - Gerar um relatório de compras. \n 9 - Copiar lista de compras para a área de transferência. \n 0 - Sair.");
            opcao = scanner.nextInt();
            switch(opcao) {
                case 0:
                    break;
                case 1:
                    mostrarProdutos(lista, produtos, colunas);
                    break;
                case 2:
                    inserirProdutos(lista, produtos, colunas);
                    break;
                case 3:
                    calcularValor(lista, produtos);
                    break;
                case 4:
                    System.out.println("Insira o produto a ser removido.");
                    nomeProduto = scanner.next();
                    removerProduto(lista, produtos, nomeProduto);
                    break;
                case 5:
                    System.out.println("Insira o nome do produto para calcular o valor: ");
                    nomeProduto = scanner.next();
                    calcularValorProduto(lista, produtos, nomeProduto);
                    break;

                case 6:
                    System.out.println("Insira o nome do produto a ser editado: ");
                    nomeProduto = scanner.next();
                    editarProduto(lista, produtos, nomeProduto);
                    break;
                case 7:
                    ordenarListaCompras(lista, produtos, colunas);
                    System.out.println("Lista de compras ordenada com sucesso.");
                    break;
                case 8:
                    gerarRelatorioCompras(lista, produtos, colunas);
                    break;
                case 9:
                    copiarListaParaAreaTransferencia(lista, produtos, colunas);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
}