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
    public class PerfilMap : EntityTypeConfiguration<Perfil>
    {

       public PerfilMap()
        {

            ToTable("PERFIL");

            HasKey(e => e.Chave)
                .Property(e => e.Chave)
                .HasColumnName("CD_PERFIL")
                .HasDatabaseGeneratedOption(DatabaseGeneratedOption.Identity);

            Property(e => e.Nome).HasColumnName("DS_NOME").IsRequired();
            Property(e => e.Admin).HasColumnName("IS_ADMIN");
            Property(e => e.Nome).HasColumnName("DS_NOME");
            Property(e => e.DataCadastro).HasColumnName("DT_CADASTRO");
            Property(e => e.DataAlteracao).HasColumnName("DT_ALTERACAO");
            Property(e => e.DataExclusao).HasColumnName("DT_EXCLUSAO");
            Property(e => e.Desativado).HasColumnName("IS_DESATIVADO");
            Property(e => e.Residencia).HasColumnName("CD_RESIDENCIA");

        
        }

    }
}
