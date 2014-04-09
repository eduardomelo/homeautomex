using System;
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
        [Required(ErrorMessage = "Digite uma descrição do seu Dispositivo."), Column(Order = 1)]
        [MinLength(3, ErrorMessage = "Descrição: O tamanho mínimo do nome são 3 caracteres.")]
        [StringLength(200, ErrorMessage = "Descrição: O tamanho máximo são 200 caracteres.")]
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
    }
}