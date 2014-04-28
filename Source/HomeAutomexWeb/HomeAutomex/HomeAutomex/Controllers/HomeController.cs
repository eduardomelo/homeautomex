using AutoMapper;
using HomeAutomex.HomeAutomexService;
using HomeAutomex.Models;
using HomeAutomexLibrary.Entidade;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace HomeAutomex.Controllers
{
    public class HomeController : Controller
    {
        private HomeAutomexWSSoapClient webService;
       

        public HomeController() {
            
        }

        public ActionResult Index()
        {
            var chave = 1;

            webService = new HomeAutomexWSSoapClient();

            var model = JsonConvert.DeserializeObject<List<Residencia>>(webService
                .ConsultarResidenciaPorUsuarioChave(JsonConvert.SerializeObject((Session["Usuario"] as UsuarioModel))))
                .Select(e => Mapper.DynamicMap<ResidenciaModel>(e));
            ViewBag.Ambientes = JsonConvert.DeserializeObject<List<AmbienteModel>>(webService.ConsultarTodosAmbientePorUsuarioChave(chave.ToString()));

            var dispositivos = JsonConvert.DeserializeObject<List<DispositivoModel>>(webService.ConsutarTodosDispositivoPorUsuarioChave(chave.ToString()));
            ViewBag.Dispositivos = dispositivos;

            return View(model);
        }

        public ActionResult About()
        {
            return View();
        }

        public ActionResult Contact()
        {
            ViewBag.Message = "Your contact page.";

            return View();
        }
    }
}
