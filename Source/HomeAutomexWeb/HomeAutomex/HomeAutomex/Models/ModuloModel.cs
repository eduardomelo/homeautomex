using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace HomeAutomex.Models
{
    public class ModuloModel : BaseModel
    {
        [Display(Name = "Número do IP")]
        [Required(ErrorMessage = "Digite o número do IP."), Column(Order = 1)]
        [StringLength(50, ErrorMessage = "Número do IP: O tamanho máximo são 50 caracteres.")]
        public string IP { get; set; }

         [Display(Name = "Número da Porta")]
        [Required(ErrorMessage = "Digite o número da porta."), Column(Order = 1)]
        [StringLength(200, ErrorMessage = "Porta: O tamanho máximo são 50 caracteres.")]
        public string Porta { get; set; }

         [Display(Name = "Nome")]
        [Required(ErrorMessage = "Digite o Nome."), Column(Order = 1)]
        [StringLength(200, ErrorMessage = "Nome: O tamanho máximo são 50 caracteres.")]
        public string Nome { get; set; }

        [Display(Name = "Número do MAC")]
        [Required(ErrorMessage = "Digite o Número do MAC."), Column(Order = 1)]
        [StringLength(200, ErrorMessage = "Número do MAC: O tamanho máximo são 50 caracteres.")]
        public string MAC { get; set; }

        [Display(Name = "Residência")]
        public int ChaveResidencia {get; set;}

        public ResidenciaModel Residencia { get; set; }
        public virtual bool Desativado { get; set; }
      
    }
}