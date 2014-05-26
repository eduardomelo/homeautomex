using HomeAutomexLibrary.Entidade;
using HomeAutomexLibrary.Repositorio.Database;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Repositorio
{
    public class DispositivoCenarioRepositorio : RepositorioBase<DispositivoCenario, int>
    {
        public DispositivoCenarioRepositorio(DatabaseContext contexto)
            : base(contexto)
        { }

        /// <summary>
        /// Exemplo
        /// </summary>

        /// <returns></returns>
        public IEnumerable<Cenario> BuscarPorDescricao(string Descricao)
        {
            return null;
           // return base.Consultar(e => e.Status.Contains(Descricao));
        }

    }
}
