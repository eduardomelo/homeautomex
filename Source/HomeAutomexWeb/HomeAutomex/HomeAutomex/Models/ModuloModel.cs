﻿using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace HomeAutomex.Models
{
    public class ModuloModel : BaseModel
    {
        [Required(ErrorMessage = "Digite o número do IP."), Column(Order = 1)]
        [StringLength(50, ErrorMessage = "Número do IP: O tamanho máximo são 50 caracteres.")]

        public string NumeroIP { get; set; }
        [Required(ErrorMessage = "Digite o número da porta."), Column(Order = 1)]
        [StringLength(200, ErrorMessage = "Porta: O tamanho máximo são 50 caracteres.")]
        public string NumeroPorta { get; set; }


        [Required(ErrorMessage = "Digite o Nome."), Column(Order = 1)]
        [StringLength(200, ErrorMessage = "Nome: O tamanho máximo são 50 caracteres.")]

        public string Nome { get; set; }
        [Required(ErrorMessage = "Digite o Número do MAC."), Column(Order = 1)]
        [StringLength(200, ErrorMessage = "Número do MAC: O tamanho máximo são 50 caracteres.")]

        public string NumeroMac { get; set; }
        public int Residencia {get; set;}

      
    }
}