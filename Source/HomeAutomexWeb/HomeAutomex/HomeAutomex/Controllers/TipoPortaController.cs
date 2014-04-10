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
    public class TipoPortaController : Controller
    {
        //
        // GET: /TipoPorta/

         private HomeAutomexWSSoapClient webService;
         public TipoPortaController()
         {
            this.webService = new HomeAutomexWSSoapClient();
         }
         public ActionResult RegistrarTipoPorta()
         {
             return View();
         }


         [HttpPost]
         [AllowAnonymous]
         [ValidateAntiForgeryToken]
         public ActionResult RegistrarTipoPorta(TipoPortaModel model)
         {
             if (ModelState.IsValid)
             {
                 try
                 {
                     var webService = new HomeAutomexWSSoapClient();
                     var tipoPorta = JsonConvert.SerializeObject(new TipoPortaModel
                     {
                        Tipo = model.Tipo,
                        Identificador = model.Identificador
                     });
                     var x = webService.InserirTipoPorta(tipoPorta);
                     if (x.StartsWith("Erro:"))
                     {
                         ModelState.AddModelError("WSErro", x);
                     }
                     else
                     {
                         return RedirectToAction("ListarTipoPorta", "TipoPorta");
                     }
                 }
                 catch (MembershipCreateUserException e)
                 {
                     ModelState.AddModelError("", e);
                 }
             }
             return View(model);
         }


         public ActionResult EditarTipoPorta(int chave)
         {
             var tipoPorta = JsonConvert.DeserializeObject<TipoPortaModel>(webService.BuscarTipoPortaPorChave(chave.ToString()));
             return View(tipoPorta);
         }


         [HttpPost]
         [AllowAnonymous]
         public ActionResult EditarTipoPorta(TipoPortaModel model)
         {
             if (ModelState.IsValid)
             {
                 try
                 {
                     var webService = new HomeAutomexWSSoapClient();
                     var tipoPorta = JsonConvert.SerializeObject(model);
                     var x = webService.AlterarTipoPorta(tipoPorta);
                     if (x.StartsWith("Erro:"))
                     {
                         ModelState.AddModelError("WSErro", x);
                     }
                     else
                     {
                         return RedirectToAction("ListarTipoPorta", "TipoPorta");
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
         public ActionResult DeleteTipoPorta(int chave)
         {
             var retorno = JsonConvert.DeserializeObject(webService.ExcluirTipoPorta(chave.ToString()));
             return RedirectToAction("ListarTipoPorta", "TipoPorta");
         }


         [AllowAnonymous]
         public ActionResult ListarTipoPorta(string pesquisa)
         {
             if (ModelState.IsValid)
             {
                 var webService = new HomeAutomexWSSoapClient();
                 var x = webService.ConsutarTodosTipoPorta();
                 var tipoPorta = JsonConvert.DeserializeObject<List<TipoPortaModel>>(x);
                 if (!string.IsNullOrEmpty(pesquisa))
                     return View(tipoPorta.Where(e =>
                                 e.Tipo.Contains(pesquisa) ||
                                 e.Identificador.Contains(pesquisa)));
                 return View(tipoPorta);
             }
             return View();
         }

    }
}
