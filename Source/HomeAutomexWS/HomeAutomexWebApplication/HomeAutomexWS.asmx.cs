using HomeAutomexLibrary.Entidade;
using HomeAutomexLibrary.Repositorio;
using HomeAutomexLibrary.Repositorio.Database;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using HomeAutomexLibrary.Fachada;

namespace HomeAutomexWebApplication
{
    /// <summary>
    /// Summary description for HomeAutomexWS
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class HomeAutomexWS : System.Web.Services.WebService
    {
        private Fachada fachada;
        private Usuario usuario;
        public HomeAutomexWS() {
            this.usuario = new Usuario();
            this.fachada = new Fachada();
        }

        [WebMethod]
        public void Inserir()
        {
          
             usuario = new Usuario
            {
                Nome = "Edson Gouveia",
                Celular = "(81)9999-9999",
                Telefone = "(81)9999-9999",
                Senha = "senha",
                Email = "email@email.com",
                Login = "joao.jorge"
            };
            this.fachada.Inserir(usuario);
        }
    }
}
