﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Entidade
{
    public class Ambiente : EntidadeBase<int>
    {
        public virtual string Descricao { get; set; }
        public virtual bool Desativado { get; set; }
        public virtual Residencia Residencia { get; set; }
        public virtual List<Usuario> Usuarios { get; set; }
        public virtual List<Cenario> Cenarios { get; set; }

        public Ambiente()
        {
            Cenarios = new List<Cenario>();
            Usuarios = new List<Usuario>();
        }

    }
}