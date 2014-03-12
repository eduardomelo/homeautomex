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
    public class ResidenciaController : Controller
    {
        //
        // GET: /Residencia/

        public ActionResult RegistrarResidencia()
        {
            return View();
        }

        [HttpPost]
        [AllowAnonymous]
        [ValidateAntiForgeryToken]
        public ActionResult RegistrarResidencia(ResidenciaModel model)
        {
            if (ModelState.IsValid)
            {
                try
                {
                    var webService = new HomeAutomexWSSoapClient();
                    var residencia = JsonConvert.SerializeObject(new ResidenciaModel
                    {
                        Logradouro = model.Logradouro,
                        Cidade = model.Cidade,
                        Bairro = model.Bairro,
                        Cep = model.Numero,
                        Complemento = model.Complemento,
                      
                    });
                    var x = webService.InserirResidencia(residencia);
                    if (x.StartsWith("Erro:"))
                    {
                        ModelState.AddModelError("WSErro", x);
                    }
                    else
                    {
                        return RedirectToAction("ListarUsuario", "Account");
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
        public ActionResult ListarResidencia(string pesquisa)
        {
            if (ModelState.IsValid)
            {
                var webService = new HomeAutomexWSSoapClient();
                var x = webService.ConsutarTodosResidecia();
                var usuario = JsonConvert.DeserializeObject<List<UsuarioModel>>(x);
                if (!string.IsNullOrEmpty(pesquisa))
                    return View(usuario.Where(e =>
                                e.Nome.Contains(pesquisa) ||
                                e.Login.Contains(pesquisa)));
                return View(usuario);
            }
            return View();
        }


    }
}
