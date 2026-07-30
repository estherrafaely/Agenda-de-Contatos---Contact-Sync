package contactsync.servico;
import contactsync.repositorio.RepositorioContato;
import java.util.ArrayList;
import contactsync.modelo.Contato;
import contactsync.excecao.CampoObrigatorioException;
import contactsync.excecao.ContatoDuplicadoException;
import contactsync.excecao.ContatoNaoEncontradoException;
import contactsync.excecao.ArquivoException;
import contactsync.repositorio.ArquivoContato;

//Executa todas as regra de negocio
public class Agenda {

    private ArrayList<Contato> contatos;
    private RepositorioContato repositorio;

    public Agenda(){

        contatos = new ArrayList<>();

        repositorio = new ArquivoContato("contato.txt")
    }

    public void adicionarContato(Contato contato) throws CampoObrigatorioException, ContatoDuplicadoException {

    }

    public void editarContato(String nome, Contato contatoAtualizado) throws ContatoNaoEncontradoException{

    }

    public boolean excluirContato(String nome) throws ContatoNaoEncontradoException{

    }

    public ArrayList<Contato> listarContatos(){

    }

    public ArrayList<Contato> pesquisarPorNome(String nome){

    }

    public Contato pesquisarPorTelefone(String telefone){

    }

    public Contato pesquisarPorEmail(String email){

    }

    public ArrayList<Contato> pesquisarPorCategoria(String categoria){

    }

    public ArrayList<String> listarCategorias(){

    }

    public void ordenarContatos(){

    }

    public void marcaFavorito(String nome) {

    }

    public void desmarcarFavorito(String nome){

    }

    public void salvarContatos() throws ArquivoException{

    }

    public void carregarContatos() throws ArquivoException{

    }



}
