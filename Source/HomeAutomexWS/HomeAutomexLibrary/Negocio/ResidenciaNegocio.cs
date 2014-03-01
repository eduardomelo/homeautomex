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
    public class ResidenciaNegocio: NegocioBase<Residencia,int>
    {
        private ResidenciaRepositorio repositorio;
        private DatabaseContext contexto;
        public ResidenciaNegocio()
            : base(new ResidenciaRepositorio(new DatabaseContext()))
        {
            this.contexto = new DatabaseContext();
        }

        public string Inserir(Residencia residencia)
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



    }
}
