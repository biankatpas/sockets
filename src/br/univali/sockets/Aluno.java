package br.univali.sockets;

/**
 *
 * @author biankatpas
 * @author vitorpassos
 *
 */
public class Aluno {

    private int id;
    private String nome;
    private String address;
    private int port;

    public Aluno() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
