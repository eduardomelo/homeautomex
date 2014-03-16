using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace HomeAutomex.Models
{
    public class AmbienteModel : BaseModel
    {
        public int Dispositivo { get; set; }
        public int Residencia { get; set; }
        [Required(ErrorMessage = "Digite uma Descrição."), Column(Order = 1)]
        [MinLength(2, ErrorMessage = "Descrição: O tamanho mínimo do nome são 2 caracteres.")]
        [StringLength(200, ErrorMessage = "Descrição: O tamanho máximo são 200 caracteres.")]
        public string Descricao { get; set; }
        public DateTime? DataCadastro { get; set; }
        public DateTime? DataAlteracao { get; set; }
        public DateTime? DataExclusao { get; set; }
        public bool Desativado { get; set; }
       

    }
}