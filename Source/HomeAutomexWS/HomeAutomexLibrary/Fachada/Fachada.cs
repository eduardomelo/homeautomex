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
        private UsuarioNegocio usuarioNegocio;
        private ResidenciaNegocio residenciaNegocio;    
        private DatabaseContext contexto;

        public Fachada()
        {
            this.contexto = new DatabaseContext();
            this.usuarioNegocio = new UsuarioNegocio();
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
        // Requisições do objeto usuário
        public string InserirUsuario(Usuario usuario)
        {
            return this.usuarioNegocio.InserirUsuario(usuario);
        }
        public string AlterarUsuario(Usuario usuario)
        {
          return this.usuarioNegocio.AlterarUsuario(usuario);
        }
        public string RemoverUsuarioPorChave(int chave)
        {
           return this.usuarioNegocio.RemoverUsuarioPorChave(chave);
        }
        public List<Usuario> ConsultarTodosUsuario()
        {
            return this.usuarioNegocio.ConsultarTodosUsuario();
        }
        public int ContarTodosUsuario()
        {
            return this.usuarioNegocio.ContarTodosUsuario();
        }
        public Usuario BuscarUsuarioPorChave(int chave)
        {
            return this.usuarioNegocio.BuscarPorChave(chave);
        }
        public string ExisteUsuario(Usuario usuario)
        {
            return this.usuarioNegocio.ExisteUsuario(usuario);
        }

        // Requisições da Residencia
        public string InserirResidencia(Residencia residencia)
        {
            return this.residenciaNegocio.InserirResidencia(residencia);
        }
        public List<Residencia> ConsultarTodosResidencia()
        {
            return this.residenciaNegocio.ConsultarTodosResidencia();
        }
        public string AlterarResidencia(Residencia residencia)
        {
            return this.residenciaNegocio.AlterarResidencia(residencia);
        }
        public Residencia BuscarResidenciaPorChave(int chave)
        {
            return this.residenciaNegocio.BuscarPorChave(chave);
        }
    }
}
