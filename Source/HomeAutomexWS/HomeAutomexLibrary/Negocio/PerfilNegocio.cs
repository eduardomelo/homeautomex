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
    public class PerfilNegocio : NegocioBase<Perfil, int>
    {

        private DatabaseContext contexto;
        public PerfilNegocio()
            : base(new PerfilRepositorio(new DatabaseContext()))
        {
            this.contexto = new DatabaseContext();
        }

        public string InserirPerfil(Perfil perfil){

            perfil.DataAlteracao = null;
            perfil.DataCadastro = DateTime.Now;
            perfil.DataExclusao = null;
            base.Inserir(perfil);

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

        public string AlterarPerfil(Perfil perfil)
        {

            perfil.DataAlteracao = DateTime.Now; ;
            perfil.DataCadastro = null;
            perfil.DataExclusao = null;
            base.Alterar(perfil);

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
        public string RemoverPerfilPorChave(int chave)
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
        public List<Perfil> ConsultarTodosPerfil()
        {
            return base.ConsultarTodos().ToList();
        }


    }
}

