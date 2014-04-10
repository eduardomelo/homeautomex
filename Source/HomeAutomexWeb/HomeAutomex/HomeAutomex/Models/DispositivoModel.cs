﻿using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace HomeAutomex.Models
{
    public class DispositivoModel : BaseModel
    {
        [Display(Name = "Descrição")]
        public string Descricao { get; set; }


         [Display(Name = "Data de Cadastro")]
        public DateTime? DataCadastro { get; set; }


         [Display(Name = "Data de Alteração")]
        public DateTime? DataAlteracao { get; set; }

         [Display(Name = "Data de Exclusão")]
        public DateTime? DataExclusao { get; set; }

         [Display(Name = "Desativado")]
        public bool Desativado { get; set; }

         [Display(Name = "Status")]
        public bool Status { get; set; }

         [Display(Name = "Porta do Modulo")]
        public int PortaModulo { get; set; }

         [Display(Name = "Favorito")]
         public bool Favorito { get; set; }
    }
}