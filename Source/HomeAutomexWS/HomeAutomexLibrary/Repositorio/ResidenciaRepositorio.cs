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
        /// Busca de residências por usuários
        /// </summary>
        /// <param name="chave">Chave do usuário</param>
        /// <returns>Lista de todas as residências do usuário</returns>
        public IEnumerable<Residencia> BuscarPorUsuarioChave(int chave)
        {
            return base.Consultar(e => e.Usuarios.Any(u => u.Chave == chave));
        }
    }
}
