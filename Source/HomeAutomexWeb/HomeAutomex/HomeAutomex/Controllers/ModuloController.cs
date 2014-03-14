using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Security;
using HomeAutomex.HomeAutomexService;
using HomeAutomex.Models;
using Newtonsoft.Json;

namespace HomeAutomex.Controllers
{
    public class ModuloController : Controller
    {
        //
        // GET: /Modulo/
        private HomeAutomexWSSoapClient webService;


        public ModuloController()
        {
            this.webService = new HomeAutomexWSSoapClient();
        }

        public ActionResult Registrar()
        {
            ViewBag.Residencias = GetDropDown();
            return View();
        }
        [HttpPost]
        [AllowAnonymous]
        [ValidateAntiForgeryToken]
        public ActionResult Registrar(ModuloModel model, string residencia)
        {
            if (ModelState.IsValid)
            {
                try
                {
                    var webService = new HomeAutomexWSSoapClient();
                    model.Residencia = JsonConvert.DeserializeObject<ResidenciaModel>(webService.BuscarResidenciaPorChave(residencia));
                    var modulo = JsonConvert.SerializeObject(model);
                    var x = webService.InserirModulo(modulo);
                    if (x.StartsWith("Erro:"))
                    {
                        ModelState.AddModelError("WSErro", x);
                    }
                    else
                    {
                        return RedirectToAction("Listar", "Residencia");
                    }
                }
                catch (MembershipCreateUserException e)
                {
                    ModelState.AddModelError("", e);
                }
            }
            ViewBag.Residencias = GetDropDown();
            return View(model);
        }

        public List<SelectListItem> GetDropDown()
        {
            var lista = new List<SelectListItem>();
            var x = webService.ConsutarTodosResidecia();
            var residencias = JsonConvert.DeserializeObject<List<ResidenciaModel>>(x);
            foreach (var item in residencias)
            {
                lista.Add(new SelectListItem() { Text = item.Logradouro, Value = item.Chave.ToString() });
            }
            return lista;
        }
       
    }
}
