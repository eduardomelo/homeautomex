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
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string BuscarResidenciaPorChave(string jChave)
        {
            int chave = JsonConvert.DeserializeObject<int>(jChave);
            var residencia = fachada.BuscarResidenciaPorChave(chave);
            return JsonConvert.SerializeObject(residencia);
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string AlterarResidencia(string jResidencia)
        {
            var residencia = JsonConvert.DeserializeObject<Residencia>(jResidencia);
            residencia.DataAlteracao = DateTime.Now;
            residencia.DataCadastro = DateTime.Now;
            residencia.DataExclusao = DateTime.Now;
            var retorno = fachada.AlterarResidencia(residencia);
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ExcluirResidencia(string jChave)
        {
            int chave = JsonConvert.DeserializeObject<int>(jChave);
            //var usuarioExcluido = fachada.BuscarUsuarioPorChave(chave);
            var residencia = fachada.RemoverResidenciaPorChave(chave);
            return JsonConvert.SerializeObject("Usuário removido com sucesso");
        }


        // Modulo
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string InserirModulo(string jModdulo)
        {
            var modulo = JsonConvert.DeserializeObject<Modulo>(jModdulo);
            var retorno = fachada.InserirModulo(modulo);
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ConsutarTodosModulo()
        { 
                    return JsonConvert.SerializeObject(fachada.ConsultarTodosModulo());
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string BuscarModuloPorChave(string jChave)
        {
            int chave = JsonConvert.DeserializeObject<int>(jChave);
            var modulo = fachada.BuscarModuloPorChave(chave);
            return JsonConvert.SerializeObject(modulo);
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string AlterarModulo(string jModulo)
        {
            var modulo = JsonConvert.DeserializeObject<Modulo>(jModulo);
            var retorno = fachada.AlterarModulo(modulo);
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ExcluirModulo(string jChave)
        {
            int chave = JsonConvert.DeserializeObject<int>(jChave);
            //var usuarioExcluido = fachada.BuscarUsuarioPorChave(chave);
            var modulo = fachada.RemoverModuloPorChave(chave);
            return JsonConvert.SerializeObject("Usuário removido com sucesso");
        }


        // Porta modulo
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string InserirPortaModulo(string jPortaModulo)
        {
            var portaModulo = JsonConvert.DeserializeObject<PortaModulo>(jPortaModulo);
            var retorno = fachada.InserirPortaModulo(portaModulo);
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ConsutarTodosPortaModulo()
        {
            return JsonConvert.SerializeObject(fachada.ConsultarTodosPortaModulo());
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string BuscarPortaModuloPorChave(string jChave)
        {
            int chave = JsonConvert.DeserializeObject<int>(jChave);
            var portaModulo = fachada.BuscarPortaModuloPorChave(chave);
            return JsonConvert.SerializeObject(portaModulo);
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string AlterarPortaModulo(string jPortaModulo)
        {
            var portaModulo = JsonConvert.DeserializeObject<PortaModulo>(jPortaModulo);
            var retorno = fachada.AlterarPortaModulo(portaModulo);
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ExcluirPortaModulo(string jChave)
        {
            int chave = JsonConvert.DeserializeObject<int>(jChave);
            var portaModulo = fachada.RemoverTipoPortaPorChave(chave);
            return JsonConvert.SerializeObject("Usuário removido com sucesso");
        }


        // Tipo de Porta
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string InserirTipoPorta(string jTipoPorta)
        {
            var tipoPorta = JsonConvert.DeserializeObject<TipoPorta>(jTipoPorta);
            var retorno = fachada.InserirTipoPorta(tipoPorta);
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ConsutarTodosTipoPorta()
        {
            return JsonConvert.SerializeObject(fachada.ConsultarTodosTipoPorta());
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string BuscarTipoPortaPorChave(string jChave)
        {
            int chave = JsonConvert.DeserializeObject<int>(jChave);
            var tipoPorta = fachada.BuscarTipoPortaPorChave(chave);
            return JsonConvert.SerializeObject(tipoPorta);
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string AlterarTipoPorta(string jTipoPorta)
        {
            var tipoPorta = JsonConvert.DeserializeObject<TipoPorta>(jTipoPorta);
            var retorno = fachada.AlterarTipoPorta(tipoPorta);
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ExcluirTipoPorta(string jChave)
        {
            int chave = JsonConvert.DeserializeObject<int>(jChave);
            var tipoPorta = fachada.RemoverTipoPortaPorChave(chave);
            return JsonConvert.SerializeObject("Usuário removido com sucesso");
        }
        // Dispositivo
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string InserirDispositivo(string jDispositivo)
        {
            var dispositivo = JsonConvert.DeserializeObject<Dispositivo>(jDispositivo);
            var retorno = fachada.InserirDispositivo(dispositivo);
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ConsutarTodosDispositivo()
        {
            return JsonConvert.SerializeObject(fachada.ConsultarTodosDispositivo());
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string BuscarDispositivoPorChave(string jChave)
        {
            int chave = JsonConvert.DeserializeObject<int>(jChave);
            var dispositivo = fachada.BuscarDispositivoPorChave(chave);
            return JsonConvert.SerializeObject(dispositivo);
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string AlterarDispositivo(string jDispositivo)
        {
            var dispositivo = JsonConvert.DeserializeObject<Dispositivo>(jDispositivo);
            var retorno = fachada.AlterarDispositivo(dispositivo);
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ExcluirDispositivo(string jChave)
        {
            int chave = JsonConvert.DeserializeObject<int>(jChave);
            var dispositivo = fachada.RemoverDispositivoPorChave(chave);
            return JsonConvert.SerializeObject("Dispositivo removido com sucesso");
        }

        // Ambiente
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string InserirAmbiente(string jAmbiente)
        {
            var ambiente = JsonConvert.DeserializeObject<Ambiente>(jAmbiente);
            var retorno = fachada.InserirAmbiente(ambiente);
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ConsutarTodosAmbiente()
        {
            return JsonConvert.SerializeObject(fachada.ConsultarTodosAmbiente());
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string BuscarAmbientePorChave(string jChave)
        {
            int chave = JsonConvert.DeserializeObject<int>(jChave);
            var ambiente = fachada.BuscarAmbientePorChave(chave);
            return JsonConvert.SerializeObject(ambiente);
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string AlterarAmbiente(string jAmbiente)
        {
            var ambiente = JsonConvert.DeserializeObject<Ambiente>(jAmbiente);
            var retorno = fachada.AlterarAmbiente(ambiente);
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ExcluirAmbiente(string jChave)
        {
            int chave = JsonConvert.DeserializeObject<int>(jChave);
            var ambiente = fachada.RemoverAmbientePorChave(chave);
            return JsonConvert.SerializeObject("Ambiente removido com sucesso");
        }
    }
}
