using HomeAutomexLibrary.Entidade;
using HomeAutomexLibrary.Repositorio.Database;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Repositorio
{
    public class CenarioRepositorio : RepositorioBase<Cenario, int>
    {
        public CenarioRepositorio(DatabaseContext contexto)
            : base(contexto)
        { }

        /// <summary>
        /// Exemplo
        /// </summary>

        /// <returns></returns>
        public IEnumerable<Cenario> BuscarPorDescricao(string Descricao)
        {
            return base.Consultar(e => e.Descricao.Contains(Descricao));
        }

    }
}
