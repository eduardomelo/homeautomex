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

        //private DatabaseContext contexto = new DatabaseContext();
        private UsuarioRepositorio usuarioRepositorio;
        private ResidenciaRepositorio residenciaRepositorio;
        private DatabaseContext contexto;

        public ResidenciaNegocio(DatabaseContext contexto)
        {
            this.contexto = contexto;
            residenciaRepositorio = new ResidenciaRepositorio(contexto);
            usuarioRepositorio = new UsuarioRepositorio(contexto);
        }

        public string InserirResidencia(Residencia residencia)
        {
            var ids = residencia.Usuarios.Select(e => e.Chave).ToList();
            var usuarios = new List<Usuario>();
            residencia.Usuarios.Clear();
            foreach (var chave in ids)
            {
                usuarios.Add(usuarioRepositorio.BuscarPorChave(chave));
            }

            residencia.Usuarios = usuarios;
            residenciaRepositorio.Inserir(residencia);

            try
            {
                contexto.SaveChanges();
                return "Operação realizada com sucesso!";
            }
            catch (System.Data.Entity.Validation.DbEntityValidationException e)
            {
                foreach (var eve in e.EntityValidationErrors)
                {
                    Console.WriteLine("Entity of type \"{0}\" in state \"{1}\" has the following validation errors:",
                        eve.Entry.Entity.GetType().Name, eve.Entry.State);
                    foreach (var ve in eve.ValidationErrors)
                    {
                        Console.WriteLine("- Property: \"{0}\", Error: \"{1}\"",
                            ve.PropertyName, ve.ErrorMessage);
                    }
                }
                throw;
            }
            catch (Exception ex)
            {
                throw new Exception(ex.InnerException.Message != null ? ex.InnerException.Message : ex.Message);
            }
        }

        public string AlterarResidencia(Residencia residencia)
        {
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

        public List<Residencia> BuscarPorUsuarioChave(int chave)
        {
            return residenciaRepositorio.BuscarPorUsuarioChave(chave).ToList();
        }



        //public string StatusArduino()
        //{
        //    var repositorio = new RepositorioBase<DispositivoTeste, int>(this.contexto);

        //    var disp = repositorio.ConsultarTodos().Last();

        //    var status = string.Empty;

        //    var codigos = new List<string>();
        //    codigos.Add(disp.Identificador.Trim() + ":" + (disp.Status ? "1":"0"));
            
        //    status = string.Join("|", codigos);
        //    return status;
        //}

        //public string MudarStatusArduino(List<DispositivoTeste> dispositivos)
        //{
        //    var repositorio = new RepositorioBase<DispositivoTeste, int>(this.contexto);

        //    foreach (var item in dispositivos)
        //    {
        //        repositorio.Inserir(item);
        //    }

        //    repositorio.SaveChanges();

        //    return "Operação efetuada com sucesso!";

        //}

    }
}

