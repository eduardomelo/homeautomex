using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace HomeAutomex.Models
{
    public class CenarioModel : BaseModel
    {
        [Required]
        public string Descricao { get; set; }

        public bool Desativado { get; set; }
        public virtual int ChaveDispositivo { get; set; }
       

    }
}