package contactsync.repositorio;

import contactsync.excecao.ArquivoException;
import contactsync.modelo.Contato;

import java.io.*;
import java.util.ArrayList;

public class ArquivoContato implements RepositorioContato {

    // Caminho do arquivo onde os contatos serão armazenados.
    private String caminhoArquivo;

    //Construtor da classe, Inicializa o caminho do arquivo onde os contatos serão armazenados.
    public ArquivoContato(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    // Salva todos os contatos da agenda no arquivo CSV.
    // Cada contato é convertido para uma linha de texto usando o metodo toCSV().
    @Override
    public void salvar(ArrayList<Contato> contatos) throws ArquivoException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {

            for (Contato contato : contatos) {


                writer.write(contato.toCSV());
                writer.newLine();
            }

        } catch (IOException e) {
            throw new ArquivoException(
                    "Não foi possível salvar os contatos no arquivo: " + e.getMessage());
        }
    }
    // Carrega os contatos do arquivo CSV.
    // Converte cada linha em um objeto Contato usando fromCSV().
    // Se o arquivo não existir, cria um novo arquivo vazio.
    // Retorna a lista de contatos carregados.
    // Lança ArquivoException em caso de erro na leitura.
    @Override
    public ArrayList<Contato> carregar() throws ArquivoException {

        ArrayList<Contato> contatos = new ArrayList<>();

        File arquivo = new File(caminhoArquivo);

        try {
            // Cria o arquivo caso ele ainda não exista.
            if (!arquivo.exists()) {
                arquivo.createNewFile();
                return contatos;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {

                String linha;

                // Lê e ignora a primeira linha (cabeçalho)
                reader.readLine();

                while ((linha = reader.readLine()) != null) {

                    if (!linha.isBlank()) {
                        contatos.add(Contato.fromCSV(linha));
                    }
                }
            }

        } catch (IOException e) {
            throw new ArquivoException(
                    "Não foi possível carregar os contatos do arquivo: " + e.getMessage());
        }

        return contatos;
    }
}