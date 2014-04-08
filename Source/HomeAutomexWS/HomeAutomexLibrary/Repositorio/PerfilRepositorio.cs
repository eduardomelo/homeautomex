using HomeAutomexLibrary.Entidade;
using HomeAutomexLibrary.Repositorio.Database;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Repositorio
{
    public class PerfilRepositorio : RepositorioBase<Perfil,int>
    {
        public PerfilRepositorio(DatabaseContext contexto)
            : base(contexto)
        { }


        /// <summary>
        /// Exemplo
        /// </summary>
     
        /// <returns></returns>
        public IEnumerable<Perfil> BuscarPorNome(string nome)
        {
            return base.Consultar(e => e.Nome.Contains(nome));
        }
    }
}
