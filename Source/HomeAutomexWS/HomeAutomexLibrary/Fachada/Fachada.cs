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
        private UsuarioNegocio      usuarioNegocio;
        private ResidenciaNegocio   residenciaNegocio;
        private ModuloNegocio       moduloNegocio;
        private AmbienteNegocio     ambienteNegocio;
        private TipoPortaNeogocio   tipoPortaNegocio;
        private DispositivoNegocio  dispositivoNegocio;
        private UTUtilizacaoNegocio utDispositivoNegocio;
        private LogNegocio          logNegocio;
        private PortaNegocio   portaNegocio;
        private DatabaseContext     contexto;
        

        public Fachada()
        {
            this.contexto = new DatabaseContext();
            this.usuarioNegocio = new UsuarioNegocio();
            this.residenciaNegocio = new ResidenciaNegocio(contexto);
            this.moduloNegocio = new ModuloNegocio(contexto);
            this.ambienteNegocio = new AmbienteNegocio(contexto);
            this.portaNegocio = new PortaNegocio(contexto);
            this.tipoPortaNegocio = new TipoPortaNeogocio();
            this.dispositivoNegocio = new DispositivoNegocio(contexto);
            this.utDispositivoNegocio   = new UTUtilizacaoNegocio();
            this.logNegocio             = new LogNegocio();
            
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


        #region Log
        public string InserirLog(Log log) 
        {
            return this.logNegocio.InserirLog(log);   
        }
        public List<Log> ConsultarTodosLog()
        {
            return this.logNegocio.ConsultarTodosLog();
        }
        #endregion



        #region UTDispositivo
        public string InserirUTDispositivo(UTDispositivo utDispositivo)
        {
            return this.utDispositivoNegocio.InserirUTDispositivo(utDispositivo);
        }
        public string AlterarUTDispositivo(UTDispositivo utDispositivo)
        {
            return this.utDispositivoNegocio.InserirUTDispositivo(utDispositivo);
        }
        public List<UTDispositivo> ConsultarTodosUTDispositivo()
        {
            return this.utDispositivoNegocio.ConsultarTodosUTDispositivo();
        }
        #endregion

        

        #region Usuário
        public Usuario Logar(Usuario usuario)
        {
            return this.usuarioNegocio.Logar(usuario);
        }
        public string InserirUsuario(Usuario usuario)
        {
            return this.usuarioNegocio.Inserir(usuario);
        }
        public string AlterarUsuario(Usuario usuario)
        {
          return this.usuarioNegocio.Alterar(usuario);
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
        #endregion



        #region Residência
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
        public string RemoverResidenciaPorChave(int chave)
        {
            return this.residenciaNegocio.RemoverResidenciaPorChave(chave);
        }
        public List<Residencia> ConsultarResidenciaPorUsuarioChave(int chave)
        {
            return this.residenciaNegocio.BuscarPorUsuarioChave(chave);
        }
        #endregion



        #region Modulo
        // Modulo
        public string InserirModulo(Modulo modulo)
        {
            return this.moduloNegocio.InserirModulo(modulo);
        }
        public List<Modulo> ConsultarTodosModulo()
        {
            return this.moduloNegocio.ConsultarTodosModulo();
        }
        public string AlterarModulo(Modulo modulo)
        {
            return this.moduloNegocio.AlterarModulo(modulo);
        }
        public Modulo BuscarModuloPorChave(int chave)
        {
            return this.moduloNegocio.BuscarPorChave(chave);
        }
        public string RemoverModuloPorChave(int chave)
        {
            return this.moduloNegocio.RemoverModuloPorChave(chave);
        }
        public List<Modulo> ConsultarModuloPorUsuario(int chave)
        {
            return this.moduloNegocio.ConsultarModuloPorUsuario(chave);
        }
        #endregion



        #region Tipo de Porta
        // Tipo Porta
        public string InserirTipoPorta(TipoPorta tipoPorta)
        {
            return this.tipoPortaNegocio.InserirTipoPorta(tipoPorta);
        }
        public List<TipoPorta> ConsultarTodosTipoPorta()
        {
            return this.tipoPortaNegocio.ConsultarTodosTipoPorta();
        }
        public string AlterarTipoPorta(TipoPorta tipoPorta)
        {
            return this.tipoPortaNegocio.AlterarTipoPorta(tipoPorta);
        }
        public TipoPorta BuscarTipoPortaPorChave(int chave)
        {
            return this.tipoPortaNegocio.BuscarPorChave(chave);
        }
        public string RemoverTipoPortaPorChave(int chave)
        {
            return this.tipoPortaNegocio.RemoverTipoPortaPorChave(chave);
        }


        #endregion

        #region Dispositivo
        public string InserirDispositivo(Dispositivo dispositivo)
        {
            return this.dispositivoNegocio.InserirDispositivo(dispositivo);
        }
        public List<Dispositivo> ConsultarTodosDispositivo()
        {
            return this.dispositivoNegocio.ConsultarTodosDispositivo();
        }
        public List<Dispositivo> ConsutarTodosDispositivoFavorito()
        {
            return this.dispositivoNegocio.ConsutarTodosDispositivoFavorito();
        }
        public string AlterarDispositivo(Dispositivo dispositivo)
        {
            return this.dispositivoNegocio.AlterarDispositivo(dispositivo);
        }
        public Dispositivo BuscarDispositivoPorChave(int chave)
        {
            return this.dispositivoNegocio.BuscarPorChave(chave);
        }

        public string RemoverDispositivoPorChave(int chave)
        {
            return this.dispositivoNegocio.RemoverDispositivoPorChave(chave);
        }

        //public string AlterarStatus(Dispositivo dispositivo)
        //{
        //    return this.dispositivoNegocio.AlterarStatus(dispositivo);
        //}
        #endregion




        #region Ambiente
        // Ambiente
        public string InserirAmbiente(Ambiente ambiente)
        {
            return this.ambienteNegocio.InserirAmbiente(ambiente);
        }
        public List<Ambiente> ConsultarTodosAmbiente()
        {
            return this.ambienteNegocio.ConsultarTodosAmbiente();
        }
        public string AlterarAmbiente(Ambiente ambiente)
        {
            return this.ambienteNegocio.AlterarAmbiente(ambiente);
        }
        public Ambiente BuscarAmbientePorChave(int chave)
        {
            return this.ambienteNegocio.BuscarPorChave(chave);
        }
        public string RemoverAmbientePorChave(int chave)
        {
            return this.ambienteNegocio.RemoverAmbientePorChave(chave);
        }
        public void RemoverAmbientePorChaveEstrageira(int chave)
        {
            this.ambienteNegocio.RemoverAmbientePorChaveEstrageira(chave);
        }
        #endregion


        #region Porta
        public string InserirPorta(Porta porta)
        {
            return this.portaNegocio.Inserir(porta);
        }
        public List<Porta> ConsultarTodosPorta()
        {
            return this.portaNegocio.ConsultarTodos().ToList();
        }
        public string AlterarPorta(Porta porta)
        {
            return this.portaNegocio.Alterar(porta);
        }
        public Porta BuscarPortaPorChave(int chave)
        {
            return this.portaNegocio.BuscarPorChave(chave);
        }
        public string RemoverPortaPorChave(int chave)
        {
            return this.portaNegocio.RemoverPorChave(chave);
        }
        #endregion

        #region Arduino        
        public string StatusArduino(string ip)
        {
            return dispositivoNegocio.StatusArduino(ip);
        }
        #endregion




        //public string StatusArduino()
        //{
        //    return this.residenciaNegocio.StatusArduino();
        //}

        //public string MudarStatusDispositivo(List<Dispositivo> dispositivos)
        //{
        //    return this.dispositivoNegocio.MudarStatusDispositivo(dispositivos);
        //}





    }
}
