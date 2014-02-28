using HomeAutomex.HomeAutomexService;
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
        public ActionResult Index()
        {
            ViewBag.Message = "Modify this template to jump-start your ASP.NET MVC application.";

            return View();
        }

        public ActionResult About()
        {

            //////////////////////EXEMPLO DE UTILIZAÇÃO DO WEB SERVICE
            //var webService = new HomeAutomexWSSoapClient();
            //webService.InserirUsuário("");           


            ViewBag.Message = "Your app description page.";

            return View();
        }

        public ActionResult Contact()
        {
            ViewBag.Message = "Your contact page.";

            return View();
        }
    }
}
