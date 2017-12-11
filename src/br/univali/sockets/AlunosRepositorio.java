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
    private static AlunosRepositorio instance;
    
    ArrayList<Aluno> alunos; 
    private int next_id;

    private AlunosRepositorio() {
        alunos = new ArrayList<>();
        next_id = 0;
    }

    public static AlunosRepositorio getInstance(){
        if(instance!=null)
            return instance;
        return new AlunosRepositorio();
    }
    
    public void save(Aluno aluno) 
    {
        aluno.setId(next_id++);
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

    public int getNext_id() {
        return next_id;
    }
}
