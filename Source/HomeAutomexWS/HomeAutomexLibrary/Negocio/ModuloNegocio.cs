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
    public class ModuloNegocio : NegocioBase<Modulo, int>
    {
        private ModuloRepositorio moduloRepositorio;
        private ResidenciaRepositorio residenciaRepositorio;

        private DatabaseContext contexto;
        public ModuloNegocio(DatabaseContext contexto)
            //: base(new ModuloRepositorio(new DatabaseContext()))
        {
            this.contexto = contexto;
            moduloRepositorio = new ModuloRepositorio(contexto);
            residenciaRepositorio = new ResidenciaRepositorio(contexto);
        }
        public string InserirModulo(Modulo modulo)
        {

            modulo.Residencia = residenciaRepositorio.BuscarPorChave(modulo.Residencia.Chave);
            moduloRepositorio.Inserir(modulo);
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
        public string AlterarModulo(Modulo modulo)
        {
            base.Alterar(modulo);
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
        public string RemoverModuloPorChave(int chave)
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
        public List<Modulo> ConsultarTodosModulo()
        {
            return base.ConsultarTodos().ToList();
        }

        public List<Modulo> ConsultarModuloPorUsuario(int chave)
        {
            return base.Consultar(e => e.Residencia != null && e.Residencia.Usuarios.Any(u => u.Chave == chave)).ToList();
        }
    }
}

