using HomeAutomexLibrary.Entidade;
using HomeAutomexLibrary.Repositorio;
using HomeAutomexLibrary.Repositorio.Database;
using System;
using System.Collections.Generic;
using System.Data.Objects;
using System.Linq;
using System.Threading;
using System.Web;
using System.Web.Security;
using System.Web.SessionState;

namespace HomeAutomexWebApplication
{
    public class Global : System.Web.HttpApplication
    {

        protected void Application_Start(object sender, EventArgs e)
        {
            var thAgendamento = new Thread(new ThreadStart(VerificarAgendamento));
            thAgendamento.Start();
        }

        protected void Session_Start(object sender, EventArgs e)
        {

        }

        protected void Application_BeginRequest(object sender, EventArgs e)
        {

        }

        protected void Application_AuthenticateRequest(object sender, EventArgs e)
        {

        }

        protected void Application_Error(object sender, EventArgs e)
        {

        }

        protected void Session_End(object sender, EventArgs e)
        {

        }

        protected void Application_End(object sender, EventArgs e)
        {

        }

        protected void VerificarAgendamento()
        {
            var contexto = new DatabaseContext();
            var agendamentoRepositorio = new RepositorioBase<Agendamento, int>(contexto);
            var dispositivoRepositorio = new RepositorioBase<Dispositivo, int>(contexto);
            while (true)
            {
                var agendamentos = agendamentoRepositorio.Consultar(e =>
                    EntityFunctions.DiffSeconds(e.DataAgendamento.Value, DateTime.Now) > 0
                    && EntityFunctions.DiffSeconds(e.DataAgendamento.Value, DateTime.Now) < 2
                    && e.Ativo).ToList();
                if (agendamentos.Any())
                {
                    foreach (var item in agendamentos)
                    {
                        var dispositivo = dispositivoRepositorio.BuscarPorChave(item.Dispositivo);
                        dispositivo.Status = true;
                        contexto.SaveChanges();
                    }

                    agendamentos.ForEach(e => e.Ativo = false);
                    contexto.SaveChanges();

                    Thread.Sleep(1000);
                }
            }
        }

    }
}