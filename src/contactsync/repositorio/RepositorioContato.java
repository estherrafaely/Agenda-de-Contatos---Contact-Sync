package contactsync.repositorio;

//imports necessarios para funcionamento correto do sistema

import contactsync.modelo.Contato;
import java.io.IOException;
import java.util.ArrayList;

//interface que será implementada os metodos na classe ArquivoContato
public interface RepositorioContato {

    void salvar(ArrayList<Contato> contatos) throws IOException;

    ArrayList<Contato> carregar() throws IOException;

}