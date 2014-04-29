﻿using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using HomeAutomexLibrary.Entidade;

namespace HomeAutomexLibrary.Repositorio.Map
{
    public class AgendamentoMap : EntityTypeConfiguration<Agendamento>
    {
        public AgendamentoMap()
        {

            ToTable("AGENDAMENTO");

            HasKey(e => e.Chave)
                .Property(e => e.Chave)
                .HasColumnName("CD_AGENDAMENTO")
                .HasDatabaseGeneratedOption(DatabaseGeneratedOption.Identity);

            Property(e => e.Ativo).HasColumnName("IS_ATIVO");
            Property(e => e.Descricao).HasColumnName("DS_DESCRICAO").IsRequired();
            Property(e => e.Cadastro).HasColumnName("DT_CADASTRO");
            Property(e => e.Altaracao).HasColumnName("DT_ALTERACAO");
            Property(e => e.Exclusao).HasColumnName("DT_EXCLUSAO");
            Property(e => e.Desativado).HasColumnName("IS_DESATIVADO");
            Property(e => e.Usuario).HasColumnName("CD_USUARIO");
            Property(e => e.Cenario).HasColumnName("CD_CENARIO");

            Ignore(e => e.DataCadastro);
            Ignore(e => e.DataAlteracao);
            Ignore(e => e.DataExclusao);
            
            //HasRequired(e => e.Usuario).WithMany().Map(e => e.MapKey("CD_USUARIO"));
            //HasRequired(e => e.Cenario).WithMany().Map(e => e.MapKey("CD_CENARIO"));
        }

    }
}