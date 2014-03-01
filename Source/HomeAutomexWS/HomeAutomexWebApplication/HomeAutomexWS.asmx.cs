using HomeAutomexLibrary.Entidade;
using HomeAutomexLibrary.Repositorio;
using HomeAutomexLibrary.Repositorio.Database;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using HomeAutomexLibrary.Fachada;
using Newtonsoft.Json;
using System.Web.Script.Services;

namespace HomeAutomexWebApplication
{
    /// <summary>
    /// Summary description for HomeAutomexWS
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    [System.Web.Script.Services.ScriptService]
    public class HomeAutomexWS : System.Web.Services.WebService
    {        
        private UsuarioFachada usuarioFachada;
        private Fachada fachada;
        private Usuario usuario;
        public HomeAutomexWS() {
            this.usuario = new Usuario();
            this.fachada = new Fachada();
        }

        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string InserirUsuário(string jUsuario)
        {

            var usuario = JsonConvert.DeserializeObject<Usuario>(jUsuario);
            var retorno = fachada.Inserir(usuario);            
            return retorno;
        }

        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string InserirResidencia(string jResidencia)
        {
            var residencia = JsonConvert.DeserializeObject<Residencia>(jResidencia);
            var retorno = fachada.Inserir(residencia);
            return retorno;
        }
    }
}
