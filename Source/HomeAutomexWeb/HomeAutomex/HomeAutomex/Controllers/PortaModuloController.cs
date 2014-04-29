//using System;
//using System.Collections.Generic;
//using System.Linq;
//using System.Web;
//using System.Web.Mvc;
//using System.Web.Security;
//using HomeAutomex.HomeAutomexService;
//using HomeAutomex.Models;
//using Newtonsoft.Json;

//namespace HomeAutomex.Controllers
//{
//    public class PortaModuloController : Controller
//    {
//        //
//        // GET: /PortaModulo/

//        private HomeAutomexWSSoapClient webService;

//        public PortaModuloController()
//        {
//            this.webService = new HomeAutomexWSSoapClient();
//        }
//        public ActionResult RegistrarPortaModulo()
//        {
//            ViewBag.TipoPorta = GetDropDownTipoPorta();
//            ViewBag.Modulo = GetDropDownModulo();
//            return View();
//        }
//        // Carregar DropLists
//        public List<SelectListItem> GetDropDownTipoPorta()
//        {
//            var lista = new List<SelectListItem>();
//            var x = webService.ConsutarTodosTipoPorta();
//            var tipoPorta = JsonConvert.DeserializeObject<List<TipoPortaModel>>(x);
//            foreach (var item in tipoPorta)
//            {
//                lista.Add(new SelectListItem() { Text = item.Tipo, Value = item.Chave.ToString() });
//            }
//            return lista;
//        }
//        public List<SelectListItem> GetDropDownModulo()
//        {
//            var lista = new List<SelectListItem>();
//            var x = webService.ConsutarTodosModulo();
//            var modulo = JsonConvert.DeserializeObject<List<ModuloModel>>(x);
//            foreach (var item in modulo)
//            {
//                lista.Add(new SelectListItem() { Text = item.Nome, Value = item.Chave.ToString() });
//            }
//            return lista;
//        }




//        [HttpPost]
//        [AllowAnonymous]
//        [ValidateAntiForgeryToken]
//        public ActionResult RegistrarPortaModulo(PortaModuloModel model, string tipoPorta, string modulo)
//        {
//            if (ModelState.IsValid)
//            {
//                try
//                {
//                    var webService = new HomeAutomexWSSoapClient();
//                    var portaModulo = JsonConvert.SerializeObject(new PortaModuloModel
//                    {

//                        Identificador = model.Identificador,
//                        NumeroPorta = model.NumeroPorta,
//                        TipoPorta = Convert.ToInt32(tipoPorta),
//                        Modulo = Convert.ToInt32(modulo)

//                    });
//                    var x = webService.InserirPortaModulo(portaModulo);
//                    if (x.StartsWith("Erro:"))
//                    {
//                        ModelState.AddModelError("WSErro", x);
//                    }
//                    else
//                    {
//                        return RedirectToAction("ListarPortaModulo", "PortaModulo");
//                    }
//                }
//                catch (MembershipCreateUserException e)
//                {
//                    ModelState.AddModelError("", e);
//                }
//            }
//            return View(model);
//        }


//        [HttpPost]
//        [AllowAnonymous]
//        public ActionResult EditarPortaModulo(PortaModuloModel model, int tipoPorta, int modulo)
//        {
//            if (ModelState.IsValid)
//            {
//                try
//                {
//                    var webService = new HomeAutomexWSSoapClient();
//                    model.TipoPorta = tipoPorta;
//                    model.Modulo = modulo;
//                    var portaModulo = JsonConvert.SerializeObject(model);
//                    var x = webService.AlterarPortaModulo(portaModulo);
//                    if (x.StartsWith("Erro:"))
//                    {
//                        ModelState.AddModelError("WSErro", x);
//                    }
//                    else
//                    {
//                        return RedirectToAction("ListarPortaModulo", "PortaModulo");
//                    }
//                }
//                catch (MembershipCreateUserException e)
//                {
//                    ModelState.AddModelError("", e);
//                }

//            }
//            return View(model);
//        }


//        public ActionResult EditarPortaModulo(int chave)
//        {
//            ViewBag.TipoPorta = GetDropDownTipoPorta();
//            ViewBag.Modulo = GetDropDownModulo();
//            var portamodulo = JsonConvert.DeserializeObject<PortaModuloModel>(webService.BuscarPortaModuloPorChave(chave.ToString()));
//            return View(portamodulo);
//        }


//        [AllowAnonymous]
//        public ActionResult DeletePortaModulo(int chave)
//        {
//            var retorno = JsonConvert.DeserializeObject(webService.ExcluirPortaModulo(chave.ToString()));
//            return RedirectToAction("ListarPortaModulo", "PortaModulo");
//        }


//        public ActionResult ListarPortaModulo(string pesquisa)
//        {
//            if (ModelState.IsValid)
//            {
//                var webService = new HomeAutomexWSSoapClient();
//                var x = webService.ConsutarTodosPortaModulo();
//                var portaModulo = JsonConvert.DeserializeObject<List<PortaModuloModel>>(x);
//                if (!string.IsNullOrEmpty(pesquisa))
//                    return View(portaModulo.Where(e =>
//                                e.Identificador.Contains(pesquisa) ||
//                                e.Identificador.Contains(pesquisa)));
//                return View(portaModulo);
//            }
//            return View();
//        }


//    }
//}
