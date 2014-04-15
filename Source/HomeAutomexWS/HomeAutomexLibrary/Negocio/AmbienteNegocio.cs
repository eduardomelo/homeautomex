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
        private ResidenciaRepositorio residenciaRepositorio;
        private DatabaseContext contexto;

        public AmbienteNegocio(DatabaseContext contexto)
        {
            this.contexto = contexto;
            ambienteRepositorio = new AmbienteRepositorio(contexto);
            residenciaRepositorio = new ResidenciaRepositorio(contexto);
           
        }

        public string InserirAmbiente(Ambiente ambiente)
        {
            ambiente.DataAlteracao = null;
            ambiente.DataExclusao = null;
            ambiente.Residencia = residenciaRepositorio.BuscarPorChave(ambiente.Residencia.Chave);
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

            //ambiente.DataAlteracao = DateTime.Now; ;
            //ambiente.DataCadastro = null;
            //ambiente.DataExclusao = null;
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
            base.RemoverPorChave(chave);
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

    }
}

