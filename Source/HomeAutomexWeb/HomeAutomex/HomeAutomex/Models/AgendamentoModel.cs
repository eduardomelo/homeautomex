using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;
using HomeAutomexLibrary.Entidade;

namespace HomeAutomex.Models
{
    public class AgendamentoModel : BaseModel
    {
    //    [Required]
        public string Descricao { get; set; }
        public bool Ativo { get; set; }

        [DisplayFormat(ApplyFormatInEditMode = true)]
        public virtual DateTime? DataAgendamento { get; set; }
        public bool Desativado { get; set; }

        public int Usuario { get; set; }
        public int Cenario { get; set; }


     

    }
}