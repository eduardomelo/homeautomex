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
    public class AmbienteController : Controller
    {
        //
        // GET: /Ambiente/

        private HomeAutomexWSSoapClient webService;
        public AmbienteController()
        {
            this.webService = new HomeAutomexWSSoapClient();
        }
        public ActionResult RegistrarAmbiente()
        {
            ViewBag.Dispositivo = GetDropDownDispositivo();
            ViewBag.Residencia = GetDropDownResidencia();
            return View();
        }
        //  Carregar DropList
        public List<SelectListItem> GetDropDownDispositivo()
        {
            var lista = new List<SelectListItem>();
            var x = webService.ConsutarTodosDispositivo();
            var dispositivos = JsonConvert.DeserializeObject<List<DispositivoModel>>(x);
            foreach (var item in dispositivos)
            {
                lista.Add(new SelectListItem() { Text = item.Descricao, Value = item.Chave.ToString() });
            }
            return lista;
        }
        public List<SelectListItem> GetDropDownResidencia()
        {
            var lista = new List<SelectListItem>();
            var x = webService.ConsutarTodosResidecia();
            var residencias = JsonConvert.DeserializeObject<List<ResidenciaModel>>(x);
            foreach (var item in residencias)
            {
                lista.Add(new SelectListItem() { Text = "CASA: " +item.Logradouro, Value = item.Chave.ToString() });
            }
            return lista;
        }
        [HttpPost]
        [AllowAnonymous]
        [ValidateAntiForgeryToken]
        public ActionResult RegistrarAmbiente(AmbienteModel model, int dispositivo, int residencia)
        {
            if (ModelState.IsValid)
            {
                try
                {
                    var webService = new HomeAutomexWSSoapClient();
                    model.Dispositivo = dispositivo;
                    model.Residencia = residencia;

                    var ambiente = JsonConvert.SerializeObject(model);
                    var x = webService.InserirAmbiente(ambiente);
                    if (x.StartsWith("Erro:"))
                    {
                        ModelState.AddModelError("WSErro", x);
                    }
                    else
                    {
                        return RedirectToAction("ListarAmbiente", "Ambiente");
                    }
                }
                catch (MembershipCreateUserException e)
                {
                    ModelState.AddModelError("", e);
                }
            }
            ViewBag.Dispositivo = GetDropDownDispositivo();
            ViewBag.Residencia = GetDropDownResidencia();
            return View(model);
        }
        [HttpPost]
        [AllowAnonymous]
        public ActionResult EditarAmbiente(AmbienteModel model, int dispositivo, int residencia)
        {
            if (ModelState.IsValid)
            {
                try
                {
                    var webService = new HomeAutomexWSSoapClient();
                    model.Dispositivo = dispositivo;
                    model.Residencia = residencia;
                    var ambiente = JsonConvert.SerializeObject(model);
                    var x = webService.AlterarAmbiente(ambiente);
                    if (x.StartsWith("Erro:"))
                    {
                        ModelState.AddModelError("WSErro", x);
                    }
                    else
                    {
                        return RedirectToAction("ListarAmbiente", "Ambiente");
                    }
                }
                catch (MembershipCreateUserException e)
                {
                    ModelState.AddModelError("", e);
                }

            }
            return View(model);
        }
        public ActionResult EditarAmbiente(int chave)
        {
            ViewBag.Dispositivo = GetDropDownDispositivo();
            ViewBag.Residencia = GetDropDownResidencia();
            var ambiente = JsonConvert.DeserializeObject<AmbienteModel>(webService.BuscarAmbientePorChave(chave.ToString()));
            return View(ambiente);
        }
        [AllowAnonymous]
        public ActionResult DeleteAmbiente(int chave)
        {
            var retorno = JsonConvert.DeserializeObject(webService.ExcluirAmbiente(chave.ToString()));
            return RedirectToAction("ListarAmbiente", "Ambiente");
        }
        public ActionResult ListarAmbiente(string pesquisa)
        {
            if (ModelState.IsValid)
            {
                var webService = new HomeAutomexWSSoapClient();
                var x = webService.ConsutarTodosAmbiente();
                var ambiente = JsonConvert.DeserializeObject<List<AmbienteModel>>(x);
                if (!string.IsNullOrEmpty(pesquisa))
                    return View(ambiente.Where(e =>
                                e.Descricao.Contains(pesquisa) ||
                                e.Descricao.Contains(pesquisa)));
                return View(ambiente);
            }
            return View();
        }
        
    }
}
