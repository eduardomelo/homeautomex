using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using HomeAutomex.HomeAutomexService;
using HomeAutomex.Models;
using HomeAutomexLibrary.Entidade;
using HomeAutomexLibrary.Models;
using Microsoft.Reporting.WebForms;
using Microsoft.Reporting.WebForms.Internal.Soap.ReportingServices2005.Execution;
using Newtonsoft.Json;

namespace HomeAutomex.Controllers
{
    public class RelatorioController : Controller
    {
        //
        // GET: /Relatorio/
        private HomeAutomexWSSoapClient webService;
        public RelatorioController()
        {
            this.webService = new HomeAutomexWSSoapClient();

        }
        public ActionResult ConsultarLogPorIntervaloData(string DataInicial, string DataFinal)
        {
            var log = new List<LogModel>();
            if (ModelState.IsValid)
            {
                try
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
                    var x = webService.ConsultarLogPorIntervaloData(Convert.ToDateTime(DataInicial), Convert.ToDateTime(DataFinal));
                    log = JsonConvert.DeserializeObject<List<LogModel>>(x);

                }
                catch (Exception)
                {

                    throw;
                }
            }
            return View("ListarLogs", log);
        }
        public ActionResult ConsultarHistoriocoUsuPorIntervaloData(string DataInicial, string DataFinal)
        {
            var log = new List<LogModel>();
            if (ModelState.IsValid)
            {
                try
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
                    var x = webService.ConsultarHistoriocoUsuPorIntervaloData(Convert.ToDateTime(DataInicial), Convert.ToDateTime(DataFinal));
                    log = JsonConvert.DeserializeObject<List<LogModel>>(x);

                }
                catch (Exception)
                {

                    throw;
                }
            }
            return View("ListarLogs", log);
        }
        public ActionResult ListarHistoricoUsoDispositivo(string pesquisa, List<UTDispositivoModel> model = null)
        {
            if (model == null)
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
                    var x = webService.ConsultarTodosUTDispositivo();
                    var UTDispositivo = JsonConvert.DeserializeObject<List<UTDispositivoModel>>(x);
                    if (!string.IsNullOrEmpty(pesquisa))
                        return View(UTDispositivo.Where(e =>
                                    e.Descricao.Contains(pesquisa)));
                    return View(UTDispositivo);
                }

                return View();
            }
            else
            {
                return View(model);
            }

        }
        public ActionResult ListarLogs(string pesquisa, List<LogModel> model = null)
        {
            if (model == null)
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
                    var x = webService.ConsutarTodosLogs();
                    var log = JsonConvert.DeserializeObject<List<LogModel>>(x);
                    if (!string.IsNullOrEmpty(pesquisa))
                        return View(log.Where(e =>
                                    e.Descricao.Contains(pesquisa) ||
                                    e.Descricao.Contains(pesquisa)));
                    return View(log);
                }

                return View();
            }
            else
            {
                return View(model);
            }

        }
        public ActionResult Report(string tipoRelatorio)
        {
            LocalReport lr = new LocalReport();
            string path = Path.Combine(Server.MapPath("~/ReportViewer"), "RepLog.rdlc");
            if (System.IO.File.Exists(path))
            {
                lr.ReportPath = path;
            }
            else
            {
                // return View("");
            }
            var x = webService.ConsutarTodosLogs();
            var log = JsonConvert.DeserializeObject<List<LogModel>>(x);

            ReportDataSource rd = new ReportDataSource("DataSetLog", log);
            lr.DataSources.Add(rd);
            string reportType = tipoRelatorio;
            string mimeType;
            string encoding;
            string fileNameExtension;

            string deviceInfo =
              "<DeviceInfo>" +
              " <OutputFormat>PDF</OutputFormat>" +
              " <PageWidth>9in</PageWidth>" +
              " <PageHeight>11in</PageHeight>" +
              " <MarginTop>0.7in</MarginTop>" +
              " <MarginLeft>2in</MarginLeft>" +
              " <MarginRight>2in</MarginRight>" +
              " <MarginBottom>0.7in</MarginBottom>" +
              "</DeviceInfo>";

            Microsoft.Reporting.WebForms.Warning[] warnings;
            string[] streams;
            byte[] bytes;

            //Renderiza o relatório em bytes
            bytes = lr.Render(
            reportType,
            deviceInfo,
            out mimeType,
            out encoding,
            out fileNameExtension,
            out streams,
            out warnings);
            return File(bytes, mimeType);

        }


        public ActionResult ReportHistoricoUsoDispositivo(string tipoRelatorio)
        {
            LocalReport lr = new LocalReport();
            string path = Path.Combine(Server.MapPath("~/ReportViewer"), "RepLog.rdlc");
            if (System.IO.File.Exists(path))
            {
                lr.ReportPath = path;
            }
            else
            {
                // return View("");
            }
            var x = webService.ConsutarTodosLogs();
            var log = JsonConvert.DeserializeObject<List<LogModel>>(x);

            ReportDataSource rd = new ReportDataSource("DataSetLog", log);
            lr.DataSources.Add(rd);
            string reportType = tipoRelatorio;
            string mimeType;
            string encoding;
            string fileNameExtension;

            string deviceInfo =
              "<DeviceInfo>" +
              " <OutputFormat>PDF</OutputFormat>" +
              " <PageWidth>9in</PageWidth>" +
              " <PageHeight>11in</PageHeight>" +
              " <MarginTop>0.7in</MarginTop>" +
              " <MarginLeft>2in</MarginLeft>" +
              " <MarginRight>2in</MarginRight>" +
              " <MarginBottom>0.7in</MarginBottom>" +
              "</DeviceInfo>";

            Microsoft.Reporting.WebForms.Warning[] warnings;
            string[] streams;
            byte[] bytes;

            //Renderiza o relatório em bytes
            bytes = lr.Render(
            reportType,
            deviceInfo,
            out mimeType,
            out encoding,
            out fileNameExtension,
            out streams,
            out warnings);
            return File(bytes, mimeType);

        }
    }
}
