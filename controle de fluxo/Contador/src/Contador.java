import java.util.Scanner;
import exceptions.ParametrosInvalidosException;

public class Contador {
    public static void main(String[] args) throws Exception {
        Scanner terminal = new Scanner(System.in);
        System.out.println("Digite o primeiro parâmetro");
        int parametroUm = terminal.nextInt();
        System.out.println("Digite o segundo parâmetro");
        int parametroDois = terminal.nextInt();

        try {
            contar(parametroUm, parametroDois);
        } catch (ParametrosInvalidosException e) {
            System.err.println(e.getMessage());
        }
        terminal.close();
    }

    public static void contar(int num1, int num2) throws ParametrosInvalidosException {
        if(num1 > num2)
            throw new ParametrosInvalidosException();
        final int contagem = num2 - num1;

        for (int i = 1; i <= contagem; i ++){
            System.out.println("Imprimindo o n\u00FAmero: " + i);
        }
    }
}
