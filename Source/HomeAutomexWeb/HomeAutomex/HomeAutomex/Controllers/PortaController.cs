using HomeAutomex.HomeAutomexService;
using HomeAutomex.Models;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using AutoMapper;
using System.Web.Mvc;
using HomeAutomexLibrary.Entidade;

namespace HomeAutomex.Controllers
{
    public class PortaController : Controller
    {
        private HomeAutomexWSSoapClient webService;

        public PortaController()
        {
            this.webService = new HomeAutomexWSSoapClient();
        }
        public ActionResult Registrar()
        {
            ViewBag.TipoPorta = GetDropDownTipoPorta();
            ViewBag.Modulo = GetDropDownModulo();
            return View();
        }
        // Carregar DropLists
        public List<SelectListItem> GetDropDownTipoPorta()
        {
            var lista = new List<SelectListItem>();
            var x = webService.ConsutarTodosTipoPorta();
            var tipoPorta = JsonConvert.DeserializeObject<List<TipoPortaModel>>(x);
            foreach (var item in tipoPorta)
            {
                lista.Add(new SelectListItem() { Text = item.Tipo, Value = item.Chave.ToString() });
            }
            return lista;
        }
        public List<SelectListItem> GetDropDownModulo()
        {
            var lista = new List<SelectListItem>();
            var x = webService.ConsultarModuloPorUsuario((Session["Usuario"] as UsuarioModel).Chave.ToString());
            var modulo = JsonConvert.DeserializeObject<List<ModuloModel>>(x);
            foreach (var item in modulo)
            {
                lista.Add(new SelectListItem() { Text = item.Nome, Value = item.Chave.ToString() });
            }
            return lista;
        }
               
        [HttpPost]
        [AllowAnonymous]
        [ValidateAntiForgeryToken]
        public ActionResult Registrar(PortaModel model)
        {
            if (ModelState.IsValid)
            {
                try
                {
                    model.Modulo = JsonConvert.DeserializeObject<ModuloModel>(webService.BuscarModuloPorChave(model.ChaveModulo.ToString()));
                    model.Tipo = JsonConvert.DeserializeObject<TipoPortaModel>(webService.BuscarTipoPortaPorChave(model.ChaveTipoPorta.ToString()));
                    var porta = Mapper.DynamicMap<Porta>(model);
                    var retorno = webService.InserirPorta(JsonConvert.SerializeObject(porta));
                    if (retorno.StartsWith("Erro:"))
                    {
                        ModelState.AddModelError("WSErro", retorno);
                    }
                    else
                    {
                        return RedirectToAction("Listar");
                    }
                }
                catch (Exception e)
                {
                    ModelState.AddModelError("", e);
                }
            }
            return View(model);
        }


        [HttpPost]
        [AllowAnonymous]
        public ActionResult Editar(PortaModel model)
        {
            if (ModelState.IsValid)
            {
                try
                {
                    model.Modulo = JsonConvert.DeserializeObject<ModuloModel>(webService.BuscarModuloPorChave(model.ChaveModulo.ToString()));
                    model.Tipo = JsonConvert.DeserializeObject<TipoPortaModel>(webService.BuscarTipoPortaPorChave(model.ChaveTipoPorta.ToString()));
                    var porta = Mapper.DynamicMap<Porta>(model);
                    var retorno = webService.AlterarPorta(JsonConvert.SerializeObject(porta));
                    if (retorno.StartsWith("Erro:"))
                    {
                        ModelState.AddModelError("WSErro", retorno);
                    }
                    else
                    {
                        return RedirectToAction("Listar");
                    }
                }
                catch (Exception e)
                {
                    ModelState.AddModelError("", e);
                }

            }
            return View(model);
        }


        public ActionResult Editar(int chave)
        {
            ViewBag.TipoPorta = GetDropDownTipoPorta();
            ViewBag.Modulo = GetDropDownModulo();
            var portamodulo = Mapper.DynamicMap<PortaModel>(JsonConvert.DeserializeObject<Porta>(webService.BuscarPortaPorChave(chave.ToString())));
            return View(portamodulo);
        }


        [AllowAnonymous]
        public ActionResult Excluir(int chave)    
        {
            var retorno = JsonConvert.DeserializeObject(webService.ExcluirPorta(chave.ToString()));
            return RedirectToAction("Listar");
        }


        public ActionResult Listar(string pesquisa)
        {
            if (ModelState.IsValid)
            {
                var webService = new HomeAutomexWSSoapClient();
                var x = webService.ConsutarTodosPorta();
                var portaModulo = JsonConvert.DeserializeObject<List<PortaModel>>(x);
                if (!string.IsNullOrEmpty(pesquisa))
                    return View(portaModulo.Where(e =>
                                e.Identificador.Contains(pesquisa) ||
                                e.Identificador.Contains(pesquisa)));
                return View(portaModulo);
            }
            return View();
        }
    }
}