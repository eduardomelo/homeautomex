using HomeAutomexLibrary.Entidade;
using HomeAutomexLibrary.Repositorio.Database;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Repositorio
{
    public class UsuarioRepositorio : RepositorioBase<Usuario, int>
    {
        public UsuarioRepositorio(DatabaseContext contexto) : base(contexto)
        { }

    }

}
