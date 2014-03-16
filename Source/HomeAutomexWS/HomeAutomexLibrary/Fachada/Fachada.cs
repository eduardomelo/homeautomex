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
        private ModuloNegocio moduloNegocio;
        private AmbienteNegocio ambienteNegocio;
        private TipoPortaNeogocio tipoPortaNegocio;
        private PortaModuloNegocio portaModuloNegocio;
        private DispositivoNegocio dispositivoNegocio;
        private DatabaseContext contexto;

        public Fachada()
        {
            this.contexto = new DatabaseContext();
            this.usuarioNegocio = new UsuarioNegocio();
            this.residenciaNegocio = new ResidenciaNegocio();
            this.moduloNegocio = new ModuloNegocio();
            this.ambienteNegocio = new AmbienteNegocio();
            this.tipoPortaNegocio = new TipoPortaNeogocio();
            this.portaModuloNegocio = new PortaModuloNegocio();
            this.dispositivoNegocio = new DispositivoNegocio();

            
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
        public string RemoverResidenciaPorChave(int chave)
        {
            return this.residenciaNegocio.RemoverResidenciaPorChave(chave);
        }

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

        //  Porta modulo
        public string InserirPortaModulo(PortaModulo tipoPorta)
        {
            return this.portaModuloNegocio.InserirPortaModulo(tipoPorta);
        }
        public List<PortaModulo> ConsultarTodosPortaModulo()
        {
            return this.portaModuloNegocio.ConsultarTodosPortaModulo();
        }
        public string AlterarPortaModulo(PortaModulo tipoPorta)
        {
            return this.portaModuloNegocio.AlterarPortaModulo(tipoPorta);
        }
        public PortaModulo BuscarPortaModuloPorChave(int chave)
        {
            return this.portaModuloNegocio.BuscarPorChave(chave);
        }
        public string RemoverPortaModuloPorChave(int chave)
        {
            return this.portaModuloNegocio.RemoverPortaModuloPorChave(chave);
        }

        // Dispositivo
        public string InserirDispositivo(Dispositivo dispositivo)
        {
            return this.dispositivoNegocio.InserirDispositivo(dispositivo);
        }
        public List<Dispositivo> ConsultarTodosDispositivo()
        {
            return this.dispositivoNegocio.ConsultarTodosDispositivo();
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
    }
}
