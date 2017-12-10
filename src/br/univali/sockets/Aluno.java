package br.univali.sockets;

import java.net.InetAddress;

/**
 *
 * @author biankatpas
 * @author vitorpassos
 *
 */
public class Aluno {

    private int id;
    private String nome;
    private InetAddress address;
    private int port;

    public Aluno(String nome, InetAddress address, int port) {
        this.nome = nome;
        this.address = address;
        this.port = port;
    }
      
    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
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
