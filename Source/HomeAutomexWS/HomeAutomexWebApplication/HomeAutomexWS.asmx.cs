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
        private UTDispositivo utDispositivo;
        private Log log;
        public HomeAutomexWS()
        {

            this.log = new Log();
            this.utDispositivo = new UTDispositivo();
            this.fachada = new Fachada();
            JsonConvert.DefaultSettings = () => new JsonSerializerSettings

            {
                NullValueHandling = NullValueHandling.Ignore,
                DefaultValueHandling = DefaultValueHandling.IgnoreAndPopulate,
                ReferenceLoopHandling = ReferenceLoopHandling.Ignore
            };
        }

        #region logs
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ConsutarTodosLogs()
        {

            return JsonConvert.SerializeObject(fachada.ConsultarTodosLog());
        }
        [WebMethod]
        public string ConsultarLogPorIntervaloData(DateTime JDataInicial, DateTime JDataFinal)
        {
            return JsonConvert.SerializeObject(fachada.ConsultarLogPorIntervaloData(JDataInicial, JDataFinal));
        }
        #endregion

        #region Usuario
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string InserirUsuário(string jUsuario)
        {
            var usuario = JsonConvert.DeserializeObject<Usuario>(jUsuario);
            log.Descricao = "Usuario inseriu o usuário:" + usuario.Nome;
            var retornoLog = fachada.InserirLog(log);
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
            log.Descricao = "Usuario alterou o usuário:" + usuario.Nome;
            var retornoLog = fachada.InserirLog(log);
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
            var usuario = fachada.RemoverUsuarioPorChave(chave);
            log.Descricao = "Usuario excluiu um usuário:!" + chave;
            var retornoLog = fachada.InserirLog(log);
            return JsonConvert.SerializeObject("Usuário removido com sucesso");
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ConsutarTodosUsuarios()
        {
            log.Descricao = "Usuario consultou todos os usuários!";
            var retornoLog = fachada.InserirLog(log);
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

        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string Logar(string jUsuario)
        {
            var usuario = JsonConvert.DeserializeObject<Usuario>(jUsuario);
            var retorno = fachada.Logar(usuario);
            log.Descricao = "Usuario logou no sistema: " + retorno.Nome;
            var retornoLog = fachada.InserirLog(log);
            //     var veificarAgendamentos = fachada.VerificarTodosAgendamento();
            return JsonConvert.SerializeObject(retorno);
        }

        #endregion

        #region Residencia
        // Residencia
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string InserirResidencia(string jResidencia)
        {
            var residencia = JsonConvert.DeserializeObject<Residencia>(jResidencia);
            log.Descricao = "Usuario inseriu uma nova Residência na rua " + residencia.Logradouro;
            var retornoLog = fachada.InserirLog(log);
            var retorno = fachada.InserirResidencia(residencia);
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ConsutarTodosResidecia()
        {
            log.Descricao = "Usuario consultou todas as residências!";
            var retornoLog = fachada.InserirLog(log);
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
            log.Descricao = "Usuario alterou uma Residência da rua " + residencia.Logradouro;
            var retornoLog = fachada.InserirLog(log);
            var retorno = fachada.AlterarResidencia(residencia);
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ExcluirResidencia(string jChave)
        {
            int chave = JsonConvert.DeserializeObject<int>(jChave);
            log.Descricao = "Usuario excluiu uma Residência na rua " + chave;
            var retornoLog = fachada.InserirLog(log);
            var residencia = fachada.RemoverResidenciaPorChave(chave);
            return JsonConvert.SerializeObject("Usuário removido com sucesso");
        }

        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ConsultarResidenciaPorUsuarioChave(string jUsuario)
        {
            var usuario = JsonConvert.DeserializeObject<Usuario>(jUsuario);
            var residencias = fachada.ConsultarResidenciaPorUsuarioChave(usuario.Chave);
            return JsonConvert.SerializeObject(residencias);
        }
        #endregion

        #region Modulo
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string InserirModulo(string jModdulo)
        {
            var modulo = JsonConvert.DeserializeObject<Modulo>(jModdulo);
            log.Descricao = "Usuario inseriu um modulo " + modulo.Nome;
            var retornoLog = fachada.InserirLog(log);
            var retorno = fachada.InserirModulo(modulo);
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ConsutarTodosModulo()
        {
            log.Descricao = "Usuario consultou todos os modulos!";
            var retornoLog = fachada.InserirLog(log);
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
            log.Descricao = "Usuario alterou um modulo " + modulo.Nome;
            var retornoLog = fachada.InserirLog(log);
            var retorno = fachada.AlterarModulo(modulo);
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ExcluirModulo(string jChave)
        {
            int chave = JsonConvert.DeserializeObject<int>(jChave);
            log.Descricao = "Usuario excluiu um modulo " + chave;
            var retornoLog = fachada.InserirLog(log);
            var modulo = fachada.RemoverModuloPorChave(chave);
            return JsonConvert.SerializeObject("Usuário removido com sucesso");
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ConsultarModuloPorUsuario(string jChave)
        {
            int chave = JsonConvert.DeserializeObject<int>(jChave);
            var modulo = fachada.ConsultarModuloPorUsuario(chave);
            return JsonConvert.SerializeObject(modulo);
        }
        #endregion

        #region Tipo porta

        // Tipo de Porta
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string InserirTipoPorta(string jTipoPorta)
        {
            var tipoPorta = JsonConvert.DeserializeObject<TipoPorta>(jTipoPorta);
            log.Descricao = "Usuario inseriu um tipo de porta " + tipoPorta.Identificador;
            var retornoLog = fachada.InserirLog(log);
            var retorno = fachada.InserirTipoPorta(tipoPorta);
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ConsutarTodosTipoPorta()
        {
            log.Descricao = "Usuario consultou todos os tipos de porta";
            var retornoLog = fachada.InserirLog(log);
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
            log.Descricao = "Usuario alterou um tipo de porta " + tipoPorta.Identificador;
            var retornoLog = fachada.InserirLog(log);
            var retorno = fachada.AlterarTipoPorta(tipoPorta);
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ExcluirTipoPorta(string jChave)
        {
            int chave = JsonConvert.DeserializeObject<int>(jChave);
            log.Descricao = "Usuario excluiu um tipo de porta " + chave;
            var retornoLog = fachada.InserirLog(log);
            var tipoPorta = fachada.RemoverTipoPortaPorChave(chave);
            return JsonConvert.SerializeObject("Tipo de Porta removido com sucesso");
        }
        #endregion

        #region Dispositivo
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string InserirDispositivo(string jDispositivo)
        {
            var dispositivo = JsonConvert.DeserializeObject<Dispositivo>(jDispositivo);
            log.Descricao = "Usuario cadastrou um dispositivo " + dispositivo.Descricao;
            var retornoLog = fachada.InserirLog(log);
            utDispositivo.Cd_dispositivo = dispositivo.Chave;
            utDispositivo.status = dispositivo.Status;
            fachada.InserirUTDispositivo(utDispositivo);
            var retorno = fachada.InserirDispositivo(dispositivo);
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ConsutarTodosDispositivoPorUsuarioChave(string jChave)
        {
            int chave = JsonConvert.DeserializeObject<int>(jChave);
            return JsonConvert.SerializeObject(fachada.ConsultarTodosDispositivoPorUsuarioChave(chave));
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ConsutarTodosDispositivoFavorito()
        {
            log.Descricao = "Usuario consultou um todos os dispositivos";
            var retornoLog = fachada.InserirLog(log);
            return JsonConvert.SerializeObject(fachada.ConsutarTodosDispositivoFavorito());
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ConsutarTodosDispositivoFavoritoPorChaveUsuario(int chave)
        {
            log.Descricao = "Usuario consultou um todos os dispositivos";
            var retornoLog = fachada.InserirLog(log);
            return JsonConvert.SerializeObject(fachada.ConsutarTodosDispositivoFavorito(chave));
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

            // Registrar log.

            log.Descricao = "Usuario alterou um dispositivo " + dispositivo.Descricao;
            var retornoLog = fachada.InserirLog(log);

            // Registrar log de uso.
            utDispositivo.Cd_dispositivo = dispositivo.Chave;
            utDispositivo.status = dispositivo.Status;
            fachada.InserirUTDispositivo(utDispositivo);

            // Alterar registro.
            var retorno = fachada.AlterarDispositivo(dispositivo);
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ExcluirDispositivo(string jChave)
        {
            int chave = JsonConvert.DeserializeObject<int>(jChave);
            log.Descricao = "Usuario excluiu um dispositivo " + jChave;
            var retornoLog = fachada.InserirLog(log);

            var dispositivo = fachada.BuscarDispositivoPorChave(chave);
            this.fachada.RemoverAmbientePorChaveEstrageira(dispositivo.Chave);

            fachada.RemoverDispositivoPorChave(chave);
            return JsonConvert.SerializeObject("Dispositivo removido com sucesso");
        }

        #endregion

        #region Ambiente
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string InserirAmbiente(string jAmbiente)
        {
            var ambiente = JsonConvert.DeserializeObject<Ambiente>(jAmbiente);
            log.Descricao = "Usuario inseriu um ambiente " + ambiente.Descricao;
            var retornoLog = fachada.InserirLog(log);
            var retorno = fachada.InserirAmbiente(ambiente);
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ConsultarTodosAmbientePorUsuarioChave(string jChave)
        {
            int chave = JsonConvert.DeserializeObject<int>(jChave);
            return JsonConvert.SerializeObject(fachada.ConsultarTodosAmbientePorUsuarioChave(chave));
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
            log.Descricao = "Usuario alterou o ambiente, " + ambiente.Descricao;
            var retornoLog = fachada.InserirLog(log);
            var retorno = fachada.AlterarAmbiente(ambiente);
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ExcluirAmbiente(string jChave)
        {
            int chave = JsonConvert.DeserializeObject<int>(jChave);
            log.Descricao = "Usuario excluiu um ambiente, " + jChave;
            var retornoLog = fachada.InserirLog(log);
            var ambiente = fachada.RemoverAmbientePorChave(chave);
            return JsonConvert.SerializeObject("Ambiente removido com sucesso");
        }
        #endregion

        #region Cenario


        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string InserirCenario(string jCenario)
        {
            var cenario = JsonConvert.DeserializeObject<Cenario>(jCenario);
            log.Descricao = "Usuario inseriu um cenário " + cenario.Descricao;
            var retornoLog = fachada.InserirLog(log);
            var retorno = fachada.InserirCenario(cenario);
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ConsutarTodosCenarioPorUsuarioChave(string jChave)
        {
            int chave = JsonConvert.DeserializeObject<int>(jChave);
            return JsonConvert.SerializeObject(fachada.ConsultarTodosCenarioPorUsuarioChave(chave));
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ConsutarTodosCenario()
        {
            log.Descricao = "Usuario consultou todos os tipos de porta";
            var retornoLog = fachada.InserirLog(log);
            return JsonConvert.SerializeObject(fachada.ConsultarTodosCenario());
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string BuscarCenarioPorChave(string jChave)
        {
            int chave = JsonConvert.DeserializeObject<int>(jChave);
            var cenario = fachada.BuscarCenarioPorChave(chave);
            return JsonConvert.SerializeObject(cenario);
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string AlterarCenario(string jCenario)
        {
            var cenario = JsonConvert.DeserializeObject<Cenario>(jCenario);
            log.Descricao = "Usuario alterou o cenario, " + cenario.Descricao;
            var retornoLog = fachada.InserirLog(log);
            var retorno = fachada.AlterarCenario(cenario);
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string CriarCenarioDispositivo(string jCenario, bool status)
        {
            var cenario = JsonConvert.DeserializeObject<Cenario>(jCenario);
            log.Descricao = "Usuario crioi um cenario, " + cenario.Descricao;
            var retornoLog = fachada.InserirLog(log);
            var retorno = fachada.CriarCenarioDispositivo(cenario, status);
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string AtivarCenarioDispositivo(string jCenario)
        {
            var cenario = JsonConvert.DeserializeObject<Cenario>(jCenario);
            log.Descricao = "Usuario alterou o cenario, " + cenario.Descricao;
            var retornoLog = fachada.InserirLog(log);
            var retorno = fachada.AtivarCenarioDispositivo(cenario);
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ExcluirCenario(string jChave)
        {
            int chave = JsonConvert.DeserializeObject<int>(jChave);
            log.Descricao = "Usuario excluiu um cenario, " + jChave;
            var retornoLog = fachada.InserirLog(log);
            var cenario = fachada.RemoverCenarioPorChave(chave);
            return JsonConvert.SerializeObject("Cenario removido com sucesso");
        }
        #endregion

        #region Porta
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string InserirPorta(string jPorta)
        {
            var porta = JsonConvert.DeserializeObject<Porta>(jPorta);
            log.Descricao = "Usuario inseriu uma nova Porta na rua " + porta.Identificador;
            var retornoLog = fachada.InserirLog(log);
            var retorno = fachada.InserirPorta(porta);
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ConsutarTodosPorta()
        {
            return JsonConvert.SerializeObject(fachada.ConsultarTodosPorta());
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string BuscarPortaPorChave(string jChave)
        {
            int chave = JsonConvert.DeserializeObject<int>(jChave);
            var residencia = fachada.BuscarPortaPorChave(chave);
            return JsonConvert.SerializeObject(residencia);
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string AlterarPorta(string jResidencia)
        {
            var porta = JsonConvert.DeserializeObject<Porta>(jResidencia);
            log.Descricao = "Usuario alterou a Porta: " + porta.Identificador;
            var retornoLog = fachada.InserirLog(log);
            var retorno = fachada.AlterarPorta(porta);
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ExcluirPorta(string jChave)
        {
            int chave = JsonConvert.DeserializeObject<int>(jChave);
            log.Descricao = "Usuario excluiu uma Porta: " + chave;
            var retornoLog = fachada.InserirLog(log);
            var residencia = fachada.RemoverPortaPorChave(chave);
            return JsonConvert.SerializeObject("Porta removida com sucesso");
        }
        #endregion

        #region Agendamento
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string InserirAgendamento(string jAgendamento)
        {
            var agendamento = JsonConvert.DeserializeObject<Agendamento>(jAgendamento);
            log.Descricao = "Usuario cadastrou um novo agendamento " + agendamento.Descricao;
            var retornoLog = fachada.InserirLog(log);
            var retorno = fachada.InserirAgendamento(agendamento);
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ConsutarTodosAgendamento()
        {
            log.Descricao = "Usuario consultou um todos os agendamentos";
            var retornoLog = fachada.InserirLog(log);
            return JsonConvert.SerializeObject(fachada.ConsutarTodosAgendamento());
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ConsutarTodosAgendamentoPorUsuarioChave(string jChave)
        {
            int chave = JsonConvert.DeserializeObject<int>(jChave);
            return JsonConvert.SerializeObject(fachada.ConsultarTodosAgendamentoPorUsuarioChave(chave));
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string BuscarAgendamentoPorChave(string jChave)
        {
            int chave = JsonConvert.DeserializeObject<int>(jChave);
            var agendamento = fachada.BuscarAgendamentoPorChave(chave);
            return JsonConvert.SerializeObject(agendamento);
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string AlterarAgendamento(string jAgendamento)
        {
            var agendamento = JsonConvert.DeserializeObject<Agendamento>(jAgendamento);

            // Registrar log.
            log.Descricao = "Usuario alterou um agendamento " + agendamento.Descricao;
            var retornoLog = fachada.InserirLog(log);

            fachada.InserirUTDispositivo(utDispositivo);

            // Alterar registro.
            var retorno = fachada.AlterarAgendamento(agendamento);
            return retorno;
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string ExcluirAgendamento(string jChave)
        {
            int chave = JsonConvert.DeserializeObject<int>(jChave);
            log.Descricao = "Usuario excluiu um agendamento " + jChave;
            var retornoLog = fachada.InserirLog(log);
            fachada.RemoverAgendamentoPorChave(chave);
            return JsonConvert.SerializeObject("Agendamento removido com sucesso");
        }

        #endregion

        #region Arduino
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string StatusArduino(string ip)
        {
            return fachada.StatusArduino(ip);
        }

        #endregion




        //[WebMethod]
        //[ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        //public string StatusArduino()
        //{
        //    //var port = portas.Split('|');
        //    //var dispositivos = new List<DispositivoTeste>();

        //    //foreach (var item in port)
        //    //{
        //    //    dispositivos.Add(new DispositivoTeste
        //    //    { 
        //    //        Identificador = item.Split(':')[0],
        //    //        Status = item.Split(':')[1] == "1" ? true : false
        //    //    });
        //    //}

        //    return fachada.StatusArduino();
        //}

        //[WebMethod]
        //[ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        //public string MudarStatusArduino(string jDispositivos)
        //{
        //    var dispositivos = JsonConvert.DeserializeObject<List<DispositivoTeste>>(jDispositivos);
        //    return this.fachada.MudarStatusArduino(dispositivos);
        //}



        //[WebMethod]
        //[ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        //public string AlterarStatusDispositivo(string jDispositivo)
        //{
        //    DispositivoRepositorio repositorioDispositivo = new DispositivoRepositorio(context);
        //    var dispositivo = JsonConvert.DeserializeObject<Dispositivo>(jDispositivo);
        //    repositorioDispositivo.AlterarStatus(dispositivo);
        //    return "Status Alterado com sucesso!";

        //}
    }
}
