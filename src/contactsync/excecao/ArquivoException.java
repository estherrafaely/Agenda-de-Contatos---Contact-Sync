package contactsync.excecao;

// Exceção lançada quando ocorre um problema ao ler ou escrever no arquivo de contatos (ex: contatos.csv)
public class ArquivoException extends Exception {

    // Construtor que recebe apenas a mensagem de erro
    public ArquivoException(String mensagem) {
        super(mensagem); // repassa a mensagem para a classe pai (Exception)
    }
}