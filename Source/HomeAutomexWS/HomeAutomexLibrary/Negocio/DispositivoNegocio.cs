using HomeAutomexLibrary.Entidade;
using HomeAutomexLibrary.Repositorio;
using HomeAutomexLibrary.Repositorio.Database;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Negocio
{
    public class DispositivoNegocio : NegocioBase<Dispositivo, int>
    {

        private DatabaseContext contexto;
        private DispositivoRepositorio dispositivoRepositorio;
        private AmbienteRepositorio ambienteRepositorio;
        private PortaRepositorio portaRepositorio;
        private ModuloRepositorio moduloRepositorio;

        public DispositivoNegocio(DatabaseContext contexto)
        {
            this.contexto = contexto;
            this.dispositivoRepositorio = new DispositivoRepositorio(contexto);
            this.ambienteRepositorio = new AmbienteRepositorio(contexto);
            this.portaRepositorio = new PortaRepositorio(contexto);
            this.moduloRepositorio = new ModuloRepositorio(contexto);
        }

        public string InserirDispositivo(Dispositivo dispositivo)
        {
            dispositivo.Ambiente = ambienteRepositorio.BuscarPorChave(dispositivo.Ambiente.Chave);
            dispositivo.Porta = portaRepositorio.BuscarPorChave(dispositivo.Porta.Chave);
            dispositivo.Desativado = true;
            dispositivoRepositorio.Inserir(dispositivo);

            try
            {
                contexto.SaveChanges();
                return "Operação realizada com sucesso!";
            }
            catch (Exception ex)
            {
                throw new Exception(ex.InnerException.Message != null ? ex.InnerException.Message : ex.Message);
            }
        }

        public string AlterarDispositivo(Dispositivo dispositivo)
        {
            var disp = dispositivoRepositorio.BuscarPorChave(dispositivo.Chave);
            disp.Ambiente = ambienteRepositorio.BuscarPorChave(dispositivo.Ambiente.Chave);
            disp.Porta = portaRepositorio.BuscarPorChave(dispositivo.Porta.Chave);
            disp.Descricao = dispositivo.Descricao;
            disp.Favorito = dispositivo.Favorito;
            disp.Status = dispositivo.Status;
            disp.Desativado = dispositivo.Desativado;
        //    disp.Desativado = true;
            dispositivoRepositorio.Alterar(disp);
            try
            {
                contexto.SaveChanges();
                return "Operação realizada com sucesso!";
            }
            catch (Exception ex)
            {

                throw new Exception(ex.InnerException.Message != null ? ex.InnerException.Message : ex.Message);
            }
        }


     
        public string RemoverDispositivoPorChave(int chave)
        {
            Dispositivo dispositivo = new Dispositivo();
            dispositivo = BuscarPorChave(chave);
            dispositivo.Desativado = false;
            base.Alterar(dispositivo);
          //  base.RemoverPorChave(chave);
            try
            {
                base.SaveChanges();
                return "Operação realizada com sucesso!";
            }
            catch (Exception ex)
            {

                throw new Exception(ex.InnerException.Message != null ? ex.InnerException.Message : ex.Message);
            }
        }
        public List<Dispositivo> ConsultarTodosDispositivo()
        {
            return base.ConsultarTodos().ToList();
        }
        public List<Dispositivo> ConsutarTodosDispositivoFavorito (int chave)
        {
            return this.dispositivoRepositorio.Consultar(e => e.Ambiente.Residencia.Usuarios.Any(u => u.Chave == chave) && e.Favorito).ToList();
        }
        public List<Dispositivo> ConsutarTodosDispositivoFavorito()
        {
            return base.Consultar(e => e.Favorito == true).ToList();
        }
        public string StatusArduino(string ip)
        {
            if (!string.IsNullOrEmpty(ip))
            {
                var modulo = moduloRepositorio.Buscar(e => e.IP.Trim() == ip.Trim());
                if (modulo != null)
                {
                    var dispositivos = dispositivoRepositorio.Consultar(e => e.Porta.Modulo.Chave == modulo.Chave).ToList();
                    var retorno = string.Empty;
                    retorno = string.Join("|", dispositivos.Select(e => e.Porta.Tipo.Identificador.Trim() + e.Porta.NumeroPorta + ":" + (e.Status ? "1" : "0")).ToList());
                    return retorno;
                }
                else
                {
                    return "O IP não está cadastrado" + ip.Trim();
                }
            }
            else
                return "IP Inválido";
        }

        public List<Dispositivo> ConsultarTodosDispositivoPorUsuarioChave(int chave)
        {
            return this.dispositivoRepositorio.Consultar(e =>
                e.Ambiente.Residencia.Usuarios.Any(u => u.Chave == chave))
                .ToList();
        }
    }
}

