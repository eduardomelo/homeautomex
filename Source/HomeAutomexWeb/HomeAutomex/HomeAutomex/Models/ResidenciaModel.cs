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
        //[Required(ErrorMessage = "Digite o Lougradouro!"), Column(Order = 1)]
        //[MinLength(3, ErrorMessage = "O tamanho mínimo do lougradouro é de 3 caracteres!")]
        //[StringLength(50, ErrorMessage = "O tamanho máximo do lougradouro é de 50 caracteres!")]
        public string Logradouro { get; set; }

        //[Required(ErrorMessage = "Digite o nome da cidade!"), Column(Order = 1)]
        //[MinLength(3, ErrorMessage = "O tamanho mínimo do nome da cidade é de 3 caracteres!")]
        //[StringLength(50, ErrorMessage = "O tamanho máximo do nome da cidade é de 50 caracteres!")]
        public string Cidade { get; set; }

     //   [Required(ErrorMessage = "Digite o nome do bairro da sua resindência!"), Column(Order = 1)]
     //   [MinLength(3, ErrorMessage = "O tamanho mínimo do bairro são 3 caracteres!")]
      //  [StringLength(30, ErrorMessage = "O tamanho máximo são 30 caracteres!")]
        public string Bairro { get; set; }

      //  [Required(ErrorMessage = "Digite o número do cep!"), Column(Order = 1)]
      //  [MinLength(8, ErrorMessage = "O tamanho mínimo do CEP é de 8 caracteres!")]
      //  [StringLength(10, ErrorMessage = "O tamanho máximo do CEP é de 10 caracteres!")]
        public string Cep { get; set; }

      //  [Required(ErrorMessage = "Digite a número da sua resindência!"), Column(Order = 1)]
        //[MinLength(1, ErrorMessage = "Número: O tamanho mínimo do número da residência é de um caractere!")] NÃO É NECESSÁRIO ESSE CAMPO, VISTO QUE 
      //  [StringLength(5, ErrorMessage = "O tamanho máximo do número da residência é de 5 caracteres.")]
        [Display(Name = "Número")]
        public string Numero { get; set; }

       
      //  [StringLength(100, ErrorMessage = "O tamanho máximo da descrição do complemento é de 100 caracteres.")]
        public string Complemento { get; set; }

        public List<Usuario> Usuarios { get; set; }
    }

    public class Usuario
    {
        public int Chave { get; set; }
    }
}
