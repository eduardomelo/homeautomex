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
        [Display(Name = "Descrição")]
        public string Descricao { get; set; }
        public bool Ativo { get; set; }

        [Display(Name = "Data e Hora do Agendamento")]
        [DisplayFormat(ApplyFormatInEditMode = true)]
        public virtual DateTime? DataAgendamento { get; set; }
        public bool Desativado { get; set; }

        [Display(Name = "Usuário")]
        public int Usuario { get; set; }

        [Display(Name = "Dispositivo")]
        public int Dispositivo { get; set; }

        


     

    }
}