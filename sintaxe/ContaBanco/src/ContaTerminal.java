
import java.util.Scanner;

public class ContaTerminal {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        try {
            System.out.println("Olá, para começar digite o número da sua agência: ");
            String agencia = input.nextLine();

            System.out.println("Agora digite o número da sua conta: ");
            int numero_conta = input.nextInt();
            input.nextLine();
            System.out.println("Agora digite o seu nome: ");
            String nome = input.nextLine();

            System.out.println("Agora digite o seu saldo: ");
            double saldo = input.nextDouble();

            System.out.println(String.format(
                    "Olá %s, obrigado por criar uma conta em nosso banco, sua agência é %s, conta %d e seu saldo %.2f já está disponível para saque.", nome, agencia, numero_conta, saldo));
        } catch (NumberFormatException e) {
            System.err.println("Número em formato incorreto.");
        }

    }
}
