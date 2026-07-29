package contactsync.excecao;

// Exceção lançada quando se tenta buscar, editar ou remover um contato que não existe na agenda
public class ContatoNaoEncontradoException extends Exception {

    // Construtor que recebe a mensagem indicando qual contato não foi encontrado
    public ContatoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}