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
    public class DispositivosFavoritosController : Controller
    {
        private DispositivoModel dispositivoModel;

        public DispositivosFavoritosController()
        {
            this.dispositivoModel = new DispositivoModel();

        }
        public ActionResult Index()
        {
            return View();
        }
        public ActionResult ListarDispositivo(string pesquisa)
        {
            if (ModelState.IsValid)
            {
                var webService = new HomeAutomexWSSoapClient();
                var x = webService.ConsutarTodosDispositivoFavorito();
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