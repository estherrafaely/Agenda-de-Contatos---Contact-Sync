package contactsync.principal;
import contactsync.excecao.CampoObrigatorioException;
import contactsync.excecao.ContatoDuplicadoException;
import contactsync.excecao.ContatoNaoEncontradoException;
import contactsync.servico.Agenda;
import java.util.Scanner;
import contactsync.validacao.ValidadorContato;
import contactsync.modelo.Contato;
import java.util.ArrayList;

//Responsável pela interação comm o usuário.
public class Menu {

    private Agenda agenda;
    private Scanner scanner;

    public Menu(){
        agenda = new Agenda();
        scanner = new Scanner(System.in);
    }

    //Enquanto o usuario não escolher sair ele mostra o menu
    //ler a opção e chama o metodo correspondente
    public void iniciar(){

        int opcao;
        // O menu é executado pelo menos uma vez e continuará
        // sendo exibido até que o usuário escolha a opção 0 (Sair).
        do {
            // Exibe todas as opções disponíveis para o usuário.
            exibirMenu();

            // Lê a opção digitada pelo usuário.
            opcao = scanner.nextInt();
            // Consome o ENTER deixado pelo nextInt(),
            // evitando problemas na leitura de Strings com nextLine().
            scanner.nextLine();

            // Executa a ação correspondente à opção escolhida.
            switch (opcao) {

                case 1:
                    cadastrarContato();
                    break;
                case 2:
                    editarContato();
                    break;
                case 3:
                    excluirContato();
                    break;
                case 4:
                    listarContatos();
                    break;
                case 5:
                    pesquisarContato();
                    break;
                case 6:
                    favoritarContato();
                    break;
                case 7:
                    desfavoritar();
                    break;
                case 0:
                    encerrar();
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao != 0); // O laço continua enquanto a opção for diferente de 0.
    }

    public void exibirMenu(){

        System.out.println("/n===== CONTACT SYNC =====");

        System.out.println("1 - Cadastrar Contato");

        System.out.println("2 - Editar Contato");

        System.out.println("3 - Excluir Contato");

        System.out.println("4 - Listar contatos");

        System.out.println("5 - Pesquisar contato");

        System.out.println("6 - Favoritar Contato");

        System.out.println("7 - Desfavoritar contato");

        System.out.println("0 - Sair");

        System.out.println("Escolha uma opção: ");
    }
    public void cadastrarContato(){

        try{

            //Lê os dados do novo contato
            System.out.println("Nome: ");
            String nome = scanner.nextLine();

            System.out.println("Telefone: ");
            String telefone = scanner.nextLine();

            System.out.println("Email: ");
            String email = scanner.nextLine();

            //Lê e valida categoria
            String categoria = lerCategoria();

            System.out.println("Endereço: ");
            String endereco = scanner.nextLine();

            System.out.println("Obeservações: ");
            String observacoes = scanner.nextLine();

            //Cria o objeto contato utilizando o construtor
            Contato contato = new Contato(
                    nome,
                    telefone,
                    email,
                    categoria,
                    endereco,
                    observacoes,
                    false
            );

            //Envia o contato para Agenda
            agenda.adicionarContato(contato);

            //Exibir mensagem de sucesso
            System.out.println("Contato cadastrado com sucesso!");

        } catch (CampoObrigatorioException | ContatoDuplicadoException e) {
            //Exibe a mensagem da exceção
            System.out.println(e.getMessage());
        }
    }
    public void editarContato(){

        try {
            //Lê o nome do contato que será editado
            System.out.println("Nome do contato que deseja editar: ");
            String nome = scanner.nextLine();

            System.out.println("Novo nome: ");
            String novoNome = scanner.nextLine();

            System.out.println("Novo telefone: ");
            String telefone = scanner.nextLine();

            System.out.println("Novo email: ");
            String email = scanner.nextLine();

            //Lê e valida categoria
            String categoria = lerCategoria();

            System.out.println("Novo endereço: ");
            String endereco = scanner.nextLine();

            System.out.println("Novas obeservações: ");
            String observacoes = scanner.nextLine();

            //Cria o objeto com os dados atualizados
            Contato contatoAtualizado = new Contato(
                    novoNome,
                    telefone,
                    email,
                    categoria,
                    endereco,
                    observacoes,
                    false
            );

            //Envia o contato para Agenda
            agenda.editarContato(nome, contatoAtualizado);

            //Exibir mensagem de sucesso
            System.out.println("Contato cadastrado com sucesso!");

        } catch (ContatoNaoEncontradoException e) {
            //Exibe a mensagem da exceção
            System.out.println(e.getMessage());
        }
    }
    public void excluirContato(){
        //*
    }
    public void listarContatos(){
        //*
    }
    public void pesquisarContato() {

        //Exibe as opções de pesquisa
        System.out.println("\n=== PESQUISAR CONTATO ===");
        System.out.println("1 - Pesquisar por nome");
        System.out.println("2 - Pesquisar por telefone");
        System.out.println("3 - Pesquisa por e-mail");
        System.out.println("4 - Pesquisar por categoria");
        System.out.print("Escolha uma opção:");

        int opcao = scanner.nextInt();
        scanner.nextLine(); //Limpar o buffer

        switch (opcao) {

            case 1:
                //Pesquisar por nome
                System.out.println("Digite o nome:");
                String nome = scanner.nextLine();

                ArrayList<Contato> contatosNome = agenda.pesquisarPorNome(nome);

                if (contatosNome.isEmpty()) {
                    System.out.println("Nenhum contato encontrado.");
                } else {
                    for (Contato contato : contatosNome) {
                        System.out.println(contato);
                    }
                }

                break;

            case 2:
                //Pesquisar por telefone
                System.out.println("Digite o telefone: ");
                String telefone = scanner.nextLine();

                Contato contatoTelefone = agenda.pesquisarPorTelefone(telefone);

                if (contatoTelefone == null) {
                    System.out.println("Contato não encontrado.");
                } else {
                    System.out.println(contatoTelefone);
                }

                break;
            case 3:

                //Pesquisar por email
                System.out.println("Digite o e-mail: ");
                String email = scanner.nextLine();

                Contato contatoEmail = agenda.pesquisarPorEmail(email);

                if (contatoEmail == null) {
                    System.out.println("Contato não encontrado!");
                } else {
                    System.out.println(contatoEmail);
                }
                break;

            case 4:

                //Pesquisar por categoria
                String categoria = lerCategoria();

                ArrayList<Contato> contatosCategoria = agenda.pesquisarPorCategoria(categoria);

                if (contatosCategoria.isEmpty()) {
                    System.out.println("Nenhum contato encontrado");
                } else {
                    for (Contato contato : contatosCategoria) {
                        System.out.println(contato);
                    }
                }

                break;
            default:
                System.out.println("Opção inválida: ");
        }
    }
    public void favoritarContato(){

        //Pedir o nome do contato
        System.out.println("Digite o nome do contato que deseja fovoritar: ");
        String nome = scanner.nextLine();

        //Chamar o metodo da Agenda para marcar o contato como favorito
        agenda.marcaFavorito(nome);
        System.out.println("Contato favoritado com sucesso!");//Exibe mensagem de confirmação
    }
    public void desfavoritar(){

        //Pedir o nome do contato
        System.out.println("Digite o nome do contato que deseja fovoritar: ");
        String nome = scanner.nextLine();

        //Chamar o metodo da Agenda para desmarcar o contato como favorito
        agenda.desmarcarFavorito(nome);
        System.out.println("Contato desfavoritado com sucesso!");//Exibe mensagem de confirmação
    }
    public String lerCategoria(){
        //System.out.print("Digite a categoria: ");
        //String categoria = scanner.nextLine();
        //return categoria;
        while (true) {
            try {
                System.out.print("Digite a categoria: ");
                String categoria = scanner.nextLine();

                ValidadorContato.validarCategoria(categoria);

                return categoria;

            } catch (CampoObrigatorioException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void exibirCategoria(){

        System.out.println("1 - Família");

        System.out.println("2 - Amigos");

        System.out.println("3 - Trabalho");

        System.out.println("4 - Faculdade");

        System.out.println("5 - Empresa");

        System.out.println("6 - Outros");
    }
    public void encerrar(){ //Fecha o scanner e encerra.

        scanner.close();

        System.out.println("Programa encerrado");
    }

}
