using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using HomeAutomexLibrary.Entidade;

namespace HomeAutomexLibrary.Repositorio.Map
{
    public class CenarioMap : EntityTypeConfiguration<Cenario>
    {
        public CenarioMap()
        {

            ToTable("CENARIO");

            HasKey(e => e.Chave)
                .Property(e => e.Chave)
                .HasColumnName("CD_CENARIO")
                .HasDatabaseGeneratedOption(DatabaseGeneratedOption.Identity);

            Property(e => e.Descricao).HasColumnName("DS_DESCRICAO").IsRequired();
            Property(e => e.Cadastro).HasColumnName("DT_CADASTRO");
            Property(e => e.Alteracao).HasColumnName("DT_ALTERACAO");
            Property(e => e.Exclusao).HasColumnName("DT_EXCLUSAO");
            Property(e => e.Desativado).HasColumnName("IS_DESATIVADO");

            Ignore(e => e.DataCadastro);
            Ignore(e => e.DataAlteracao);
            Ignore(e => e.DataExclusao);
        }

    }
}
