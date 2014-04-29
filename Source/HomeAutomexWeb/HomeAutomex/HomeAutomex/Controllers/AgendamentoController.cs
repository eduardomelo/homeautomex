using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Security;
using AutoMapper;
using HomeAutomex.HomeAutomexService;
using HomeAutomex.Models;
using HomeAutomexLibrary.Entidade;
using Newtonsoft.Json;

namespace HomeAutomex.Controllers
{
    public class AgendamentoController : Controller
    {
        //
        // GET: /Agendamento/
        private HomeAutomexWSSoapClient webService;
        public AgendamentoController()
        {

            this.webService = new HomeAutomexWSSoapClient();
        
        }

        public ActionResult RegistrarAgendamento()
        {
            ViewBag.Cenario = GetDropDownCenario();
            
            return View();
        }
        public List<SelectListItem> GetDropDownCenario()
        {
            var lista = new List<SelectListItem>();
            var x = webService.ConsutarTodosCenario();
            var cenario = JsonConvert.DeserializeObject<List<CenarioModel>>(x);
            foreach (var item in cenario)
            {
                lista.Add(new SelectListItem() { Text = item.Descricao, Value = item.Chave.ToString() });
            }
            return lista;
        }
        [HttpPost]
        [AllowAnonymous]
        [ValidateAntiForgeryToken]
        public ActionResult RegistrarAgendamento(AgendamentoModel model)
        {
            if (ModelState.IsValid)
            {
                try
                {
                   
                    var agendamento = Mapper.DynamicMap<Agendamento>(model);
                    agendamento.Usuarios = new List<Usuario> { new Usuario { Chave = (Session["Usuario"] as UsuarioModel).Chave } };
                    var retorno = webService.InserirAgendamento(JsonConvert.SerializeObject(agendamento));
                    if (retorno.StartsWith("Erro:"))
                    {
                        ModelState.AddModelError("WSErro", retorno);
                    }
                    else
                    {
                        return RedirectToAction("ListarAgendamento", "Agendamento");
                    }
                }
                catch (MembershipCreateUserException e)
                {
                    ModelState.AddModelError("", e);
                }
            }

            return View(model);
        }

        public ActionResult ListarAgendamento(string pesquisa)
        {
            if (ModelState.IsValid)
            {
                var webService = new HomeAutomexWSSoapClient();
                //     var chave = 1;
                var x = webService.ConsutarTodosAgendamento();
                //    var x = webService.ConsutarTodosCenarioPorUsuarioChave(chave.ToString());
                var agendamento = JsonConvert.DeserializeObject<List<AgendamentoModel>>(x);
                if (!string.IsNullOrEmpty(pesquisa))
                    return View(agendamento.Where(e =>
                                e.Descricao.Contains(pesquisa) ||
                                e.Descricao.Contains(pesquisa)));
                return View(agendamento);
            }
            return View();
        }

        [HttpPost]
        [AllowAnonymous]
        public ActionResult EditarAgendamento(AgendamentoModel model)
        {
            if (ModelState.IsValid)
            {
                try
                {
                   
                    var webService = new HomeAutomexWSSoapClient();
                    var agendamento = JsonConvert.SerializeObject(model);
                    var x = webService.AlterarAgendamento(agendamento);
                    if (x.StartsWith("Erro:"))
                    {
                        ModelState.AddModelError("WSErro", x);
                    }
                    else
                    {
                        return RedirectToAction("ListarAgendamento", "Agendamento");
                    }
                }
                catch (MembershipCreateUserException e)
                {
                    ModelState.AddModelError("", e);
                }

            }
            return View(model);
        }
        [AllowAnonymous]
        public ActionResult DeleteAgendamento(int chave)
        {
            var retorno = JsonConvert.DeserializeObject(webService.ExcluirAgendamento(chave.ToString()));
            return RedirectToAction("ListarAgendamento", "Agendamento");
        }
        public ActionResult EditarAgendamento(int chave)
        {
            var agendamento = JsonConvert.DeserializeObject<AgendamentoModel>(webService.BuscarAgendamentoPorChave(chave.ToString()));
            ViewBag.Cenario = GetDropDownCenario();
           
            return View(agendamento);
        }

    }
}
