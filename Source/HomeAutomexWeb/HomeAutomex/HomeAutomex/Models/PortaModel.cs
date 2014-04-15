using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace HomeAutomex.Models
{
    public class PortaModel : BaseModel
    {
        [Required]
        [Display(Name = "Identificador")]
        public virtual string Identificador { get; set; }

        [Required]
        [Display(Name = "Numero da porta")]
        public virtual string NumeroPorta { get; set; }

        [Required]
        [Display(Name = "Módulo")]
        public virtual int ChaveModulo { get; set; }
        public virtual ModuloModel Modulo { get; set; }

        [Required]
        [Display(Name = "Tipo da porta")]
        public virtual int ChaveTipoPorta { get; set; }
        public virtual TipoPortaModel Tipo { get; set; }
    }
}