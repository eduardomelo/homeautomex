using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using HomeAutomexLibrary.Entidade;
using HomeAutomexLibrary.Negocio;
using HomeAutomexLibrary.Repositorio.Database;

namespace HomeAutomexLibrary.Fachada
{
    public class Fachada
    {
        private UsuarioNegocio negocioUsuario;
        private DatabaseContext contexto;

        public Fachada()
        {
            this.contexto = new DatabaseContext();
            this.negocioUsuario = new UsuarioNegocio(contexto);
        }
        private static Fachada instancia;
        public static Fachada obterInstancia()
        {
            if (instancia == null)
            {
                instancia = new Fachada();
            }
            return instancia;
        }
        public void Inserir(Usuario usuario)
        {
            this.negocioUsuario.Inserir(usuario);
        }
        public void Alterar(Usuario usuario)
        {
            this.negocioUsuario.Alterar(usuario);
        }
        public void Excluir(Usuario usuario)
        {
            this.negocioUsuario.Excluir(usuario);
        }
        public IEnumerable<Usuario> ConsultarTodos()
        {
            return this.negocioUsuario.ConsultarTodos();
        }
        public int ContarTodos()
        {
            return this.negocioUsuario.ContarTodos();
        }

    }
}
