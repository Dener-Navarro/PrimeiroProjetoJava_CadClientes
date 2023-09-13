package br.com.dnavarro.dao;

import br.com.dnavarro.domain.Cliente;

import java.util.Collection;

/**
 * Define a interface para operações de banco de dados relacionadas a clientes.
 *
 * @author dener.navarro
 */


    /**
     * Altera os dados de um cliente no banco de dados.
     *
     * @param cliente O cliente com os dados atualizados.
     */
    public interface IClienteDAO {

        public Boolean cadastrar(Cliente cliente);

        public void excluir(Long cpf);

        public void alterar(Cliente cliente);

        public Cliente consultar(Long cpf);

        public Collection<Cliente> buscarTodos();
    }