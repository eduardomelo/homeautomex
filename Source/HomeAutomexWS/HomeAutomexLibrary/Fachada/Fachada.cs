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
        private ResidenciaNegocio residenciaNegocio;    
        private DatabaseContext contexto;

        public Fachada()
        {
            this.contexto = new DatabaseContext();
            this.negocioUsuario = new UsuarioNegocio();
            this.residenciaNegocio = new ResidenciaNegocio();
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
        public string Inserir(Usuario usuario)
        {
            return this.negocioUsuario.Inserir(usuario);
        }
        public void Alterar(Usuario usuario)
        {
            this.negocioUsuario.Alterar(usuario);
        }
        public void Excluir(Usuario usuario)
        {
            this.negocioUsuario.Excluir(usuario);
        }
        public List<Usuario> ConsultarTodos()
        {
            return this.negocioUsuario.ConsultarTodos();
        }
        public int ContarTodos()
        {
            return this.negocioUsuario.ContarTodos();
        }

        public string ExisteUsuario(Usuario usuario)
        {
            return this.negocioUsuario.ExisteUsuario(usuario);
        }

        public string Inserir(Residencia residencia)
        {
            return this.residenciaNegocio.Inserir(residencia);
        }

    }
}
