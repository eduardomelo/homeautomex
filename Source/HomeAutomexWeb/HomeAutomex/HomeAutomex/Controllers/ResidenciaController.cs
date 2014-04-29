using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Security;
using HomeAutomex.HomeAutomexService;
using HomeAutomex.Models;
using Newtonsoft.Json;
using HomeAutomexLibrary.Entidade;
using AutoMapper;

namespace HomeAutomex.Controllers
{
    public class ResidenciaController : Controller
    {
        private HomeAutomexWSSoapClient webService;

        public ResidenciaController() {
            this.webService = new HomeAutomexWSSoapClient();
        }

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
                    var residencia = Mapper.DynamicMap<Residencia>(model);
                    residencia.Usuarios = new List<Usuario> { new Usuario { Chave = (Session["Usuario"] as UsuarioModel).Chave } };
                    var retorno = webService.InserirResidencia(JsonConvert.SerializeObject(residencia));
                    if (retorno.StartsWith("Erro:"))
                    {
                        ModelState.AddModelError("WSErro", retorno);
                    }
                    else
                    {
                        return RedirectToAction("ListarResidencia", "Residencia");
                    }
                }
                catch (MembershipCreateUserException e)
                {
                    ModelState.AddModelError("", e);
                }
            }
            return View(model);
        }
        public ActionResult EditarResidencia(int chave)
        {
            var residencia = JsonConvert.DeserializeObject<ResidenciaModel>(webService.BuscarResidenciaPorChave(chave.ToString()));
            return View(residencia);
        }


        [HttpPost]
        [AllowAnonymous]
        public ActionResult EditarResidencia(ResidenciaModel model)
        {
            if (ModelState.IsValid)
            {
                try
                {
                    var webService = new HomeAutomexWSSoapClient();
                    var residencia = JsonConvert.SerializeObject(model);
                    var x = webService.AlterarResidencia(residencia);
                    if (x.StartsWith("Erro:"))
                    {
                        ModelState.AddModelError("WSErro", x);
                    }
                    else
                    {
                        return RedirectToAction("ListarResidencia", "Residencia");
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
        public ActionResult DeleteResidencia(int chave)
        {
            var retorno = JsonConvert.DeserializeObject(webService.ExcluirResidencia(chave.ToString()));
            return RedirectToAction("ListarResidencia", "Residencia");
        }


        [AllowAnonymous]
        public ActionResult ListarResidencia(string pesquisa)
        {
            try
            {



                if (ModelState.IsValid)
                {

                    var webService = new HomeAutomexWSSoapClient();
                    var retorno = webService.ConsultarResidenciaPorUsuarioChave(JsonConvert.SerializeObject((Session["Usuario"] as UsuarioModel)));
                    var residencia = JsonConvert.DeserializeObject<List<ResidenciaModel>>(retorno);
                    if (!string.IsNullOrEmpty(pesquisa))
                        return View(residencia.Where(e =>
                                    e.Cep.Contains(pesquisa) ||
                                    e.Logradouro.Contains(pesquisa)));
                    return View(residencia);
                }
                return View();

            }

            catch (Exception)
            {

                return RedirectToAction("SessaoExpirou", "Account");
            }
        }
    }
}
