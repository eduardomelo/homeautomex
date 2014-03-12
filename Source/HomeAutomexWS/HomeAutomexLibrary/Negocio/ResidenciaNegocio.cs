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
    public class ResidenciaNegocio : NegocioBase<Residencia, int>
    {
        private ResidenciaRepositorio repositorio;
        private DatabaseContext contexto;
        public ResidenciaNegocio()
            : base(new ResidenciaRepositorio(new DatabaseContext()))
        {
            this.contexto = new DatabaseContext();
        }

        public string InserirResidencia(Residencia residencia)
        {
            residencia.DataAlteracao = null;
            residencia.DataCadastro = DateTime.Now;
            residencia.DataExclusao = null;
            base.Inserir(residencia);

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

        public string AlterarResidencia(Residencia residencia)
        {

            residencia.DataAlteracao = DateTime.Now; ;
            residencia.DataCadastro = null;
            residencia.DataExclusao = null;
            base.Alterar(residencia);

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

        public string RemoverResidenciaPorChave(int chave)
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
        public List<Residencia> ConsultarTodosResidencia()
        {
            return base.ConsultarTodos().ToList();
        }
        

    }
}

