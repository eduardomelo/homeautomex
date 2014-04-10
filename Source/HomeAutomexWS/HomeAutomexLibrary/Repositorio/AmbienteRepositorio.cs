using HomeAutomexLibrary.Entidade;
using HomeAutomexLibrary.Repositorio.Database;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Repositorio
{
    public class AmbienteRepositorio : RepositorioBase<Ambiente,int>
    {
        public AmbienteRepositorio(DatabaseContext contexto)
            : base(contexto)
        { }

        /// <summary>
        /// Exemplo
        /// </summary>
     
        /// <returns></returns>
        public IEnumerable<Ambiente> BuscarPorDescricao(string Descricao)
        {
            return base.Consultar(e => e.Descricao.Contains(Descricao));
        }
        public void RemoverDispositivoPorChave(int chave)
        {
            Context.Database.ExecuteSqlCommand("DELETE FROM AMBIENTE WHERE CD_DISPOSITIVO = {0}", chave);

        }
    }
}
