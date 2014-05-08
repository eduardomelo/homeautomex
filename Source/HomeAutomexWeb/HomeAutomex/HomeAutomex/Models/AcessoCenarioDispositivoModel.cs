using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using HomeAutomex.Models;

namespace HomeAutomexLibrary.Entidade
{
    public class AcessoCenarioDispositivoModel : BaseModel
    {

        public int Dispositivos { get; set; }
        public int Cenarios { get; set; }


        //public virtual List<Cenario> Cenarios { get; set; }
        //public virtual List<Dispositivo> Dispositivos { get; set; }

        //public AcessoCenarioDispositivoModel()
        //{
        //    Cenarios = new List<Cenario>();
        //    Dispositivos = new List<Dispositivo>();
        //}



    }
}
