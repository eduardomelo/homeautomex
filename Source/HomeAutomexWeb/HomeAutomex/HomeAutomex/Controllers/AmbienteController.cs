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
            ViewBag.Residencias = GetDropDownResidencia();
            return View();
        }
        //  Carregar DropList 
        public List<SelectListItem> GetDropDownResidencia()
        {
            var lista = new List<SelectListItem>();
            var x = webService.ConsultarResidenciaPorUsuarioChave(JsonConvert.SerializeObject((Session["Usuario"] as UsuarioModel)));
            var residencias = JsonConvert.DeserializeObject<List<ResidenciaModel>>(x);
            foreach (var item in residencias)
            {
                lista.Add(new SelectListItem() { Text = item.Nome, Value = item.Chave.ToString() });
            }
            return lista;
        }

        [HttpPost]
        [AllowAnonymous]
        [ValidateAntiForgeryToken]
        public ActionResult RegistrarAmbiente(AmbienteModel model)
        {
            IEnumerable<ModelError> erros;

            if (ModelState.IsValid)
            {
                try
                {
                    if (model.ChaveResidencia == 0)
                        ModelState.AddModelError("Residencia", "Selecione uma residência.");
                    else
                    {
                        var webService = new HomeAutomexWSSoapClient();
                        model.Residencia = JsonConvert.DeserializeObject<ResidenciaModel>(webService.BuscarResidenciaPorChave(model.ChaveResidencia.ToString()));
                        var ambiente = Mapper.DynamicMap<Ambiente>(model);
                        var retorno = webService.InserirAmbiente(JsonConvert.SerializeObject(ambiente));
                        if (retorno.StartsWith("Erro:"))
                        {
                            ModelState.AddModelError("WSErro", retorno);
                        }
                        else
                        {
                            return RedirectToAction("ListarAmbiente", "Ambiente");
                        }
                    }
                }
                catch (MembershipCreateUserException e)
                {
                    ModelState.AddModelError("", e);
                }
            }
            else 
                erros = ModelState.Values.SelectMany(e => e.Errors);
            ViewBag.Residencia = GetDropDownResidencia();
            return View(model);
        }


        [HttpPost]
        [AllowAnonymous]
        public ActionResult EditarAmbiente(AmbienteModel model)
        {
            if (ModelState.IsValid)
            {
                try
                {
                    var webService = new HomeAutomexWSSoapClient();
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
            ViewBag.Residencias = GetDropDownResidencia();
            var ambiente = Mapper.DynamicMap<AmbienteModel>(JsonConvert.DeserializeObject<Ambiente>(webService.BuscarAmbientePorChave(chave.ToString())));
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
               var x = webService.ConsultarTodosAmbientePorUsuarioChave(chave.ToString());
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
