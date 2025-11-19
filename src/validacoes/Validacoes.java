package validacoes;

import dominio.Pet;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validacoes {
    public static final String NAO_INFORMADO = "Nao Informado";

    public String lerNumeroDeCasaValido(Scanner input) {
        while (true) {
            String entradaDoUsuario = input.nextLine().trim();

            if (entradaDoUsuario.isBlank()) {
                return Pet.NAO_INFORMADO;
            }

            try {
                Integer.parseInt(entradaDoUsuario);
                return entradaDoUsuario;

            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada inválida! Digite um número.");
            }
        }
    }

    public int lerDoisNumValidos(Scanner input) {
        try {
            System.out.println("Digite um numero: ");
            int numValido = input.nextInt();
            if (numValido == 1 || numValido == 2) {
                input.nextLine();
                return numValido;
            } else {
                System.out.println("Entrada inválida! Digite um número valido.");
                return lerDoisNumValidos(input);
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Digite um número valido.");
            input.nextLine();
            return lerDoisNumValidos(input);
        }
    }

    public String lerIdadeValida(Scanner input) {

        while (true) {

            String entradaDoUsuario = input.nextLine().trim();

            if (entradaDoUsuario.isBlank()) {
                return Pet.NAO_INFORMADO;
            }

            try {
                entradaDoUsuario = entradaDoUsuario.replace(",", ".");
                float idade = Float.parseFloat(entradaDoUsuario);
                if (idade > 20) {
                    throw new IllegalArgumentException("Idade superior a 20 anos! ");
                }
                return String.valueOf(idade);
            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada inválida! Digite um número.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String lerPesoValido(Scanner input) {

        while (true) {

            String entradaDoUsuario = input.nextLine();

            if (entradaDoUsuario.isBlank()) {
                return Pet.NAO_INFORMADO;
            }

            try {
                entradaDoUsuario = entradaDoUsuario.replace(",", ".");
                float peso = Float.parseFloat(entradaDoUsuario);
                if(peso < 0.5 || peso > 60) {
                    throw new IllegalArgumentException("Peso inválido! Deve ser entre 0.5kg e 60kg.");
                }
                return String.valueOf(peso);
            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada inválida! Digite um número.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int lerNValido(Scanner input) {
        try {
            System.out.print("Digite um número: ");
            int numValido = input.nextInt();
            input.nextLine();
            return numValido;
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Digite um número valido.");
            input.nextLine();
            return lerNValido(input);
        }
    }

    public String lerNomeValido(Scanner input) {
        try {
            Pet pet = new Pet();
            String nomeInformado = input.nextLine();
            return pet.setPetNome(nomeInformado);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage() + " Tente novamente.");
            return lerNomeValido(input);
        }
    }
}
