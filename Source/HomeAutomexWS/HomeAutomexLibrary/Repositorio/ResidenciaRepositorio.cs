using HomeAutomexLibrary.Entidade;
using HomeAutomexLibrary.Repositorio.Database;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Repositorio
{
    public class ResidenciaRepositorio : RepositorioBase<Residencia,int>
    {
        public ResidenciaRepositorio(DatabaseContext contexto) : base(contexto)
        { }


        /// <summary>
        /// Exemplo
        /// </summary>
        /// <param name="nomeRua"></param>
        /// <returns></returns>
        public IEnumerable<Residencia> BuscarPorNomeDaRua(string nomeRua)
        {
            return base.Consultar(e => e.Logradouro.Contains(nomeRua));
        }
    }
}
