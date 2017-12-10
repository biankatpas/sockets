package br.univali.sockets;

import java.util.ArrayList;

/**
 *
 * @author biankatpas
 * @author vitorpassos
 *
 */
public class AlunosRepositorio 
{

    ArrayList<Aluno> alunos = new ArrayList<>();

    public void save(Aluno aluno) 
    {
        alunos.add(aluno);
    }

    public void delete(Aluno aluno) 
    {
        alunos.remove(aluno);
    }

    public Aluno findById(int id) 
    {
        for (int i = 0; i < alunos.size(); i++) 
        {
            if (alunos.get(i).getId() == id) 
            {
                return alunos.get(i);
            }
        }
        return null;
    }

    public ArrayList<Aluno> findAll() 
    {
        return alunos;
    }

}
