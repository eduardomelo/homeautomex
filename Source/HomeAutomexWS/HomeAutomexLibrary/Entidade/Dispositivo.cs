using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace HomeAutomexLibrary.Entidade
{
   public class Dispositivo : EntidadeBase<int>
    {
       
        public string Descricao { get; set; }
        public DateTime? DataCadastro { get; set; }
        public DateTime? DataAlteracao { get; set; }
        public DateTime? DataExclusao { get; set; }
        public bool Desativado { get; set; }
        public bool Status { get; set; }
        public int PortaModulo { get; set; }

        public bool Favorito { get; set; }

       
    }

   //public class DispositivoTeste : EntidadeBase<int>
   //{
   //    public virtual string Identificador { get; set; }
   //    public virtual bool Status { get; set; }
   //}

}
