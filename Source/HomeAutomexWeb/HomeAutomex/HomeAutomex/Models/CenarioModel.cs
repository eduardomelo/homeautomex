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


        public string Descricao { get; set; }
        public DateTime Cadastro { get; set; }
        public DateTime Alteracao { get; set; }
        public DateTime Exclusao { get; set; }
        public bool Desativado { get; set; } 
       

    }
}