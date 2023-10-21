package devandroid.daniel.applistacurso.model;

public class Pessoa {

    private String primeiroNome;
    private String sobreNome;
    private String cursoDesejao;
    private String telefoneContato;


    public Pessoa(){

    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public String getCursoDesejao() {
        return cursoDesejao;
    }

    public void setCursoDesejao(String cursoDesejao) {
        this.cursoDesejao = cursoDesejao;
    }

    public String getTelefoneContato() {
        return telefoneContato;
    }

    public void setTelefoneContato(String telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "primeiroNome='" + primeiroNome + '\'' +
                ", sobreNome='" + sobreNome + '\'' +
                ", cursoDesejao='" + cursoDesejao + '\'' +
                ", telefoneContato='" + telefoneContato + '\'' +
                '}';
    }
}
