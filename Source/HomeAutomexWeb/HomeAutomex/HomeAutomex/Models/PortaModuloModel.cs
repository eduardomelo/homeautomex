using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace HomeAutomex.Models
{
    public class PortaModuloModel : BaseModel
    {
        [Required(ErrorMessage = "Digite o identificador."), Column(Order = 1)]

        public string Identificador { get; set; }
        [Required(ErrorMessage = "Digite o número da Porta."), Column(Order = 1)]

        public string NumeroPorta { get; set; }
        public int TipoPorta { get; set; }
        public int Modulo { get; set; }

    }
}