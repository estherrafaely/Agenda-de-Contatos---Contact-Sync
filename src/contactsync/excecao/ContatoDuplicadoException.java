package contactsync.excecao;

//Herda de Exception
public class ContatoDuplicadoException extends Exception{
    //Construtor da exceção
    public ContatoDuplicadoException(String mensagem) {
         super(mensagem);
    }
}
