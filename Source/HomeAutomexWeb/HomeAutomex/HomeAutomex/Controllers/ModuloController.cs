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
            ViewBag.BOData = GetDropDown();
            return View();
        }
        [HttpPost]
        [AllowAnonymous]
        [ValidateAntiForgeryToken]
        public ActionResult Registrar(ModuloModel model)
        {
            if (ModelState.IsValid)
            {
                try
                {
                    var webService = new HomeAutomexWSSoapClient();
                    var modulo = JsonConvert.SerializeObject(new ModuloModel
                    {


                        NumeroIP = model.NumeroIP,
                        NumeroPorta = model.NumeroPorta,
                        Nome = model.Nome,
                        NumeroMac = model.NumeroMac,
                        Residencia = model.Residencia
                    });
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
            return View(model);
        }

        public List<ResidenciaModel> GetDropDown()
        {
            List<ResidenciaModel> residencias = new List<ResidenciaModel>();
            var x = webService.ConsutarTodosResidecia();
            var modulo = JsonConvert.DeserializeObject<List<ResidenciaModel>>(x);
            foreach (var temp in modulo)
            {
                residencias.Add(new ResidenciaModel() { Numero = temp.Numero, Chave = temp.Chave });
            }
            return residencias;
        }
       
    }
}
