package contactsync.validacao;

import contactsync.excecao.CampoObrigatorioException;
import contactsync.excecao.ContatoDuplicadoException;
import contactsync.modelo.Contato;

import java.util.ArrayList;

public class ValidadorContato {

    // Valida todas as informações do contato
    public void validarContato(Contato contato, ArrayList<Contato> contatos)
            throws CampoObrigatorioException, ContatoDuplicadoException {

        validarNome(contato.getNome());

        validarTelefone(contato.getTelefone());

        validarEmail(contato.getEmail());

        validarCategoria(contato.getCategoria());

        validarEndereco(contato.getCategoria(),
                contato.getEndereco());

        validarDuplicidade(contato, contatos);
    }

    // Valida o nome
    public static void validarNome(String nome)
            throws CampoObrigatorioException {

        if (nome == null || nome.isBlank()) {
            throw new CampoObrigatorioException(
                    "O nome é obrigatório.");
        }
    }

    // Valida o telefone
    public static void validarTelefone(String telefone)
            throws CampoObrigatorioException {

        if (telefone == null || telefone.isBlank()) {
            throw new CampoObrigatorioException(
                    "O telefone é obrigatório.");
        }

        if (!telefone.matches("\\d{8,15}")) {
            throw new CampoObrigatorioException(
                    "Telefone inválido.");
        }
    }

    // Valida o e-mail (opcional, mas quando informado deve ser válido)
    public static void validarEmail(String email)
            throws CampoObrigatorioException {

        if (email == null || email.isBlank()) {
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new CampoObrigatorioException(
                    "E-mail inválido.");
        }
    }

    // Valida categoria
    public static void validarCategoria(String categoria)
            throws CampoObrigatorioException {

        // Verifica se a categoria foi informada
        if (categoria == null || categoria.isBlank()) {
            throw new CampoObrigatorioException(
                    "Categoria obrigatória.");
        }
    }

    // Endereço é opcional
    public static void validarEndereco(String categoria,
                                String endereco)
            throws CampoObrigatorioException {

        if (categoria.equalsIgnoreCase("Empresa") &&
                (endereco == null || endereco.isBlank())) {

            throw new CampoObrigatorioException(
                    "O endereço é obrigatório para contatos da categoria Empresa.");
        }
    }

    // Verifica duplicidade de telefone e e-mail
    public void validarDuplicidade(Contato contato,
                                   ArrayList<Contato> contatos)
            throws ContatoDuplicadoException {

        for (Contato c : contatos) {

            // Verifica telefone duplicado
            if (c.getTelefone() != null &&
                    c.getTelefone().equals(contato.getTelefone())) {

                throw new ContatoDuplicadoException(
                        "Já existe um contato com esse telefone.");
            }

            // Verifica e-mail duplicado apenas se ele foi informado
            if (contato.getEmail() != null &&
                    !contato.getEmail().isBlank() &&
                    c.getEmail() != null &&
                    c.getEmail().equalsIgnoreCase(contato.getEmail())) {

                throw new ContatoDuplicadoException(
                        "Já existe um contato com esse e-mail.");
            }
        }
    }
}