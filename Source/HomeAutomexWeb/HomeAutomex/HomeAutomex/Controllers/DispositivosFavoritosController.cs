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
    public class DispositivosFavoritosController : Controller
    {
        private DispositivoModel dispositivoModel;
        private HomeAutomexWSSoapClient webService;
        public DispositivosFavoritosController()
        {
            this.dispositivoModel = new DispositivoModel();
            this.webService = new HomeAutomexWSSoapClient();

        }
        public ActionResult Index()
        {
            return View();
        }
        public ActionResult ListarDispositivo(string pesquisa)
        {
            try
            {

            var chave = (Session["Usuario"] as UsuarioModel).Chave.ToString();
            webService = new HomeAutomexWSSoapClient();
            var model = JsonConvert.DeserializeObject<List<Residencia>>(webService
                .ConsultarResidenciaPorUsuarioChave(JsonConvert.SerializeObject((Session["Usuario"] as UsuarioModel))))
                .Select(e => Mapper.DynamicMap<ResidenciaModel>(e));
            ViewBag.Ambientes = JsonConvert.DeserializeObject<List<AmbienteModel>>(webService.ConsultarTodosAmbientePorUsuarioChave(chave.ToString()));

            var dispositivos = JsonConvert.DeserializeObject<List<DispositivoModel>>(webService.ConsutarTodosDispositivoFavorito());
            ViewBag.Dispositivos = dispositivos;

            return View(model);
            }
            catch (Exception)
            {

                return RedirectToAction("SessaoExpirou", "Account");
            }

        }
        [HttpGet]
        public void EditarStatus(int chave, bool status)
        {
            if (ModelState.IsValid)
            {
                try
                {
                    //var webService = new HomeAutomexWSSoapClient();
                    //var dispositivo = JsonConvert.DeserializeObject<DispositivoModel>(webService.BuscarDispositivoPorChave(chave.ToString()));
                    //dispositivo.Desativado  = status;
                    //dispositivo.Status      = status;
                    //var objeto = JsonConvert.SerializeObject(dispositivo);
                    //var x = webService.AlterarDispositivo(objeto);
                }
                catch (MembershipCreateUserException e)
                {
                    ModelState.AddModelError("", e);
                }

            }
        }

        [HttpPost]
        [AllowAnonymous]
        public ActionResult EditarDispositivo(DispositivoModel model, int portaModulo)
        {
            if (ModelState.IsValid)
            {
                try
                {
                    //var webService = new HomeAutomexWSSoapClient();
                    //model.PortaModulo = portaModulo;
                    //var dispositivo = JsonConvert.SerializeObject(model);
                    //var x = webService.AlterarDispositivo(dispositivo);
                    //if (x.StartsWith("Erro:"))
                    //{
                    //    ModelState.AddModelError("WSErro", x);
                    //}
                    //else
                    //{
                    //    return RedirectToAction("ListarDispositivo", "Dispositivo");
                    //}
                }
                catch (MembershipCreateUserException e)
                {
                    ModelState.AddModelError("", e);
                }
            }
            return View(model);
        }

    }
    
}