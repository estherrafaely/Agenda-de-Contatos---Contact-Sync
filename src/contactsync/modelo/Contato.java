package contactsync.modelo;

//Classe que representa um contato na agenda.
public class Contato {

    private String nome;
    private String telefone;
    private String email;
    private String categoria;
    private String endereco;
    private String observacoes;
    private boolean favorito;

    public Contato(String nome, String telefone, String email, String categoria, String endereco, String observacoes, boolean favorito){
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.categoria = categoria;
        this.endereco = endereco;
        this.observacoes = observacoes;
        this.favorito = favorito;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    @Override
    public String toString(){
        return "----------------------------" +
                    "\nNome: " + nome +
                    "\nTelefone: " + telefone +
                    "\nE-mail: " + email +
                    "\nCategoria: " + categoria +
                    "\nEndereço: " + endereco +
                    "\nObservações: " + observacoes +
                    "\nFavorito: " + (favorito ? "Sim" : "Não") +
                    "\n----------------------------";
    }


    public String toCSV() { // Vai converte o contato para uma linha no formato CSV
        return nome + ";" +
                telefone + ";" +
                email + ";" +
                categoria + ";" +
                endereco + ";" +
                observacoes + ";" +
                favorito;
    }

    public static Contato fromCSV(String linha) { //Vai cria um contato a partir de uma linha do arquivo CSV

        String[] dados = linha.split(";");// Divide a linha do arquivo em partes usando ";" como separador

        return new Contato( // Cria e retorna um novo objeto Contato com os dados lidos
                dados[0],
                dados[1],
                dados[2],
                dados[3],
                dados[4],
                dados[5],
                Boolean.parseBoolean(dados[6])
        );
    }

}
