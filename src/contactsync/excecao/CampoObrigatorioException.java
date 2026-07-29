package contactsync.excecao;

//Herda de Exception
public class CampoObrigatorioException extends Exception {

    //Construtor da exceção
    public CampoObrigatorioException(String mensagem) {
        super(mensagem); //Envia a mensagem para a classe Exception
    }


}
