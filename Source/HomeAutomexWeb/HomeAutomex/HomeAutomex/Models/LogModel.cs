using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using HomeAutomex.Models;

namespace HomeAutomexLibrary.Models
{
    public class LogModel : BaseModel
    {
        [Display(Name = "Usuario")]
        public int Cd_usuario { get; set; }

        [Display(Name = "Descrição")]
        public string Descricao { get; set; }

        [Display(Name = "Momento")]
        public virtual DateTime DataCadastro { get; set; }


    }
}
