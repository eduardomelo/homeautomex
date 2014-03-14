using HomeAutomexLibrary.Entidade;
using HomeAutomexLibrary.Repositorio.Database;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Repositorio
{
    public class ModuloRepositorio : RepositorioBase<Modulo,int>
    {
        public ModuloRepositorio(DatabaseContext contexto)
            : base(contexto)
        { }


        /// <summary>
        /// Exemplo
        /// </summary>
     
        /// <returns></returns>
        public IEnumerable<Modulo> BuscarPorNome(string Nome)
        {
            return base.Consultar(e => e.Nome.Contains(Nome));
        }
    }
}
