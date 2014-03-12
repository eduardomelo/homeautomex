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
     
        private Fachada fachada;
        private Usuario usuario;
        public HomeAutomexWS() {
            this.usuario = new Usuario();
            this.fachada = new Fachada();
        }
        // Usuario
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string InserirUsuário(string jUsuario)
        {
            var usuario = JsonConvert.DeserializeObject<Usuario>(jUsuario);
            var retorno = fachada.InserirUsuario(usuario);            
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string AlterarUsuario(string jUsuario)
        {
            var usuario = JsonConvert.DeserializeObject<Usuario>(jUsuario);
            usuario.DataAlteracao = DateTime.Now;
            usuario.DataCadastro = DateTime.Now;
            usuario.DataExclusao = DateTime.Now;
            var retorno = fachada.AlterarUsuario(usuario);
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string BuscarUsuarioPorChave(string jChave)
        {
            int chave = JsonConvert.DeserializeObject<int>(jChave);
            var usuario = fachada.BuscarUsuarioPorChave(chave);
            return JsonConvert.SerializeObject(usuario);
        }

        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ExcluirUsuario(string jChave)
        {
            int chave = JsonConvert.DeserializeObject<int>(jChave);
            //var usuarioExcluido = fachada.BuscarUsuarioPorChave(chave);
            var usuario = fachada.RemoverUsuarioPorChave(chave);
            return JsonConvert.SerializeObject("Usuário removido com sucesso");
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ConsutarTodosUsuarios()
        {
            return JsonConvert.SerializeObject(fachada.ConsultarTodosUsuario());
        }

        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ExistirUsuario(string jUsuario)
        {
            var usuario = JsonConvert.DeserializeObject<Usuario>(jUsuario);
            var retorno = fachada.ExisteUsuario(usuario);
            return retorno;
        }



        // Residencia
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string InserirResidencia(string jResidencia)
        {
            var residencia = JsonConvert.DeserializeObject<Residencia>(jResidencia);
            var retorno = fachada.InserirResidencia(residencia);
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ConsutarTodosResidecia()
        {
            return JsonConvert.SerializeObject(fachada.ConsultarTodosResidencia());
        }
    }
}
