using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;

namespace HomeAutomexLibrary.Entidade
{
    public class LogModel : EntidadeBase<int>
    {
        [Display(Name = "Usuario")]
        public int Cd_usuario { get; set; }

        [Display(Name = "Descrição")]
        public string Descricao { get; set; }


    }
}
