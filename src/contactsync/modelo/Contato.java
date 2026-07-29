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
}
