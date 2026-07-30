package contactsync.repositorio;

import contactsync.excecao.ArquivoException;
import contactsync.modelo.Contato;
import java.util.ArrayList;

//interface que será implementada os metodos na classe ArquivoContato
public interface RepositorioContato {

    void salvar(ArrayList<Contato> contatos) throws ArquivoException;

    ArrayList<Contato> carregar() throws ArquivoException;

}