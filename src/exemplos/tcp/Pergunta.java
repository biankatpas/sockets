package exemplos.tcp;

/**
 *
 * @author biankatpas
 */

public class Pergunta 
{
    private String pergunta;
    private String resposta;

    public Pergunta() {}

    public Pergunta(String pergunta, String resposta) 
    {
        this.pergunta = pergunta;
        this.resposta = resposta;
    }

    public String getPergunta() 
    {
        return pergunta;
    }

    public void setPergunta(String pergunta) 
    {
        this.pergunta = pergunta;
    }

    public String getResposta() 
    {
        return resposta;
    }

    public void setResposta(String resposta) 
    {
        this.resposta = resposta;
    }
        
}
