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
    public class AmbienteNegocio : NegocioBase<Ambiente, int>
    {
        private AmbienteRepositorio ambienteRepositorio;
        private DispositivoRepositorio dispositivoRepositorio;
        private ResidenciaRepositorio residenciaRepositorio;
        private DatabaseContext contexto;

        public AmbienteNegocio(DatabaseContext contexto)
        {
            this.contexto = contexto;
            dispositivoRepositorio = new DispositivoRepositorio(contexto);
            ambienteRepositorio = new AmbienteRepositorio(contexto);
            residenciaRepositorio = new ResidenciaRepositorio(contexto);
           
        }

        public string InserirAmbiente(Ambiente ambiente)
        {
            ambiente.DataAlteracao = null;
            ambiente.DataExclusao = null;
            ambiente.Residencia = residenciaRepositorio.BuscarPorChave(ambiente.Residencia.Chave);
            ambiente.Desativado = true;
            ambienteRepositorio.Inserir(ambiente);

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

        public string AlterarAmbiente(Ambiente ambiente)
        {
            ambiente.Desativado = true;
            base.Alterar(ambiente);
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
        public string RemoverAmbientePorChave(int chave)
        {
            Ambiente ambiente = new Ambiente();
            ambiente = BuscarPorChave(chave);
            ambiente.Desativado = false;
            base.Alterar(ambiente);
           //base.RemoverPorChave(chave);
            
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
        public List<Ambiente> ConsultarTodosAmbiente()
        {
            return base.ConsultarTodos().ToList();
        }
        public void RemoverAmbientePorChaveEstrageira(int chave) {

          this.ambienteRepositorio.RemoverDispositivoPorChave(chave);
        }
        public List<Ambiente> ConsultarTodosAmbientePorUsuarioChave(int chave)
        {
            return this.ambienteRepositorio.Consultar(e =>
                e.Residencia.Usuarios.Any(u => u.Chave == chave && e.Desativado == true))
                .ToList();
        }
    }
}

