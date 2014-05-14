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
    public class CenarioController : Controller
    {
        //
        // GET: /Cenario/
        private HomeAutomexWSSoapClient webService;
        public CenarioController()
        {
            this.webService = new HomeAutomexWSSoapClient();
        }

        public ActionResult RegistrarCenario()
        {
            return View();
        }
        public ActionResult AtivarCenario()
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
        public ActionResult AssociarDispositivo(string pesquisa)
        {
            try
            {
                var chave = (Session["Usuario"] as UsuarioModel).Chave.ToString();
                webService = new HomeAutomexWSSoapClient();
                var model = JsonConvert.DeserializeObject<List<Residencia>>(webService
                    .ConsultarResidenciaPorUsuarioChave(JsonConvert.SerializeObject((Session["Usuario"] as UsuarioModel))))
                    .Select(e => Mapper.DynamicMap<ResidenciaModel>(e));
                ViewBag.Ambientes = JsonConvert.DeserializeObject<List<AmbienteModel>>(webService.ConsultarTodosAmbientePorUsuarioChave(chave.ToString()));
                var dispositivos = JsonConvert.DeserializeObject<List<DispositivoModel>>(webService.ConsutarTodosDispositivoPorUsuarioChave(chave.ToString()));
                ViewBag.Dispositivos = dispositivos;
                ViewBag.Cenario = GetDropDownCenario();
                return View(model);
            }
            catch (Exception)
            {

                return RedirectToAction("SessaoExpirou", "Account");
            }
        }
        [HttpGet]
        public void RegistrarDispositivosCenario(int chaveDispositivo, int chaveCenario)
        {
            if (ModelState.IsValid)
            {
                try
                {
                    var webService = new HomeAutomexWSSoapClient();
                    var cenario = JsonConvert.DeserializeObject<Cenario>(webService.BuscarCenarioPorChave(chaveCenario.ToString()));
                    cenario.Dispositivo = new List<Dispositivo> { new Dispositivo { Chave = chaveDispositivo } };
                    var retorno = webService.AlterarCenario(JsonConvert.SerializeObject(cenario));
                    if (retorno.StartsWith("Erro:"))
                    {
                        ModelState.AddModelError("WSErro", retorno);
                    }
                }
                catch (MembershipCreateUserException e)
                {
                    ModelState.AddModelError("", e);
                }
            }
        }
        [HttpPost]
        [AllowAnonymous]
        public ActionResult AtivarCenario(CenarioModel model)
        {
            if (ModelState.IsValid)
            {
                try
                {
                    var webService = new HomeAutomexWSSoapClient();
                    var cenario = JsonConvert.SerializeObject(model);
                    var x = webService.AtivarCenarioDispositivo(cenario);
                    if (x.StartsWith("Erro:"))
                    {
                        ModelState.AddModelError("WSErro", x);
                    }
                    else
                    {
                        return RedirectToAction("ListarCenario", "Cenario");
                    }
                }
                catch (MembershipCreateUserException e)
                {
                    ModelState.AddModelError("", e);
                }

            }
            ViewBag.Cenario = GetDropDownCenario();
            return View();
        }
        [HttpPost]
        [AllowAnonymous]
        [ValidateAntiForgeryToken]
        public ActionResult RegistrarCenario(CenarioModel model)
        {
            if (ModelState.IsValid)
            {
                try
                {
                    var cenario = Mapper.DynamicMap<Cenario>(model);
                    var retorno = webService.InserirCenario(JsonConvert.SerializeObject(cenario));
                    if (retorno.StartsWith("Erro:"))
                    {
                        ModelState.AddModelError("WSErro", retorno);
                    }
                    else
                    {
                        return RedirectToAction("ListarCenario", "Cenario");
                    }
                }
                catch (MembershipCreateUserException e)
                {
                    ModelState.AddModelError("", e);
                }
            }

            return View(model);
        }
        public ActionResult ListarCenario(string pesquisa)
        {
            if (ModelState.IsValid)
            {
                var webService = new HomeAutomexWSSoapClient();
                var x = webService.ConsutarTodosCenario();
                var cenario = JsonConvert.DeserializeObject<List<CenarioModel>>(x);
                if (!string.IsNullOrEmpty(pesquisa))
                    return View(cenario.Where(e =>
                                e.Descricao.Contains(pesquisa) ||
                                e.Descricao.Contains(pesquisa)));
                return View(cenario);
            }
            return View();
        }
        [HttpPost]
        [AllowAnonymous]
        public ActionResult EditarCenario(CenarioModel model)
        {
            if (ModelState.IsValid)
            {
                try
                {
                    var webService = new HomeAutomexWSSoapClient();
                    var cenario = JsonConvert.SerializeObject(model);
                    var x = webService.AlterarCenario(cenario);
                    if (x.StartsWith("Erro:"))
                    {
                        ModelState.AddModelError("WSErro", x);
                    }
                    else
                    {
                        return RedirectToAction("ListarCenario", "Cenario");
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
        public ActionResult DeleteCenario(int chave)
        {
            var retorno = JsonConvert.DeserializeObject(webService.ExcluirCenario(chave.ToString()));
            return RedirectToAction("ListarCenario", "Cenario");
        }
        public ActionResult EditarCenario(int chave)
        {
            var cenario = JsonConvert.DeserializeObject<CenarioModel>(webService.BuscarCenarioPorChave(chave.ToString()));
            return View(cenario);
        }


    }

}
