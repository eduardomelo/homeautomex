using HomeAutomexLibrary.Entidade;
using HomeAutomexLibrary.Repositorio;
using HomeAutomexLibrary.Repositorio.Database;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Negocio
{  //edson
    public class CenarioNegocio : NegocioBase<Cenario, int>
    {
        private CenarioRepositorio cenarioRepositorio;
        private UTCenarioRepositorio utCenarioRepositorio;
        private DispositivoRepositorio dispositivoRepositorio;
        private DatabaseContext contexto;

        public CenarioNegocio(DatabaseContext contexto)
        {
            this.contexto = contexto;
            this.utCenarioRepositorio = new UTCenarioRepositorio(contexto);
            cenarioRepositorio = new CenarioRepositorio(contexto);
            dispositivoRepositorio = new DispositivoRepositorio(contexto);
        }
        public string InserirCenario(Cenario cenario)
        {
            cenario.Desativado = true;
            cenarioRepositorio.Inserir(cenario);
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

        public string AlterarCenario(Cenario cenarioNovo)
        {
            var ids = cenarioNovo.Dispositivo.Select(e => e.Chave).ToList();
            var dispositivo = new List<Dispositivo>();
            cenarioNovo.Dispositivo.Clear();
            var cenario = cenarioRepositorio.BuscarPorChave(cenarioNovo.Chave);
            foreach (var chave in ids)
            {
                dispositivo.Add(dispositivoRepositorio.BuscarPorChave(chave));
            }

            cenario.Dispositivo.AddRange(dispositivo);

            cenario.Desativado = true;
            cenarioRepositorio.Alterar(cenario);



            try
            {
                cenarioRepositorio.SaveChanges();
                return "Operação realizada com sucesso!";
            }
            catch (Exception ex)
            {

                throw new Exception(ex.InnerException.Message != null ? ex.InnerException.Message : ex.Message);
            }
        }
        public string CriarCenarioDispositivo(Cenario cenarioNovo, bool status)
        {
            var ids = cenarioNovo.Dispositivo.Select(e => e.Chave).ToList();
            var dispositivo = new List<Dispositivo>();
            cenarioNovo.Dispositivo.Clear();
            var cenario = cenarioRepositorio.BuscarPorChave(cenarioNovo.Chave);
            foreach (var chave in ids)
            {
                dispositivo.Add(dispositivoRepositorio.BuscarPorChave(chave));
                dispositivo[0].Status = status;

            }
            cenario.Dispositivo.AddRange(dispositivo);
            cenarioRepositorio.Alterar(cenario);

            var utCenario = new UTCenario();
            utCenario.CD_Cenario = cenario.Chave;
            utCenario.CD_Dispositivo = dispositivo[0].Chave;
            utCenario.StatusDispositivo = status;
            this.utCenarioRepositorio.Inserir(utCenario);
            try
            {
                cenarioRepositorio.SaveChanges();
                return "Operação realizada com sucesso!";
            }
            catch (Exception ex)
            {

                throw new Exception(ex.InnerException.Message != null ? ex.InnerException.Message : ex.Message);
            }
        }



        public string AtivarCenarioDispositivo(Cenario cenario)
        {
            var cenarioNovo = new Cenario();
        
            List<UTCenario> utCenario = new List<UTCenario>();
            cenarioNovo = repositorio.BuscarPorChave(cenario.Chave);

            foreach (Dispositivo dispositivo in cenarioNovo.Dispositivo)
            {
              utCenario = utCenarioRepositorio.Consultar(e => e.CD_Cenario == cenarioNovo.Chave && e.CD_Dispositivo == dispositivo.Chave).ToList();

                var dispositivoNovo = dispositivoRepositorio.BuscarPorChave(dispositivo.Chave);
                if (utCenario[0].CD_Dispositivo == dispositivo.Chave && utCenario[0].CD_Cenario == cenarioNovo.Chave)
                {

                    dispositivoNovo.Status = utCenario[0].StatusDispositivo;
                    this.dispositivoRepositorio.Alterar(dispositivoNovo);
                }
            }
            try
            {
                cenarioRepositorio.SaveChanges();
                return "Operação realizada com sucesso!";
            }
            catch (Exception ex)
            {

                throw new Exception(ex.InnerException.Message != null ? ex.InnerException.Message : ex.Message);
            }
        }
        public string DesativarCenarioDispositivo(Cenario cenario)
        {
            var cenarioNovo = new Cenario();
            cenarioNovo = repositorio.BuscarPorChave(cenario.Chave);
            foreach (Dispositivo dispositivo in cenarioNovo.Dispositivo)
            {
                var dispositivoNovo = dispositivoRepositorio.BuscarPorChave(dispositivo.Chave);
                dispositivoNovo.Status = false;
                this.dispositivoRepositorio.Alterar(dispositivoNovo);
            }
            try
            {
                cenarioRepositorio.SaveChanges();
                return "Operação realizada com sucesso!";
            }
            catch (Exception ex)
            {

                throw new Exception(ex.InnerException.Message != null ? ex.InnerException.Message : ex.Message);
            }
        }

        public string RemoverCenarioPorChave(int chave)
        {
            Cenario cenario = new Cenario();
            cenario = BuscarPorChave(chave);
            cenario.Desativado = false;
            base.Alterar(cenario);
            //    base.RemoverPorChave(chave);
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
        public List<Cenario> ConsultarTodosCenarioPorUsuarioChave(int chave)
        {

            return this.cenarioRepositorio.Consultar(e => e.Usuario == chave && e.Desativado == true).ToList();
        }

        public List<Cenario> ConsultarTodosCenario()
        {
            return base.ConsultarTodos().ToList();
        }
    }
}

