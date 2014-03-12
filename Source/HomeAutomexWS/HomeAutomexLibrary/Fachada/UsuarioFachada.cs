using HomeAutomexLibrary.Entidade;
using HomeAutomexLibrary.Negocio;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Fachada
{
    public class UsuarioFachada
    {
        private UsuarioNegocio usuarioNegocio;
        public UsuarioFachada()
        {
            this.usuarioNegocio = new UsuarioNegocio();
        }

        public string Inserir(Usuario usuario)
        {
           return usuarioNegocio.InserirUsuario(usuario);
        }

        public void Alterar(Usuario usuario)
        {
            usuarioNegocio.Alterar(usuario);
        }

        public void Remover(Usuario usuario)
        {
            usuarioNegocio.Remover(usuario);
        }

        public void RemoverPorChave(int chave)
        {
            usuarioNegocio.RemoverPorChave(chave);
        }
    }
}
