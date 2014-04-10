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
    public class DispositivoController : Controller
    {
        //
        // GET: /Dispositivo/

        private HomeAutomexWSSoapClient webService;

        public DispositivoController()
        {
            this.webService = new HomeAutomexWSSoapClient();
        }
        public ActionResult RegistrarDispositivo()
        {
            ViewBag.PortaModulo = GetDropDownPortaModulo();
            return View();
        }
        //  Carregar DropList
        public List<SelectListItem> GetDropDownPortaModulo()
        {
            var lista = new List<SelectListItem>();
            var x = webService.ConsutarTodosPortaModulo();
            var residencias = JsonConvert.DeserializeObject<List<PortaModuloModel>>(x);
            foreach (var item in residencias)
            {
                lista.Add(new SelectListItem() { Text = item.Identificador, Value = item.Chave.ToString() });
            }
            return lista;
        }

        [HttpPost]
        [AllowAnonymous]
        [ValidateAntiForgeryToken]
        public ActionResult RegistrarDispositivo(DispositivoModel model, int portaModulo)
        {
            if (ModelState.IsValid)
            {
                try
                {
                    var webService = new HomeAutomexWSSoapClient();
                    model.PortaModulo = portaModulo;
                    var dispositivo = JsonConvert.SerializeObject(model);
                    var x = webService.InserirDispositivo(dispositivo);
                    if (x.StartsWith("Erro:"))
                    {
                        ModelState.AddModelError("WSErro", x);
                    }
                    else
                    {
                        return RedirectToAction("ListarDispositivo", "Dispositivo");
                    }
                }
                catch (MembershipCreateUserException e)
                {
                    ModelState.AddModelError("", e);
                }
            }
            ViewBag.PortaModulo = GetDropDownPortaModulo();
            return View(model);
        }

        [HttpPost]
        [AllowAnonymous]
        public ActionResult EditarDispositivo(DispositivoModel model, int portaModulo)
        {
            if (ModelState.IsValid)
            {
                try
                {
                    var webService = new HomeAutomexWSSoapClient();
                    model.PortaModulo = portaModulo;
                    var dispositivo = JsonConvert.SerializeObject(model);
                    var x = webService.AlterarDispositivo(dispositivo);
                    if (x.StartsWith("Erro:"))
                    {
                        ModelState.AddModelError("WSErro", x);
                    }
                    else
                    {
                        return RedirectToAction("ListarDispositivo", "Dispositivo");
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
        public ActionResult DeleteDispositivo(int chave)
        {
            var retorno = JsonConvert.DeserializeObject(webService.ExcluirDispositivo(chave.ToString()));
            return RedirectToAction("ListarDispositivo", "Dispositivo");
        }


        public ActionResult EditarDispositivo(int chave)
        {
            ViewBag.PortaModulo = GetDropDownPortaModulo();
            var dispositivo = JsonConvert.DeserializeObject<DispositivoModel>(webService.BuscarDispositivoPorChave(chave.ToString()));
            return View(dispositivo);
        }


        public ActionResult ListarDispositivo(string pesquisa)
          {
              if (ModelState.IsValid)
              {
                  var webService = new HomeAutomexWSSoapClient();
                  var x = webService.ConsutarTodosDispositivo();
                  var dispositivo = JsonConvert.DeserializeObject<List<DispositivoModel>>(x);
                  if (!string.IsNullOrEmpty(pesquisa))
                      return View(dispositivo.Where(e =>
                                  e.Descricao.Contains(pesquisa) ||
                                  e.Descricao.Contains(pesquisa)));
                  return View(dispositivo);
              }
              return View();
          }
        
    }
}
