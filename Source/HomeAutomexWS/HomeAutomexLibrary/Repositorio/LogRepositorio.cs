using HomeAutomexLibrary.Entidade;
using HomeAutomexLibrary.Repositorio.Database;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Repositorio
{
    public class LogRepositorio : RepositorioBase<Log,int>
    {
        public LogRepositorio(DatabaseContext contexto)
            : base(contexto)
        { }


        /// <summary>
        /// Exemplo
        /// </summary>
     
        /// <returns></returns>
        public IEnumerable<Log> BuscarPorDescricao(string Descricao)
        {
            return base.Consultar(e => e.Descricao.Contains(Descricao));
        }
    }
}
