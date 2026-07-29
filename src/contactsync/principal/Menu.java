package contactsync.principal;
import contactsync.servico.Agenda;
import java.util.Scanner;

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
        //*
    }
    public void editarContato(){
        //*
    }
    public void excluirContato(){
        //*
    }
    public void listarContatos(){
        //*
    }
    public void pesquisarContato(){
        //*
    }
    public void favoritarContato(){
        //*
    }
    public void desfavoritar(){
        //*
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
    //exibi categoria
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
