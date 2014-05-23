using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Security;
using HomeAutomex.HomeAutomexService;
using HomeAutomex.Models;
using Newtonsoft.Json;
using AutoMapper;
using HomeAutomexLibrary.Entidade;

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
            ViewBag.Ambientes = GetDropDownAmbientes();
            return View();
        }
        //  Carregar DropList
        public List<SelectListItem> GetDropDownPortaModulo()
        {
            var lista = new List<SelectListItem>();
            var x = webService.ConsutarTodosPorta();
            var portas = JsonConvert.DeserializeObject<List<PortaModel>>(x);
            foreach (var item in portas)
            {
                lista.Add(new SelectListItem() { Text = item.Identificador, Value = item.Chave.ToString() });
            }
            return lista;
        }

        public List<SelectListItem> GetDropDownAmbientes()
        {
            var lista = new List<SelectListItem>();

            var x = webService.ConsultarTodosAmbientePorUsuarioChave((Session["Usuario"] as UsuarioModel).Chave.ToString());
            var ambientes = JsonConvert.DeserializeObject<List<AmbienteModel>>(x);
            foreach (var item in ambientes)
            {
                lista.Add(new SelectListItem() { Text = item.Residencia.Nome + ": " + item.Descricao, Value = item.Chave.ToString() });
            }
            return lista;
        }

        [HttpPost]
        [AllowAnonymous]
        [ValidateAntiForgeryToken]
        public ActionResult RegistrarDispositivo(DispositivoModel model)
        {
            if (ModelState.IsValid)
            {
                try
                {
                    model.Porta = JsonConvert.DeserializeObject<PortaModel>(webService.BuscarPortaPorChave(model.ChavePorta.ToString()));
                    model.Ambiente = JsonConvert.DeserializeObject<AmbienteModel>(webService.BuscarAmbientePorChave(model.ChaveAmbiente.ToString()));
                    var dispositivo = Mapper.DynamicMap<Dispositivo>(model);
                    var retorno = webService.InserirDispositivo(JsonConvert.SerializeObject(dispositivo));
                    if (retorno.StartsWith("Erro:"))
                    {
                        ModelState.AddModelError("WSErro", retorno);
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
        public ActionResult EditarDispositivo(DispositivoModel model)
        {
            if (ModelState.IsValid)
            {
                try
                {
                    model.Porta = JsonConvert.DeserializeObject<PortaModel>(webService.BuscarPortaPorChave(model.ChavePorta.ToString()));
                    model.Ambiente = JsonConvert.DeserializeObject<AmbienteModel>(webService.BuscarAmbientePorChave(model.ChaveAmbiente.ToString()));
                    var dispositivo = Mapper.DynamicMap<Dispositivo>(model);
                    var retorno = webService.AlterarDispositivo(JsonConvert.SerializeObject(dispositivo));
                    if (retorno.StartsWith("Erro:"))
                    {
                        ModelState.AddModelError("WSErro", retorno);
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
            ViewBag.Ambientes = GetDropDownAmbientes();
            var dispositivo = Mapper.DynamicMap<DispositivoModel>(JsonConvert.DeserializeObject<Dispositivo>(webService.BuscarDispositivoPorChave(chave.ToString())));
            return View(dispositivo);
        }


        public ActionResult ListarDispositivo(string pesquisa)
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
                var x = webService.ConsutarTodosDispositivoPorUsuarioChave(chave.ToString());
                var dispositivo = JsonConvert.DeserializeObject<List<DispositivoModel>>(x);
                if (!string.IsNullOrEmpty(pesquisa))
                    return View(dispositivo.Where(e =>
                                e.Descricao.Contains(pesquisa) ||
                                e.Descricao.Contains(pesquisa)));
                return View(dispositivo);
            }
            return View();
        }

        [HttpGet]
        public void AlterarStatus(int chave, bool status)
        {
            try
            {
                var dispositivo = JsonConvert.DeserializeObject<Dispositivo>(webService.BuscarDispositivoPorChave(chave.ToString()));
                dispositivo.Status = status;
                var retorno = webService.AlterarDispositivo(JsonConvert.SerializeObject(dispositivo));
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
}
