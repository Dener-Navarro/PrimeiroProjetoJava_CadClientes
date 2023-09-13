package br.com.dnavarro;

import br.com.dnavarro.dao.ClienteMapDAO;
import br.com.dnavarro.dao.IClienteDAO;
import br.com.dnavarro.domain.Cliente;

import javax.swing.*;

/**
 * Classe de ponto de entrada da aplicação.
 *
 * Esta classe permite ao usuário cadastrar, consultar, excluir e alterar clientes usando um DAO de mapa.
 *
 * @author dener.navarro
 */
public class App {

    private static IClienteDAO iClienteDAO;

    public static void main(String args[]) {
        iClienteDAO = new ClienteMapDAO();

        String opcao = JOptionPane.showInputDialog(null,
                "Digite 1 para cadastro, 2 para consulta, 3 para exclusão, 4 para alteração ou 5 para sair",
                "Cadastro", JOptionPane.INFORMATION_MESSAGE);

        while (!isOpcaoValida(opcao)) {
            if ("".equals(opcao)) {
                sair();
            }
            opcao = JOptionPane.showInputDialog(null,
                    "Opção inválida. Digite 1 para cadastro, 2 para consulta, 3 para exclusão, 4 para alteração ou 5 para sair",
                    "Green dinner", JOptionPane.INFORMATION_MESSAGE);
        }

        while (isOpcaoValida(opcao)) {
            if (isOpcaoSair(opcao)) {
                sair();
            } else if (isCadastro(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite os dados do cliente separados por vírgula, conforme exemplo: Nome, CPF, Telefone, Endereço, Número, Cidade e Estado",
                        "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                cadastrar(dados); // Realiza o cadastro de um cliente com base nos dados fornecidos.
            } else if (isConsultar(opcao)) {
                String cpf = JOptionPane.showInputDialog(null,
                        "Digite o CPF do cliente",
                        "Consultar", JOptionPane.INFORMATION_MESSAGE);

                consultar(cpf); // Realiza a consulta de um cliente com base no CPF fornecido.
            }

            opcao = JOptionPane.showInputDialog(null,
                    "Digite 1 para cadastro, 2 para consulta, 3 para exclusão, 4 para alteração ou 5 para sair",
                    "Green dinner", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void consultar(String cpf) {
        try {
            Long cpfLong = Long.parseLong(cpf);
            Cliente cliente = iClienteDAO.consultar(cpfLong);
            if (cliente != null) {
                JOptionPane.showMessageDialog(null, "Cliente encontrado:\n" + cliente.toString(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "CPF inválido. Digite apenas números.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static boolean isConsultar(String opcao) {
        return "2".equals(opcao); // Verifica se a opção escolhida é para consulta.
    }

    private static void cadastrar(String dados) {
        String[] dadosSeparados = dados.split(",");
        if (dadosSeparados.length == 7) {
            Cliente cliente = new Cliente(dadosSeparados[0], dadosSeparados[1], dadosSeparados[2], dadosSeparados[3], dadosSeparados[4], dadosSeparados[5], dadosSeparados[6]);
            Boolean isCadastrado = iClienteDAO.cadastrar(cliente);
            if (isCadastrado) {
                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Cliente já se encontra cadastrado.", "Erro", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Dados inválidos. Certifique-se de que você forneceu todos os campos necessários.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static boolean isCadastro(String opcao) {
        return "1".equals(opcao); // Verifica se a opção escolhida é para cadastro.
    }

    private static boolean isOpcaoSair(String opcao) {
        return "5".equals(opcao); // Verifica se a opção escolhida é para sair.
    }

    private static void sair() {
        JOptionPane.showMessageDialog(null, "Até logo!", "Sair", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0); // Encerra a aplicação.
    }

    private static boolean isOpcaoValida(String opcao) {
        return "1".equals(opcao) || "2".equals(opcao) || "3".equals(opcao) || "4".equals(opcao) || "5".equals(opcao); // Verifica se a opção escolhida é válida.
    }
}
