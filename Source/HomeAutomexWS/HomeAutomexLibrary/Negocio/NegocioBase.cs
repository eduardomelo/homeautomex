using HomeAutomexLibrary.Entidade;
using HomeAutomexLibrary.Repositorio;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Negocio
{
    public class NegocioBase<TEntidade, TChave>
        where TEntidade : EntidadeBase<TChave>
    {
        RepositorioBase<TEntidade, TChave> repositorio;

        public NegocioBase(RepositorioBase<TEntidade, TChave> repositorio)
        {
            this.repositorio = repositorio;
        }

        public TEntidade BuscarPorChave(TChave chave)
        {
            return repositorio.BuscarPorChave(chave);
        }

        public TEntidade BuscarPrimeiro(Expression<Func<TEntidade, bool>> criterios)
        {
            return repositorio.BuscarPrimeiro(criterios);
        }

        public TEntidade Buscar(Expression<Func<TEntidade, bool>> criterios)
        {
            return repositorio.Buscar(criterios);
        }

        public void Remover(TEntidade entidade)
        {
            repositorio.Remover(entidade);
        }

        public void RemoverPorChave(TChave chave)
        {            
            repositorio.RemoverPorChave(chave);
        }

        public void Inserir(TEntidade entidade)
        {
            repositorio.Inserir(entidade);
        }

        public void Alterar(TEntidade entidade)
        {
            repositorio.Alterar(entidade);
        }

        public IEnumerable<TEntidade> Consultar(Expression<Func<TEntidade, bool>> criterios)
        {
            return repositorio.Consultar(criterios);
        }

        public IEnumerable<TEntidade> ConsultarTodos()
        {
            return repositorio.ConsultarTodos();
        }

        public int ContarTodos()
        {
            return repositorio.ContarTodos();
        }

        public int Contar(Expression<Func<TEntidade, bool>> criterios)
        {
            return repositorio.Contar(criterios);
        }

        public bool Existir(Expression<Func<TEntidade, bool>> criterios)
        {
            return repositorio.Existir(criterios);
        }

        public void SaveChanges()
        {
            repositorio.SaveChanges();
        }
    }
}
