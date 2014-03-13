using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HomeAutomex.Models
{
    public class ModuloModel : BaseModel
    {
        public string NumeroIP { get; set; }
        public string NumeroPorta { get; set; }
        public string Nome { get; set; }
        public string NumeroMac { get; set; }
        public ResidenciaModel Residencia {get; set;}

        public ModuloModel()
        {
            this.Residencia = new ResidenciaModel();
        }
    }
}