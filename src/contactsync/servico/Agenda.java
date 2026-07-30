package contactsync.servico;
import contactsync.repositorio.RepositorioContato;
import java.util.ArrayList;
import java.util.Comparator;

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

        repositorio = new ArquivoContato("contato.txt");
    }

    public void adicionarContato(Contato contato) throws CampoObrigatorioException, ContatoDuplicadoException {

    }

    public void editarContato(String nome, Contato contatoAtualizado) throws ContatoNaoEncontradoException{

    }

    public boolean excluirContato(String nome) throws ContatoNaoEncontradoException {

        boolean removido = false;

        for (int i = 0; i < contatos.size(); i++) {

            if (contatos.get(i).getNome().equalsIgnoreCase(nome)) {

                contatos.remove(i);
                removido = true;
                break;
            }
        }

        if (!removido) {
            throw new ContatoNaoEncontradoException("Contato não encontrado.");
        }

        return true;
    }

    public ArrayList<Contato> listarContatos(){
        ordenarContatos();
        return contatos;
    }
    public ArrayList<Contato> pesquisarPorNome(String nome){

    }

    public Contato pesquisarPorTelefone(String telefone){

    }

    public Contato pesquisarPorEmail(String email){

        for (Contato c : contatos) {
            if (c.getEmail() != null && c.getEmail().equalsIgnoreCase(email)) {
                return c;
            }
        }

        return null;
    }

    public ArrayList<Contato> pesquisarPorCategoria(String categoria){

    }

    public ArrayList<String> listarCategorias(){

    }

    public void ordenarContatos(){
        contatos.sort(Comparator.comparing(Contato::getNome, String.CASE_INSENSITIVE_ORDER));
    }

    public void marcaFavorito(String nome) {

    }

    public void desmarcarFavorito(String nome){

    }

    public void salvarContatos() throws ArquivoException{
        repositorio.salvar(contatos);
    }

    public void carregarContatos() throws ArquivoException{
        contatos = repositorio.carregar();
    }

}
