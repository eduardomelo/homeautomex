using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace HomeAutomex.Models
{
    public class ResidenciaModel : BaseModel
    {
        [Required(ErrorMessage = "Digite o Lougradouro de sua residência."), Column(Order = 1)]
        [MinLength(5, ErrorMessage = "Residência: O tamanho mínimo do lougradouro são 10 caracteres.")]
        [StringLength(200, ErrorMessage = "Residência: O tamanho máximo são 200 caracteres.")]
        public string Logradouro { get; set; }
        [Required(ErrorMessage = "Digite a cidade da sua resindência."), Column(Order = 1)]
        [MinLength(5, ErrorMessage = "Cidade: O tamanho mínimo do cidade são 10 caracteres.")]
        [StringLength(200, ErrorMessage = "Cidade: O tamanho máximo são 200 caracteres.")]
        public string Cidade { get; set; }
        [Required(ErrorMessage = "Digite a bairro da sua resindência."), Column(Order = 1)]
        [MinLength(5, ErrorMessage = "Bairro: O tamanho mínimo do bairro são 10 caracteres.")]
        [StringLength(200, ErrorMessage = "Bairro: O tamanho máximo são 200 caracteres.")]
        public string Bairro { get; set; }
        [Required(ErrorMessage = "Digite a cep da sua resindência."), Column(Order = 1)]
        [MinLength(5, ErrorMessage = "CEP: O tamanho mínimo do CEP são 10 caracteres.")]
        [StringLength(200, ErrorMessage = "CEP: O tamanho máximo são 200 caracteres.")]
        public string Cep { get; set; }
        [Required(ErrorMessage = "Digite a número da sua resindência."), Column(Order = 1)]
        [MinLength(5, ErrorMessage = "Número: O tamanho mínimo do número da residência são 10 caracteres.")]
        [StringLength(200, ErrorMessage = "Número: O tamanho máximo são 200 caracteres.")]
        public string Numero { get; set; }
        [Required(ErrorMessage = "Digite a complemento da sua resindência."), Column(Order = 1)]
        [MinLength(5, ErrorMessage = "Complemento: O tamanho mínimo do complemento da residência são 10 caracteres.")]
        [StringLength(200, ErrorMessage = "Complemento: O tamanho máximo são 200 caracteres.")]
        public string Complemento { get; set; }
    }
}