using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;
using HomeAutomexLibrary.Entidade;

namespace HomeAutomex.Models
{
    public class UTDispositivoModel : BaseModel
    {
        public string Descricao { get; set; }
        public string Nome { get; set; }
        public DateTime? UT_utilizacao { get; set; }
        public bool status { get; set; }
     


    
     

    }
}