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

    /**
     * Carrega os contatos armazenados no arquivo CSV.
     * Cada linha do arquivo é convertida novamente em um
     * objeto Contato utilizando o metodo fromCSV().
     *
     * Caso o arquivo ainda não exista, ele é criado e uma
     * lista vazia é retornada.
     *
     * @return lista de contatos carregados do arquivo.
     * @throws ArquivoException caso ocorra erro na leitura do arquivo.
     */
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

                // Lê cada linha do arquivo até o final.
                while ((linha = reader.readLine()) != null) {

                    // Ignora linhas em branco.
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