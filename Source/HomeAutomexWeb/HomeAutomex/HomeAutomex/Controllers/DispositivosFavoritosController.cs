using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using HomeAutomex.HomeAutomexService;
using HomeAutomex.Models;
using Newtonsoft.Json;

namespace HomeAutomex.Controllers
{
    public class DispositivosFavoritosController : Controller
    {
        //
        // GET: /DispositivosFavoritos/

        public ActionResult Index()
        {
            return View();
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
        public ActionResult EditarStatus(int chave, bool status) {

            return View();
        }


    }
}
