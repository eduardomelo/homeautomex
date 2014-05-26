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
            ViewBag.Dispositivo = GetDropDownDispositivo();
            return View();
        }
        public List<SelectListItem> GetDropDownDispositivo()
        {
            var lista = new List<SelectListItem>();
            var chave = (Session["Usuario"] as UsuarioModel).Chave.ToString();
            var x = webService.ConsutarTodosDispositivoPorUsuarioChave(chave.ToString());
            var dispositivo = JsonConvert.DeserializeObject<List<DispositivoModel>>(x);
            foreach (var item in dispositivo)
            {
                lista.Add(new SelectListItem() { Text = item.Ambiente.Residencia.Nome+": "+ item.Descricao, Value = item.Chave.ToString() });
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
                    var chave = "";
                    try
                    {
                        chave = (Session["Usuario"] as UsuarioModel).Chave.ToString();

                    }
                    catch (Exception)
                    {

                        return RedirectToAction("SessaoExpirou", "Account");
                    }
                    var agendamento = Mapper.DynamicMap<Agendamento>(model);
                    agendamento.Usuario = Convert.ToInt32(chave);
                    agendamento.Desativado = true;
               
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
                var chave = "";
                try
                {
                    chave = (Session["Usuario"] as UsuarioModel).Chave.ToString();


                }
                catch (Exception)
                {
                    return RedirectToAction("SessaoExpirou", "Account");
                }
                var webService = new HomeAutomexWSSoapClient();
                var x = webService.ConsutarTodosAgendamentoPorUsuarioChave(chave.ToString());
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
        public ActionResult EditarAgendamento(AgendamentoModel model, string DataHoraAgendamento)
        {
            if (ModelState.IsValid)
            {
                try
                {
                    var agendamento = Mapper.DynamicMap<Agendamento>(model);

                    try
                    {
                        var chave = (Session["Usuario"] as UsuarioModel).Chave.ToString();
                        agendamento.Usuario = Convert.ToInt32(chave);
                    }
                    catch (Exception)
                    {

                        return RedirectToAction("SessaoExpirou", "Account");
                    }
                 
                    var webService = new HomeAutomexWSSoapClient();
                    agendamento.DataAgendamento = Convert.ToDateTime(DataHoraAgendamento);
                    var x = webService.AlterarAgendamento(JsonConvert.SerializeObject(agendamento));
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
                   //
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
            try
            {
                var chaveUsuario = (Session["Usuario"] as UsuarioModel).Chave.ToString();
                var agendamento = JsonConvert.DeserializeObject<AgendamentoModel>(webService.BuscarAgendamentoPorChave(chave.ToString()));
                ViewBag.Dispositivo = GetDropDownDispositivo();

                return View(agendamento);
              
            }
            catch (Exception)
            {

                return RedirectToAction("SessaoExpirou", "Account");
            }
          
        }

    }
}
