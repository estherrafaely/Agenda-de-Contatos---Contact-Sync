package contactsync.servico;
import contactsync.repositorio.RepositorioContato;
import java.util.ArrayList;
import contactsync.modelo.Contato;
import contactsync.excecao.CampoObrigatorioException;
import contactsync.excecao.ContatoDuplicadoException;
import contactsync.excecao.ContatoNaoEncontradoException;
import contactsync.excecao.ArquivoException;
import contactsync.repositorio.ArquivoContato;
import contactsync.validacao.ValidadorContato;

//Executa todas as regra de negocio
public class Agenda {

    private ArrayList<Contato> contatos;
    private RepositorioContato repositorio;

    public Agenda(){

        contatos = new ArrayList<>();

        repositorio = new ArquivoContato("contato.csv");
    }

    public void adicionarContato(Contato contato) throws CampoObrigatorioException, ContatoDuplicadoException {
        //Valida os campos obrigatórios
        ValidadorContato.validarNome(contato.getNome());
        ValidadorContato.validarTelefone(contato.getTelefone());
        ValidadorContato.validarEmail(contato.getEmail());
        ValidadorContato.validarCategoria(contato.getCategoria());

        //Verifica se já existe um contato com o mesmo telefone ou e-mail
        for (Contato c : contatos) {

            if (c.getTelefone().equals(contato.getTelefone())
                || c.getEmail().equalsIgnoreCase(contato.getEmail())) {

                throw new ContatoDuplicadoException("Contato já cadastrado");
            }
        }

        // Adicionar o contato
        contatos.add(contato);
    }

    public void editarContato(String nome, Contato contatoAtualizado) throws ContatoNaoEncontradoException{

        //Percorre todos os contatos da agenda
        for(Contato c : contatos) {

            //Verifica se o nome informado é igual ao do contato
            if (c.getNome().equalsIgnoreCase(nome)) {

                //Atualiza os dados do contato
                c.setNome(contatoAtualizado.getNome());
                c.setTelefone(contatoAtualizado.getEmail());
                c.setEmail(contatoAtualizado.getCategoria());
                c.setCategoria(contatoAtualizado.getCategoria());
                c.setEndereco(contatoAtualizado.getEndereco());
                c.setObservacoes(contatoAtualizado.getObservacoes());
                c.setFavorito(contatoAtualizado.isFavorito());

                //Encerrar o metodo pós editar
                return;
            }
        }

        //Se não encontrou o contato
        throw new ContatoNaoEncontradoException("Contato não encontrado.");
    }
    //*
    public boolean excluirContato(String nome) throws ContatoNaoEncontradoException{


    }
    //*
    public ArrayList<Contato> listarContatos(){

    }

    public ArrayList<Contato> pesquisarPorNome(String nome){

        //Lista que armazenará os contatos encontrado
        ArrayList<Contato> resultado = new ArrayList<>();

        //Percorre todos os contatos cadastrados
        for (Contato contato : contatos) {

            //Verifica se o nome pesquisado está contido no nome do contato
            if (contato.getNome().toLowerCase().contains(nome.toLowerCase())) {
                resultado.add(contato); //Adicionar o contato na lista de resultados
            }
        }
        //Retorna a lista de contatos encontrados
        return resultado;
    }

    public Contato pesquisarPorTelefone(String telefone){

        //Percorre todos os contatos da agenda
        for (Contato contato : contatos){

            //Verifica se o telefone é igual ao informado
            if(contato.getTelefone().equals(telefone)){
                return contato; //retorna o contato encontrado
            }
        }
        return null;//Retorna null caso nenhum contato seja encontrado
    }
    //*
    public Contato pesquisarPorEmail(String email){

    }

    public ArrayList<Contato> pesquisarPorCategoria(String categoria){

        //Criar uma lista para armazenar o resultado
        ArrayList<Contato> resultado = new ArrayList<>();

        //Percorre todos os contatos da agenda
        for (Contato contato : contatos){

            //Verifique se a categoria é igual a categoria pesquisada
            if(contato.getCategoria().equalsIgnoreCase(categoria)){
                resultado.add(contato); //Adiciona o contato a lista de resultado
            }
        }
        return resultado; //Retorna todos os encontrados
    }

    public ArrayList<String> listarCategorias(){

        //Criar uma lista ara armazenar as categorias sem repetição
        ArrayList<String> categorias = new ArrayList<>();

        //Percorre todos os contatos
        for (Contato contato : contatos){
            //Verifica se a categoria ainda não foi adicionada a lista
            if(!categorias.contains(contato.getCategoria())){
                //Adiciona a categoria à lista
                categorias.add(contato.getCategoria());
            }
        }
        return categorias; //Retornar todas as categorias
    }
    //*
    public void ordenarContatos(){

    }

    public void marcaFavorito(String nome) {

        //Percorre todos os contatos
        for (Contato contato : contatos){
            //Verifica se o nome informado corresponde ao contato
            if(contato.getNome().equalsIgnoreCase(nome)){
                contato.setFavorito(true); //Marca o contato favorito

                return; //Encerra o metodo
            }
        }
    }

    public void desmarcarFavorito(String nome){

        //Percorre todos os contatos
        for (Contato contato : contatos){
            //Verifica se o nome informado corresponde ao contato
            if(contato.getNome().equalsIgnoreCase(nome)){
                contato.setFavorito(false); //Desmarca o contato favorito

                return; //Encerra o metodo
            }
        }
    }
    //*
    public void salvarContatos() throws ArquivoException{

    }
    //*
    public void carregarContatos() throws ArquivoException{

    }



}
