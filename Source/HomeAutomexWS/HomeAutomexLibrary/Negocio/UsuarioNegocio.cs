using HomeAutomexLibrary.Entidade;
using HomeAutomexLibrary.Repositorio;
using HomeAutomexLibrary.Repositorio.Database;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Negocio
{
    public class UsuarioNegocio : NegocioBase<Usuario, int>
    {
        private UsuarioRepositorio repositorio;
        private DatabaseContext contexto;
        public UsuarioNegocio(DatabaseContext contexto)
        {
            this.repositorio = new UsuarioRepositorio(contexto);
            this.contexto = contexto;
        }

        public void Inserir(Usuario usuario)
        {
            this.repositorio.Inserir(usuario);
        }

    }
}
