//using System;
//using System.Collections.Generic;
//using System.Linq;
//using System.Web;
//using System.Web.Mvc;
//using System.Web.Security;
//using AutoMapper;
//using HomeAutomex.HomeAutomexService;
//using HomeAutomex.Models;
//using HomeAutomexLibrary.Entidade;
//using Newtonsoft.Json;

//namespace HomeAutomex.Controllers
//{
//    public class CenarioController : Controller
//    {
//        //
//        // GET: /Cenario/
//        private HomeAutomexWSSoapClient webService;

//        public CenarioController() {

//            this.webService = new HomeAutomexWSSoapClient();
        
//        }

//        public ActionResult RegistrarCenario()
//        {
//            ViewBag.Ambientes = GetDropDownAmbientes();
//            return View();
//        }
//        [HttpPost]
//        [AllowAnonymous]
//        [ValidateAntiForgeryToken]
//        public ActionResult RegistrarCenario(CenarioModel model)
//        {
//            if (ModelState.IsValid)
//            {
//                try
//                {
//                   var cenario = Mapper.DynamicMap<Cenario>(model);
//                   var retorno = webService.InserirCenario (JsonConvert.SerializeObject(cenario));
//                    if (retorno.StartsWith("Erro:"))
//                    {
//                        ModelState.AddModelError("WSErro", retorno);
//                    }
//                    else
//                    {
//                        return RedirectToAction("ListarCenario", "Cenario");
//                    }
//                }
//                catch (MembershipCreateUserException e)
//                {
//                    ModelState.AddModelError("", e);
//                }
//            }
        
//            return View(model);
//        }
//        public ActionResult ListarCenario(string pesquisa)
//        {
//            if (ModelState.IsValid)
//            {
//                var webService = new HomeAutomexWSSoapClient();
//           //     var chave = 1;
//                var x = webService.ConsutarTodosCenario();
//            //    var x = webService.ConsutarTodosCenarioPorUsuarioChave(chave.ToString());
//                var cenario = JsonConvert.DeserializeObject<List<CenarioModel>>(x);
//                if (!string.IsNullOrEmpty(pesquisa))
//                    return View(cenario.Where(e =>
//                                e.Descricao.Contains(pesquisa) ||
//                                e.Descricao.Contains(pesquisa)));
//                return View(cenario);
//            }
//            return View();
//        }

//        [HttpPost]
//        [AllowAnonymous]
//        public ActionResult EditarCenario(CenarioModel model)
//        {
//            if (ModelState.IsValid)
//            {
//                try
//                {
//                    var webService = new HomeAutomexWSSoapClient();
//                    var cenario = JsonConvert.SerializeObject(model);
//                    var x = webService.AlterarCenario(cenario);
//                    if (x.StartsWith("Erro:"))
//                    {
//                        ModelState.AddModelError("WSErro", x);
//                    }
//                    else
//                    {
//                        return RedirectToAction("ListarCenario", "Cenario");
//                    }
//                }
//                catch (MembershipCreateUserException e)
//                {
//                    ModelState.AddModelError("", e);
//                }

//            }
//            return View(model);
//        }

//        [AllowAnonymous]
//        public ActionResult DeleteCenario(int chave)
//        {
//            var retorno = JsonConvert.DeserializeObject(webService.ExcluirCenario(chave.ToString()));
//            return RedirectToAction("ListarCenario", "Cenario");
//        }
//        public ActionResult EditarCenario(int chave)
//        {
//            var cenario = JsonConvert.DeserializeObject<CenarioModel>(webService.BuscarCenarioPorChave(chave.ToString()));
//            return View(cenario);
//        }

//        public List<SelectListItem> GetDropDownAmbientes()
//        {
//            var lista = new List<SelectListItem>();
//            //var chave = 1;
//            var x = webService.ConsultarTodosAmbientePorUsuarioChave((Session["Usuario"] as UsuarioModel).Chave.ToString());
//            var ambientes = JsonConvert.DeserializeObject<List<AmbienteModel>>(x);
//            foreach (var item in ambientes)
//            {
//                lista.Add(new SelectListItem() { Text = item.Descricao, Value = item.Chave.ToString() });
//            }
//            return lista;
//        }
//    }

//}
