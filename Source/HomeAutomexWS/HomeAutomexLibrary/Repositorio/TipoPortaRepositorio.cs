using HomeAutomexLibrary.Entidade;
using HomeAutomexLibrary.Repositorio.Database;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Repositorio
{
    public class TipoPortaRepositorio : RepositorioBase<TipoPorta,int>
    {
        public TipoPortaRepositorio(DatabaseContext contexto)
            : base(contexto)
        { }


        /// <summary>
        /// Exemplo
        /// </summary>
        /// <param name="nomeRua"></param>
        /// <returns></returns>
        public IEnumerable<TipoPorta> BuscarPorTipo(string tipo)
        {
            return base.Consultar(e => e.Tipo.Contains(tipo));
        }
    }
}
