using HomeAutomexLibrary.Entidade;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;
using HomeAutomexLibrary.Repositorio.Database;

namespace HomeAutomexLibrary.Repositorio
{
    public abstract class RepositorioBase<TEntidade, TChave> 
        where TEntidade : EntidadeBase<TChave>
    {   
            protected DatabaseContext Context { get; private set; }
            protected DbSet<TEntidade> Entidades { get; private set; }

            protected RepositorioBase(DatabaseContext contexto)
            {
                Context = contexto;
                Entidades = contexto.Set<TEntidade>();
            }

            public TEntidade BuscarPorChave(TChave chave)
            {
                return Entidades.Find(chave);
            }

            public TEntidade BuscarPrimeiro(Expression<Func<TEntidade, bool>> criterios)
            {
                return Entidades.Where(criterios).FirstOrDefault();
            }

            public TEntidade Buscar(Expression<Func<TEntidade, bool>> criterios)
            {
                return Entidades.Where(criterios).SingleOrDefault();
            }

            public void Remover(TEntidade entidade)
            {
                Entidades.Remove(entidade);
            }

            public void RemoverPorChave(TChave chave)
            {
                TEntidade entidade = BuscarPorChave(chave);
                Entidades.Remove(entidade);
            }

            public void Inserir(TEntidade entidade)
            {
                Entidades.Add(entidade);
            }

            public void Alterar(TEntidade entidade)
            {
                Entidades.Attach(entidade);
                Context.Entry(entidade).State = EntityState.Modified;
            }

            public IEnumerable<TEntidade> Consultar(Expression<Func<TEntidade, bool>> criterios)
            {
                return Entidades.Where(criterios);
            }

            public IEnumerable<TEntidade> ConsultarTodos()
            {
                return Entidades;
            }

            public int ContarTodos()
            {
                return Entidades.Count();
            }

            public int Contar(Expression<Func<TEntidade, bool>> criterios)
            {
                return Entidades.Count(criterios);
            }

            public bool Existir(Expression<Func<TEntidade, bool>> criterios)
            {
                return Entidades.Any(criterios);
            }
    
    }
}
