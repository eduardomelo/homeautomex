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
        public ActionResult RegistrarModulo()
        {
            ViewBag.Residencias = GetDropDownResidencia();
            return View();
        }

        [HttpPost]
        [AllowAnonymous]
        [ValidateAntiForgeryToken]
        public ActionResult RegistrarModulo(ModuloModel model, int residencia)
        {
            if (ModelState.IsValid)
            {
                try
                {
                    var webService = new HomeAutomexWSSoapClient();
                    model.Residencia = residencia;
                    var modulo = JsonConvert.SerializeObject(model);
                    var x = webService.InserirModulo(modulo);
                    if (x.StartsWith("Erro:"))
                    {
                        ModelState.AddModelError("WSErro", x);
                    }
                    else
                    {
                        return RedirectToAction("ListarModulo", "Modulo");
                    }
                }
                catch (MembershipCreateUserException e)
                {
                    ModelState.AddModelError("", e);
                }
            }
            ViewBag.Residencias = GetDropDownResidencia();
            return View(model);
        }


        [AllowAnonymous]
        public ActionResult DeleteModulo(int chave)
        {
            var retorno = JsonConvert.DeserializeObject(webService.ExcluirModulo(chave.ToString()));
            return RedirectToAction("ListarModulo", "Modulo");
        }


        public ActionResult EditarModulo(int chave)
        {
            ViewBag.Residencias = GetDropDownResidencia();
            var modulo = JsonConvert.DeserializeObject<ModuloModel>(webService.BuscarModuloPorChave(chave.ToString()));
            return View(modulo);
        }


        [HttpPost]
        [AllowAnonymous]
        public ActionResult EditarModulo(ModuloModel model)
        {

            if (ModelState.IsValid)
            {
                try
                {
                    var webService = new HomeAutomexWSSoapClient();
                    var modulo = JsonConvert.SerializeObject(model);
                    var x = webService.AlterarModulo(modulo);
                    if (x.StartsWith("Erro:"))
                    {
                        ModelState.AddModelError("WSErro", x);
                    }
                    else
                    {
                        return RedirectToAction("ListarModulo", "Modulo");
                    }
                }
                catch (MembershipCreateUserException e)
                {
                    ModelState.AddModelError("", e);
                }

            }
            
            return View(model);
        }


        public List<SelectListItem> GetDropDownResidencia()
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



        public ActionResult ListarModulo(string pesquisa)
        {
            if (ModelState.IsValid)
            {
                var webService = new HomeAutomexWSSoapClient();
                var x = webService.ConsutarTodosModulo();
                var modulo = JsonConvert.DeserializeObject<List<ModuloModel>>(x);
                if (!string.IsNullOrEmpty(pesquisa))
                    return View(modulo.Where(e =>
                                e.Nome.Contains(pesquisa) ||
                                e.NumeroIP.Contains(pesquisa)));
                return View(modulo);
            }
            return View();
        }
       
    }
}
