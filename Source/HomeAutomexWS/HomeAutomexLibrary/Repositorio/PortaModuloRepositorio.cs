using HomeAutomexLibrary.Entidade;
using HomeAutomexLibrary.Repositorio.Database;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Repositorio
{
    public class PortaModuloRepositorio : RepositorioBase<PortaModulo,int>
    {
        public PortaModuloRepositorio(DatabaseContext contexto)
            : base(contexto)
        { }


        /// <summary>
        /// Exemplo
        /// </summary>
        /// <param name="nomeRua"></param>
        /// <returns></returns>
        public IEnumerable<PortaModulo> BuscarPorIdentificador(string identificador)
        {
            return base.Consultar(e => e.Identificador.Contains(identificador));
        }
    }
}
