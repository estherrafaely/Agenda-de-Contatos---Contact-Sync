package contactsync.servico;
import contactsync.repositorio.RepositorioContato;
import java.util.ArrayList;
import java.util.Comparator;
import contactsync.validacao.ValidadorContato;
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

    public Agenda() {

        repositorio = new ArquivoContato("dados/contatos.csv");

        try {
            contatos = repositorio.carregar();
        } catch (ArquivoException e) {
            contatos = new ArrayList<>();
        }
    }

    public void adicionarContato(Contato contato)
            throws CampoObrigatorioException,
            ContatoDuplicadoException,
            ArquivoException {

        System.out.println("Recebido: " + contato);

        ValidadorContato.validarNome(contato.getNome());
        ValidadorContato.validarTelefone(contato.getTelefone());
        ValidadorContato.validarEmail(contato.getEmail());
        ValidadorContato.validarCategoria(contato.getCategoria());

        for (Contato c : contatos) {
            if (c.getTelefone().equals(contato.getTelefone())
                    || c.getEmail().equalsIgnoreCase(contato.getEmail())) {

                throw new ContatoDuplicadoException("Contato já cadastrado.");
            }
        }

        contatos.add(contato);

        repositorio.salvar(contatos);
    }

    public void editarContato(String nome, Contato contatoAtualizado)
            throws ContatoNaoEncontradoException, ArquivoException {

        for (Contato c : contatos) {

            if (c.getNome().equalsIgnoreCase(nome)) {

                c.setNome(contatoAtualizado.getNome());
                c.setTelefone(contatoAtualizado.getTelefone());
                c.setEmail(contatoAtualizado.getEmail());
                c.setCategoria(contatoAtualizado.getCategoria());
                c.setEndereco(contatoAtualizado.getEndereco());
                c.setObservacoes(contatoAtualizado.getObservacoes());
                c.setFavorito(contatoAtualizado.isFavorito());

                repositorio.salvar(contatos);

                return;
            }
        }

        throw new ContatoNaoEncontradoException("Contato não encontrado.");
    }

    public boolean excluirContato(String nome)
            throws ContatoNaoEncontradoException, ArquivoException {

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

        repositorio.salvar(contatos);

        return true;
    }

    public ArrayList<Contato> listarContatos(){
        ordenarContatos();
        return contatos;
    }

    public ArrayList<Contato> pesquisarPorNome(String nome){

        // Lista que armazenará os contatos encontrados
        ArrayList<Contato> resultado = new ArrayList<>();

        // Percorre todos os contatos cadastrados
        for (Contato contato : contatos) {

            // Verifica se o nome pesquisado está contido no nome do contato
            if (contato.getNome().toLowerCase().contains(nome.toLowerCase())) {

                // Adiciona o contato na lista de resultados
                resultado.add(contato);
            }
        }

        // Retorna a lista de contatos encontrados
        return resultado;
    }

    public Contato pesquisarPorTelefone(String telefone){

        // Percorre todos os contatos da agenda
        for (Contato contato : contatos) {

            // Verifica se o telefone é igual ao informado
            if (contato.getTelefone().equals(telefone)) {

                // Retorna o contato encontrado
                return contato;
            }
        }

        // Retorna null caso nenhum contato seja encontrado
        return null;

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

        // Lista para armazenar os contatos encontrados
        ArrayList<Contato> resultado = new ArrayList<>();

        // Percorre todos os contatos da agenda
        for (Contato contato : contatos) {

            // Verifica se a categoria do contato é igual à categoria pesquisada
            if (contato.getCategoria().equalsIgnoreCase(categoria)) {

                // Adiciona o contato à lista de resultados
                resultado.add(contato);
            }
        }

        // Retorna todos os contatos encontrados
        return resultado;
    }

    public ArrayList<String> listarCategorias(){
        // Lista que armazenará as categorias sem repetição
        ArrayList<String> categorias = new ArrayList<>();

        // Percorre todos os contatos
        for (Contato contato : contatos) {

            // Verifica se a categoria ainda não foi adicionada
            if (!categorias.contains(contato.getCategoria())) {

                // Adiciona a categoria à lista
                categorias.add(contato.getCategoria());
            }
        }

        // Retorna todas as categorias encontradas
        return categorias;
    }

    public void ordenarContatos(){
        contatos.sort(Comparator.comparing(Contato::getNome, String.CASE_INSENSITIVE_ORDER));
    }

    public void marcaFavorito(String nome)
            throws ArquivoException {

        for (Contato contato : contatos) {

            if (contato.getNome().equalsIgnoreCase(nome)) {

                contato.setFavorito(true);

                repositorio.salvar(contatos);

                return;
            }
        }
    }

    public void desmarcarFavorito(String nome)
            throws ArquivoException {

        for (Contato contato : contatos) {

            if (contato.getNome().equalsIgnoreCase(nome)) {

                contato.setFavorito(false);

                repositorio.salvar(contatos);

                return;
            }
        }
    }

    public void salvarContatos() throws ArquivoException{
        repositorio.salvar(contatos);
    }

    public void carregarContatos() throws ArquivoException{
        contatos = repositorio.carregar();
    }

}
