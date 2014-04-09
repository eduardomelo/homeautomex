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
        [Display(Name = "Identificador")]
        [Required(ErrorMessage = "Digite o identificador."), Column(Order = 1)]
        public string Identificador { get; set; }

        [Display(Name = "Número da Porta")]
        [Required(ErrorMessage = "Digite o número da Porta."), Column(Order = 1)]
        public string NumeroPorta { get; set; }

        [Display(Name = "Tipo de Porta")]
        public int TipoPorta { get; set; }

        [Display(Name = "Modulo")]
        public int Modulo { get; set; }

    }
}